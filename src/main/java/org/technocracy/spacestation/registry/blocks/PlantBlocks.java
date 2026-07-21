package org.technocracy.spacestation.registry.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.block.SimpleCropBlock;
import org.technocracy.spacestation.registry.items.PlantItems;

public class PlantBlocks {
    public static final Block ALOE_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "aloe_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.ALOE_SEEDS
            )
    );

    public static final Block AMBROSIA_DEUS_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "ambrosia_deus_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.AMBROSIA_DEUS_SEEDS
            )
    );

    public static final Block AMBROSIA_VULGARIS_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "ambrosia_vulgaris_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.AMBROSIA_VULGARIS_SEEDS
            )
    );

    public static final Block BLOOD_TOMATO_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "blood_tomato_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.BLOOD_TOMATO_SEEDS
            )
    );

    public static final Block BLOONION_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "bloonion_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.BLOONION_SEEDS
            )
    );

    public static final Block BLUE_TOMATO_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "blue_tomato_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.BLUE_TOMATO_SEEDS
            )
    );

    public static final Block BUNGO_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "bungo_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.BUNGO_SEEDS
            )
    );

    public static final Block CABBAGE_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "cabbage_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.CABBAGE_SEEDS
            )
    );

    public static final Block CHILI_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "chili_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.CHILI_SEEDS
            )
    );

    public static final Block CHILLI_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "chilli_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.CHILLI_SEEDS
            )
    );

    public static final Block CORN_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "corn_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.CORN_SEEDS
            )
    );

    public static final Block COTTON_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "cotton_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.COTTON_SEEDS
            )
    );

    public static final Block DEATH_NETTLE_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "death_nettle_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.DEATH_NETTLE_SEEDS
            )
    );

    public static final Block EGGPLANT_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "eggplant_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.EGGPLANT_SEEDS
            )
    );

    public static final Block EGGY_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "eggy_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.EGGY_SEEDS
            )
    );

    public static final Block GARLIC_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "garlic_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.GARLIC_SEEDS
            )
    );

    public static final Block KOIBEAN_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "koibean_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.KOIBEAN_SEEDS
            )
    );

    public static final Block LAUGHIN_PEA_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "laughin_pea_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.LAUGHIN_PEA_SEEDS
            )
    );

    public static final Block MEATWHEAT_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "meatwheat_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.MEATWHEAT_SEEDS
            )
    );

    public static final Block NETTLE_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "nettle_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.NETTLE_SEEDS
            )
    );

    public static final Block OAT_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "oat_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.OAT_SEEDS
            )
    );

    public static final Block ONION_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "onion_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.ONION_SEEDS
            )
    );

    public static final Block ONION_RED_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "onion_red_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.ONION_RED_SEEDS
            )
    );

    public static final Block PEA_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "pea_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.PEA_SEEDS
            )
    );

    public static final Block PINEAPPLE_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "pineapple_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.PINEAPPLE_SEEDS
            )
    );

    public static final Block PIROTTON_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "pirotton_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.PIROTTON_SEEDS
            )
    );


    public static final Block RICE_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "rice_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.RICE_SEEDS
            )
    );

    public static final Block TOMATO_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "tomato_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.TOMATO_SEEDS
            )
    );

    public static final Block TOWERCAP_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "towercap_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.TOWERCAP_SEEDS
            )
    );

    private PlantBlocks() {}

    public static void register() {}
}
