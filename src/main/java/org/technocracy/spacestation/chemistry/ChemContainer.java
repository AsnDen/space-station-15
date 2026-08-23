package org.technocracy.spacestation.chemistry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.technocracy.spacestation.registry.ModComponents;

import java.util.List;
import java.util.Map;

public class ChemContainer extends Item {

    private static final double DRINK_AMOUNT = 5.0; // юнитов за одно питьё.
    private static final int MAX_EFFECT_DURATION = 6000;

    public ChemContainer(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        ChemData data = stack.get(ModComponents.CHEM_DATA);
        if (data == null || data.chemicals().isEmpty()) {
            return super.getName(stack);
        }

        if (data.chemicals().size() > 1) {
            return Text.translatable("item.spacestation.beaker_of_mixture");
        }

        String chemical = data.chemicals().keySet().iterator().next();
        Text chemicalName = Text.translatableWithFallback(
                "chem.spacestation." + chemical,
                chemical
        );
        return Text.translatable("item.spacestation.beaker_of", chemicalName);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 8;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        ChemData data = stack.get(ModComponents.CHEM_DATA);

        if (data == null || data.chemicals().isEmpty()) {
            return TypedActionResult.pass(stack);
        }

        player.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient()) return stack;

        ChemData data = stack.get(ModComponents.CHEM_DATA);
        if (data == null || data.chemicals().isEmpty()) {
            return stack;
        }

        // ============ ПИТЬЁ ============
        ChemData updated = data;
        PlayerEntity player = user instanceof PlayerEntity playerEntity ? playerEntity : null;
        for (Map.Entry<String, Double> entry : data.chemicals().entrySet()) {
            String chem = entry.getKey();
            double amount = entry.getValue();

            if (amount < DRINK_AMOUNT) continue;

            updated = updated.remove(chem, DRINK_AMOUNT);
            if (player != null) {
                applyChemEffect(player, chem, DRINK_AMOUNT);
            }
        }

        if (updated == data) {
            return stack;
        }

        updated = ChemReactor.react(updated);

