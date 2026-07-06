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

    public static final Block COTTON_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "cotton_crop"),
            new SimpleCropBlock(
                    AbstractBlock.Settings.copy(Blocks.WHEAT).nonOpaque().ticksRandomly(),
                    () -> PlantItems.COTTON_SEEDS
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
