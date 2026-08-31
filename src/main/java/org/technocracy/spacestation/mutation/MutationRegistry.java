package org.technocracy.spacestation.mutation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

import java.io.InputStreamReader;
import java.util.*;

public class MutationRegistry {

    private static final Gson GSON = new Gson();

    private static final Map<Identifier, MutationRecipe> MUTATIONS = new HashMap<>();

    public record MutationEntry(
            Identifier id,
            double chance
    ) {}

    public record MutationRecipe(
            Identifier target,
            List<MutationEntry> mutations
    ) {}

    public static void register() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA)
                .registerReloadListener(
                        new SimpleSynchronousResourceReloadListener() {

                            @Override
                            public Identifier getFabricId() {
                                return Identifier.of(SpaceStation.MOD_ID, "mutation_registry");
                            }

                            @Override
                            public void reload(ResourceManager manager) {
                                loadMutations(manager);
                            }
                        }
                );
    }

    private static void loadMutations(ResourceManager manager) {
        MUTATIONS.clear();

        manager.findResources(
                "mutations",
                id -> id.getPath().endsWith(".json")
        ).forEach((id, resource) -> {

            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {

                JsonObject json = GSON.fromJson(reader, JsonObject.class);

                Identifier target = Identifier.of(json.get("target").getAsString());

                if (!Registries.BLOCK.containsId(target)) {
                    throw new IllegalArgumentException("Unknown block: " + target);
                }

                JsonArray array = json.getAsJsonArray("mutations");

                List<MutationEntry> mutations = new ArrayList<>();

                for (JsonElement element : array) {
                    JsonObject mutation = element.getAsJsonObject();

                    Identifier mutationId = Identifier.of(mutation.get("id").getAsString());

                    double chance = mutation.get("weight").getAsDouble();

                    if (chance <= 0) {
                        throw new IllegalArgumentException("Mutation chance must be positive: " + mutationId);
                    }

                    mutations.add(new MutationEntry(mutationId, chance));
                }

                MUTATIONS.put(target, new MutationRecipe(target, List.copyOf(mutations)));

            } catch (Exception e) {
                SpaceStation.LOGGER.error("Ошибка загрузки mutation {}: {}", id, e.getMessage());
            }
        });

        SpaceStation.LOGGER.info("Загружено mutation рецептов: {}", MUTATIONS.size()
        );
    }

    public static Optional<MutationRecipe> get(Identifier target) {
        return Optional.ofNullable(MUTATIONS.get(target));
    }

    public static Collection<MutationRecipe> getAll() {
        return Collections.unmodifiableCollection(MUTATIONS.values());
    }
}