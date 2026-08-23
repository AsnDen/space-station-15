package org.technocracy.spacestation.chemistry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import org.technocracy.spacestation.block.AssemblyBlock;
import org.technocracy.spacestation.item.components.ToolIngredient;
import org.technocracy.spacestation.item.components.ToolQuality;
import org.technocracy.spacestation.chemistry.sublimator.SublimationRecipe;
import java.io.InputStreamReader;
import java.util.*;

public class ChemRegistry {

    private static final Gson GSON = new Gson();

    // Рецепт дробления
    public record GrindingRecipe(Identifier ingredient, Map<String, Double> results) {}

    // Рецепт реакции
    public record ReactionRecipe(Map<String, Double> reagents, Map<String, Double> results, double minVolume) {}

    private static final Map<Identifier, GrindingRecipe> GRINDING = new HashMap<>();
    private static final List<ReactionRecipe> REACTIONS = new ArrayList<>();
    private static final Map<String, SublimationRecipe> SUBLIMATION = new LinkedHashMap<>();

    public static void register() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA)
                .registerReloadListener(new SimpleSynchronousResourceReloadListener() {
                    @Override
                    public Identifier getFabricId() {
                        return Identifier.of("spacestation", "chem_registry");
                    }

                    @Override
                    public void reload(ResourceManager manager) {
                        loadGrinding(manager);
                        loadReactions(manager);
                        loadSublimation(manager);
                        loadAssembly(manager);
                    }
                });
    }

    private static void loadGrinding(ResourceManager manager) {
        GRINDING.clear();
        manager.findResources("grinding", id ->
                id.getNamespace().equals("spacestation") && id.getPath().endsWith(".json")
        ).forEach((id, resource) -> {
            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);
                Identifier ingredient = Identifier.of(json.get("ingredient").getAsString());
                Map<String, Double> results = parseDoubleMap(json.getAsJsonObject("results"));
                GRINDING.put(ingredient, new GrindingRecipe(ingredient, results));
            } catch (Exception e) {
                System.err.println("[SpaceStation] Ошибка загрузки grinding рецепта: " + id + " — " + e.getMessage());
            }
        });
        System.out.println("[SpaceStation] Загружено grinding рецептов: " + GRINDING.size());
    }

    private static void loadReactions(ResourceManager manager) {
        REACTIONS.clear();
        manager.findResources("reactions", id ->
                id.getNamespace().equals("spacestation") && id.getPath().endsWith(".json")
        ).forEach((id, resource) -> {
            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);

                if (json.has("reagents") && json.has("results")) {
                    Map<String, Double> reagents = parseDoubleMap(json.getAsJsonObject("reagents"));
                    Map<String, Double> results = parseDoubleMap(json.getAsJsonObject("results"));
                    double minVolume = json.has("min_volume") ? json.get("min_volume").getAsDouble() : 0.0;
                    REACTIONS.add(new ReactionRecipe(reagents, results, minVolume));
                    return;
                }

                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    if (!entry.getValue().isJsonObject()) continue;

                    String product = entry.getKey();
                    Map<String, Double> reagents = parseDoubleMap(entry.getValue().getAsJsonObject());
                    Map<String, Double> results = new LinkedHashMap<>();
                    results.put(product, 1.0);

                    double minVolume = reagents.values().stream().mapToDouble(Double::doubleValue).sum();
                    REACTIONS.add(new ReactionRecipe(reagents, results, minVolume));
                }
            } catch (Exception e) {
                System.err.println("[SpaceStation] Ошибка загрузки reaction рецепта: " + id + " — " + e.getMessage());
            }
        });
        System.out.println("[SpaceStation] Загружено reaction рецептов: " + REACTIONS.size());
    }

    private static void loadSublimation(ResourceManager manager) {
        SUBLIMATION.clear();
        manager.findResources("sublimation", id ->
                id.getNamespace().equals("spacestation") && id.getPath().endsWith(".json")
        ).forEach((id, resource) -> {
            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);
                String chemical = json.get("chemical").getAsString();
                Identifier output = Identifier.of(json.get("output").getAsString());
                double units = json.has("units")
                        ? json.get("units").getAsDouble()
                        : SublimationRecipe.DEFAULT_UNITS;
                if (units <= 0.0) throw new IllegalArgumentException("units must be positive");
                SUBLIMATION.put(chemical, new SublimationRecipe(chemical, output, units));
            } catch (Exception e) {
                System.err.println("[SpaceStation] Ошибка загрузки sublimation рецепта: " + id + " — " + e.getMessage());
            }
        });
        System.out.println("[SpaceStation] Загружено sublimation рецептов: " + SUBLIMATION.size());
    }

    private static void loadAssembly(ResourceManager manager) {
        AssemblyBlock.clearRecipes();
        manager.findResources("assembly", id ->
                id.getNamespace().equals("spacestation") && id.getPath().endsWith(".json")
        ).forEach((id, resource) -> {
            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);
                AssemblyBlock.registerUpgrade(
                        Registries.BLOCK.get(Identifier.of(json.get("source").getAsString())),
                        Registries.BLOCK.get(Identifier.of(json.get("result").getAsString())),
                        json.get("cost").getAsFloat(),
                        json.get("assembly_time").getAsFloat(),
                        json.has("fuel_cost") ? json.get("fuel_cost").getAsFloat() : 0.0f,
                        json.has("disassembly_time") ? json.get("disassembly_time").getAsFloat() : 0.0f,
                        parseToolIngredient(json.getAsJsonObject("assembly_tool")),
                        parseToolIngredient(json.getAsJsonObject("disassembly_tool"))
                );
            } catch (Exception e) {
                System.err.println("[SpaceStation] Ошибка загрузки assembly рецепта: " + id + " — " + e.getMessage());
            }
        });
        System.out.println("[SpaceStation] Загружено assembly рецептов: " + AssemblyBlock.getRecipes().size());
    }

    private static ToolIngredient parseToolIngredient(JsonObject json) {
        Set<net.minecraft.item.Item> items = new HashSet<>();
        Set<ToolQuality> qualities = new HashSet<>();
        if (json == null) return ToolIngredient.of();

        JsonArray itemArray = json.has("items") ? json.getAsJsonArray("items") : new JsonArray();
        itemArray.forEach(entry -> items.add(Registries.ITEM.get(Identifier.of(entry.getAsString()))));

        JsonArray qualityArray = json.has("qualities") ? json.getAsJsonArray("qualities") : new JsonArray();
        qualityArray.forEach(entry -> {
            for (ToolQuality quality : ToolQuality.ALL) {
                if (quality.name().equalsIgnoreCase(entry.getAsString())) qualities.add(quality);
            }
        });
        return new ToolIngredient(Set.copyOf(items), Set.copyOf(qualities));
    }

    private static Map<String, Double> parseDoubleMap(JsonObject obj) {
        Map<String, Double> map = new LinkedHashMap<>();
        for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getAsDouble());
        }
        return map;
    }

    // Получить рецепт дробления по айтему
    public static Optional<GrindingRecipe> getGrinding(Identifier itemId) {
        return Optional.ofNullable(GRINDING.get(itemId));
    }

    public static Collection<GrindingRecipe> getGrindingRecipes() {
        return Collections.unmodifiableCollection(GRINDING.values());
    }

    // Получить все рецепты реакций
    public static List<ReactionRecipe> getReactions() {
        return Collections.unmodifiableList(REACTIONS);
    }

    public static Optional<SublimationRecipe> getSublimation(String chemical) {
        return Optional.ofNullable(SUBLIMATION.get(chemical));
    }

    public static Collection<SublimationRecipe> getSublimationRecipes() {
        return Collections.unmodifiableCollection(SUBLIMATION.values());
    }
}