        stack.set(ModComponents.CHEM_DATA, updated);
        return stack;
    }

    // ============ ЭФФЕКТЫ ============
        private void applyChemEffect(PlayerEntity player, String chem, double amount) {
        switch (chem) {
            case "bicaridine", "inaprovaline", "saline" ->
                addEffect(player, StatusEffects.REGENERATION, 120, 0);
            case "aloxadone", "cryoxadone", "leporazine" ->
                addEffect(player, StatusEffects.FIRE_RESISTANCE, 160, 0);
            case "arkryox" -> {
                addEffect(player, StatusEffects.FIRE_RESISTANCE, 120, 0);
                addEffect(player, StatusEffects.RESISTANCE, 80, 0);
            }
            case "dexalin", "dexalin_plus" ->
                addEffect(player, StatusEffects.WATER_BREATHING, 180, 0);
            case "kelotane", "dermaline", "antiseptic", "hydrogen_peroxide" ->
                addEffect(player, StatusEffects.RESISTANCE, 100, 0);
            case "epinephrine", "norepinephrine_acid" -> {
            addEffect(player, StatusEffects.REGENERATION, 100, 0);
            addEffect(player, StatusEffects.SPEED, 80, 0);
            }
            case "ephedrine", "desoxyephedrine", "stimulants", "hyperzine" -> {
            addEffect(player, StatusEffects.SPEED, 160, 1);
            addEffect(player, StatusEffects.HASTE, 120, 0);
            }
            case "glucose" -> addEffect(player, StatusEffects.HASTE, 100, 0);
            case "nutrient_solution", "nutrient_paste", "mannitol" ->
                addEffect(player, StatusEffects.REGENERATION, 80, 0);
            case "cognizine", "psicodine", "synaptizine" ->
                addEffect(player, StatusEffects.NIGHT_VISION, 160, 0);
                case "cryptobiolin", "bruizine" ->
                    addEffect(player, StatusEffects.NAUSEA, 140, 0);
            case "happiness", "holy_water", "blessing" ->
                addEffect(player, StatusEffects.SATURATION, 40, 0);
            case "oculine" -> addEffect(player, StatusEffects.NIGHT_VISION, 220, 0);
            case "charcoal", "dylovene", "tranexamic_acid" ->
                addEffect(player, StatusEffects.RESISTANCE, 80, 0);
            case "tricordrazine", "ultravasculine", "opporozidone", "britvium" ->
                addEffect(player, StatusEffects.REGENERATION, 100, 1);
                case "insuzine", "doxarubixadone", "ethyloxyephedrine" ->
                    addEffect(player, StatusEffects.REGENERATION, 70, 0);
                case "tazinide", "siderlac", "sigynate", "phalangimine" ->
                    addEffect(player, StatusEffects.WEAKNESS, 120, 0);
                case "pyrazine" -> addEffect(player, StatusEffects.FIRE_RESISTANCE, 100, 0);
            case "paks", "space_drugs", "lipozine", "lipolicide", "mindbreaker_toxin",
                "heartbreaker_toxin", "mute_toxin" ->
                addEffect(player, StatusEffects.NAUSEA, 200, 0);
            case "ipecac", "chloral_hydrate", "nocturine" -> {
            addEffect(player, StatusEffects.NAUSEA, 180, 0);
            addEffect(player, StatusEffects.SLOWNESS, 120, 0);
            }
            case "carbon_dioxide", "fluorosulfuric_acid", "polytrinic_acid", "thermite",
                "uranium", "unstable_mutagen", "ambuzol", "ambuzol_plus", "necrosol" ->
                addEffect(player, StatusEffects.POISON, 220, 1);
            case "bleach", "sodium_hydroxide", "sulfuric_acid", "iron_silicide" ->
                addEffect(player, StatusEffects.POISON, 160, 0);
            case "lexorin" -> addEffect(player, StatusEffects.WEAKNESS, 220, 1);
            case "local_anesthetic" -> {
            addEffect(player, StatusEffects.SLOWNESS, 140, 0);
            addEffect(player, StatusEffects.RESISTANCE, 100, 0);
            }
            case "arithrazine", "hyronalin", "haloperidol", "impedrezene" ->
                addEffect(player, StatusEffects.MINING_FATIGUE, 180, 0);
            case "lacerinol", "puncturase", "hemorrhaginol", "warfarin" ->
                addEffect(player, StatusEffects.WITHER, 140, 0);
            case "foaming_agent", "fluorosurfactant", "space_cleaner", "space_glue",
                    "fertilizer", "copper_sulfate", "sodium_chloride", "acetone", "ammonia",
                    "benzene", "phenol", "ethanol",
                "diethylamine", "diphenhydramine", "diphenylmethylamine",
                "ethylredoxrazine", "oil", "oxygen",
                "water", "hydrogen", "nitrogen", "chlorine", "fluorine", "phosphorus",
                "potassium", "lithium", "iodine", "mercury", "radium", "silicon",
                "sodium", "aluminum", "iron", "copper", "calcium", "carbon", "ash",
                    "sugar", "protein", "starch", "cellulose", "fat", "stone", "plasma",
                    "table_salt", "sodium_carbonate", "sodium_polycarbonate",
                "potassium_iodide", "aloe", "capsaicin", "citric_acid", "toxin",
                    "blood", "coffee", "welding_fuel" -> {
            // Базовые вещества и технические растворы не дают прямого эффекта при питье.
            }
            default -> {
                addEffect(player, StatusEffects.NAUSEA, 80, 0);
            }
        }
    }

        private void addEffect(PlayerEntity player, net.minecraft.registry.entry.RegistryEntry<net.minecraft.entity.effect.StatusEffect> effect,
                    int duration, int amplifier) {
        StatusEffectInstance current = player.getStatusEffect(effect);
        int combinedDuration = duration;
        int combinedAmplifier = amplifier;
        if (current != null) {
            combinedDuration = Math.min(MAX_EFFECT_DURATION, current.getDuration() + duration);
            combinedAmplifier = Math.max(current.getAmplifier(), amplifier);
        }
        player.addStatusEffect(new StatusEffectInstance(effect, combinedDuration, combinedAmplifier));
        }

    // ============ ТУЛТИП В ИНВЕНТАРЕ ПРИ НАВЕДЕНИИ ============
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context,
                              List<Text> tooltip, TooltipType type) {
        ChemData data = stack.get(ModComponents.CHEM_DATA);
        if (data == null) return;

        if (data.chemicals().isEmpty()) {
            tooltip.add(Text.literal("§7Пусто"));
        } else {
            tooltip.add(Text.literal(String.format("§7%.1f / %.0f u", data.totalVolume(), data.capacity())));
            for (Map.Entry<String, Double> entry : data.chemicals().entrySet()) {
                String chemName = Text.translatableWithFallback(
                        "chem.spacestation." + entry.getKey(),
                        entry.getKey()
                ).getString();
                tooltip.add(Text.literal(String.format("  §f%s: §e%.2f u", chemName, entry.getValue())));
            }
        }
    }
}