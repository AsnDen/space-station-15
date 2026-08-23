package org.technocracy.spacestation.chemistry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ChemEffects {

    public record EffectSpec(RegistryEntry<StatusEffect> effect, int duration, int amplifier) {
        public EffectSpec(RegistryEntry<StatusEffect> effect, int duration) {
            this(effect, duration, 0);
        }
    }

    private static final Map<String, List<EffectSpec>> EFFECTS = new HashMap<>();
    private static final Set<String> INERT = new HashSet<>();
    private static final List<EffectSpec> DEFAULT_EFFECT = List.of(new EffectSpec(StatusEffects.NAUSEA, 80, 0));

    static {
        // Regeneration
        register(List.of("bicaridine", "inaprovaline", "saline"),
                new EffectSpec(StatusEffects.REGENERATION, 120, 0));
        register(List.of("nutrient_solution", "nutrient_paste", "mannitol"),
                new EffectSpec(StatusEffects.REGENERATION, 80, 0));
        register(List.of("insuzine", "doxarubixadone", "ethyloxyephedrine"),
                new EffectSpec(StatusEffects.REGENERATION, 70, 0));
        register(List.of("tricordrazine", "ultravasculine", "opporozidone", "britvium"),
                new EffectSpec(StatusEffects.REGENERATION, 100, 1));

        // Fire Resistance
        register(List.of("aloxadone", "cryoxadone", "leporazine"),
                new EffectSpec(StatusEffects.FIRE_RESISTANCE, 160, 0));
        register("pyrazine",
                new EffectSpec(StatusEffects.FIRE_RESISTANCE, 100, 0));

        // Arkryox (Fire Resistance + Resistance)
        register("arkryox",
                new EffectSpec(StatusEffects.FIRE_RESISTANCE, 120, 0),
                new EffectSpec(StatusEffects.RESISTANCE, 80, 0));

        // Water Breathing
        register(List.of("dexalin", "dexalin_plus"),
                new EffectSpec(StatusEffects.WATER_BREATHING, 180, 0));

        // Resistance
        register(List.of("kelotane", "dermaline", "antiseptic", "hydrogen_peroxide"),
                new EffectSpec(StatusEffects.RESISTANCE, 100, 0));
        register(List.of("charcoal", "dylovene", "tranexamic_acid"),
                new EffectSpec(StatusEffects.RESISTANCE, 80, 0));

        // Speed & Haste & Epinephrine
        register(List.of("epinephrine", "norepinephrine_acid"),
                new EffectSpec(StatusEffects.REGENERATION, 100, 0),
                new EffectSpec(StatusEffects.SPEED, 80, 0));
        register(List.of("ephedrine", "desoxyephedrine", "stimulants", "hyperzine"),
                new EffectSpec(StatusEffects.SPEED, 160, 1),
                new EffectSpec(StatusEffects.HASTE, 120, 0));
        register("glucose",
                new EffectSpec(StatusEffects.HASTE, 100, 0));

        // Night Vision
        register(List.of("cognizine", "psicodine", "synaptizine"),
                new EffectSpec(StatusEffects.NIGHT_VISION, 160, 0));
        register("oculine",
                new EffectSpec(StatusEffects.NIGHT_VISION, 220, 0));

        // Nausea & Toxins
        register(List.of("cryptobiolin", "bruizine"),
                new EffectSpec(StatusEffects.NAUSEA, 140, 0));
        register(List.of("paks", "space_drugs", "lipozine", "lipolicide", "mindbreaker_toxin",
                "heartbreaker_toxin", "mute_toxin"),
                new EffectSpec(StatusEffects.NAUSEA, 200, 0));
        register(List.of("ipecac", "chloral_hydrate", "nocturine"),
                new EffectSpec(StatusEffects.NAUSEA, 180, 0),
                new EffectSpec(StatusEffects.SLOWNESS, 120, 0));

        // Saturation & Blessing
        register(List.of("happiness", "holy_water", "blessing"),
                new EffectSpec(StatusEffects.SATURATION, 40, 0));

        // Weakness
        register(List.of("tazinide", "siderlac", "sigynate", "phalangimine"),
                new EffectSpec(StatusEffects.WEAKNESS, 120, 0));
        register("lexorin",
                new EffectSpec(StatusEffects.WEAKNESS, 220, 1));

        // Poison & Acids
        register(List.of("carbon_dioxide", "fluorosulfuric_acid", "polytrinic_acid", "thermite",
                "uranium", "unstable_mutagen", "ambuzol", "ambuzol_plus", "necrosol"),
                new EffectSpec(StatusEffects.POISON, 220, 1));
        register(List.of("bleach", "sodium_hydroxide", "sulfuric_acid", "iron_silicide"),
                new EffectSpec(StatusEffects.POISON, 260, 3),
                new EffectSpec(StatusEffects.WITHER, 160, 3));

        // Local Anesthetic
        register("local_anesthetic",
                new EffectSpec(StatusEffects.SLOWNESS, 140, 0),
                new EffectSpec(StatusEffects.RESISTANCE, 100, 0));

        // Mining Fatigue
        register(List.of("arithrazine", "hyronalin", "haloperidol", "impedrezene"),
                new EffectSpec(StatusEffects.MINING_FATIGUE, 180, 0));

        // Wither
        register(List.of("lacerinol", "puncturase", "hemorrhaginol", "warfarin"),
                new EffectSpec(StatusEffects.WITHER, 140, 0));

        // Inert reagents (no direct effect when ingested)
        registerInert(List.of(
                "foaming_agent", "fluorosurfactant", "space_cleaner", "space_glue",
                "fertilizer", "copper_sulfate", "sodium_chloride", "acetone", "ammonia",
                "benzene", "phenol", "ethanol", "diethylamine", "diphenhydramine",
                "diphenylmethylamine", "ethylredoxrazine", "oil", "oxygen", "water",
                "hydrogen", "nitrogen", "chlorine", "fluorine", "phosphorus", "potassium",
                "lithium", "iodine", "mercury", "radium", "silicon", "sodium", "aluminum",
                "iron", "copper", "calcium", "carbon", "ash", "sugar", "protein", "starch",
                "cellulose", "fat", "stone", "plasma", "table_salt", "sodium_carbonate",
                "sodium_polycarbonate", "potassium_iodide", "aloe", "capsaicin",
                "citric_acid", "toxin", "blood", "coffee", "welding_fuel"
        ));
    }

    private ChemEffects() {}

    public static void register(String chem, EffectSpec... specs) {
        EFFECTS.put(chem, List.of(specs));
    }

    public static void register(List<String> chems, EffectSpec... specs) {
        List<EffectSpec> list = List.of(specs);
        for (String chem : chems) {
            EFFECTS.put(chem, list);
        }
    }

    public static void registerInert(List<String> chems) {
        INERT.addAll(chems);
    }

    public static List<EffectSpec> getEffects(String chem) {
        List<EffectSpec> list = EFFECTS.get(chem);
        if (list != null) return list;
        if (INERT.contains(chem)) return List.of();
        return DEFAULT_EFFECT;
    }
}
