package org.technocracy.spacestation.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.block.CottonCropBlock;
import org.technocracy.spacestation.block.TomatoCropBlock;
import org.technocracy.spacestation.block.TowercapCropBlock;
import org.technocracy.spacestation.chemistry.ChemContainer;
import org.technocracy.spacestation.chemistry.ChemData;
import org.technocracy.spacestation.chemistry.ModComponents;

public final class ModCrops {

    public static final Item COTTON        = register("cotton");
    public static final Item COTTON_RAW    = register("cotton_raw");

    public static final Block COTTON_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "cotton_crop"),
            new CottonCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)
                    .nonOpaque()
                    .ticksRandomly())
    );

    public static final Item COTTON_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cotton_seeds"),
            new AliasedBlockItem(ModCrops.COTTON_CROP, new Item.Settings())
    );

    public static final Item TOMATO = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.3f).build()))
    );

    public static final Block TOMATO_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "tomato_crop"),
            new TomatoCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)
                    .nonOpaque()
                    .ticksRandomly())
    );

    public static final Item TOMATO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato_seeds"),
            new AliasedBlockItem(ModCrops.TOMATO_CROP, new Item.Settings())
    );


    public static final Item TOWERCAP_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "towercap_seeds"),
            new AliasedBlockItem(ModCrops.TOWERCAP_CROP, new Item.Settings())
    );

    public static final Block TOWERCAP_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "towercap_crop"),
            new TowercapCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)
                    .nonOpaque()
                    .ticksRandomly())
    );

    // TODO (asnden): pineapple stuff
    public static final Item PINEAPPLE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pineapple"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    private ModCrops() {}

    public static void register() {}
}