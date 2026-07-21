package org.technocracy.spacestation.registry.items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.registry.blocks.PlantBlocks;

public final class PlantItems {

    public static final Item ALOE        = register("aloe");
    public static final Item ALOE_CREAM        = register("aloe_cream");

    public static final Item ALOE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "aloe_seeds"),
            new AliasedBlockItem(PlantBlocks.ALOE_CROP, new Item.Settings())
    );

    public static final Item AMBROSIA_DEUS        = register("ambrosia_deus");

    public static final Item AMBROSIA_DEUS_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "ambrosia_deus_seeds"),
            new AliasedBlockItem(PlantBlocks.AMBROSIA_DEUS_CROP, new Item.Settings())
    );

    public static final Item AMBROSIA_VULGARIS        = register("ambrosia_vulgaris");

    public static final Item AMBROSIA_VULGARIS_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "ambrosia_vulgaris_seeds"),
            new AliasedBlockItem(PlantBlocks.AMBROSIA_VULGARIS_CROP, new Item.Settings())
    );

    public static final Item BLOOD_TOMATO        = register("blood_tomato");

    public static final Item BLOOD_TOMATO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "blood_tomato_seeds"),
            new AliasedBlockItem(PlantBlocks.BLOOD_TOMATO_CROP, new Item.Settings())
    );

    public static final Item BLOONION        = register("bloonion");

    public static final Item BLOONION_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bloonion_seeds"),
            new AliasedBlockItem(PlantBlocks.BLOONION_CROP, new Item.Settings())
    );

    public static final Item BLUE_TOMATO        = register("blue_tomato");

    public static final Item BLUE_TOMATO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "blue_tomato_seeds"),
            new AliasedBlockItem(PlantBlocks.BLUE_TOMATO_CROP, new Item.Settings())
    );

    public static final Item BUNGO        = register("bungo");

    public static final Item BUNGO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bungo_seeds"),
            new AliasedBlockItem(PlantBlocks.BUNGO_CROP, new Item.Settings())
    );

    public static final Item CABBAGE        = register("cabbage");

    public static final Item CABBAGE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cabbage_seeds"),
            new AliasedBlockItem(PlantBlocks.CABBAGE_CROP, new Item.Settings())
    );

    public static final Item CHILI        = register("chili");

    public static final Item CHILI_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "chili_seeds"),
            new AliasedBlockItem(PlantBlocks.CHILI_CROP, new Item.Settings())
    );

    public static final Item CHILLI        = register("chilli");

    public static final Item CHILLI_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "chilli_seeds"),
            new AliasedBlockItem(PlantBlocks.CHILLI_CROP, new Item.Settings())
    );

    public static final Item CORN        = register("corn");

    public static final Item CORN_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "corn_seeds"),
            new AliasedBlockItem(PlantBlocks.CORN_CROP, new Item.Settings())
    );

    public static final Item COTTON        = register("cotton");
    public static final Item COTTON_RAW    = register("cotton_raw");

    public static final Item COTTON_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cotton_seeds"),
            new AliasedBlockItem(PlantBlocks.COTTON_CROP, new Item.Settings())
    );

    public static final Item DEATH_NETTLE        = register("death_nettle");

    public static final Item DEATH_NETTLE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "death_nettle_seeds"),
            new AliasedBlockItem(PlantBlocks.DEATH_NETTLE_CROP, new Item.Settings())
    );

    public static final Item EGGPLANT        = register("eggplant");

    public static final Item EGGPLANT_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "eggplant_seeds"),
            new AliasedBlockItem(PlantBlocks.EGGPLANT_CROP, new Item.Settings())
    );

    public static final Item EGGY_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "eggy_seeds"),
            new AliasedBlockItem(PlantBlocks.EGGY_CROP, new Item.Settings())
    );

    public static final Item GARLIC        = register("garlic");

    public static final Item GARLIC_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "garlic_seeds"),
            new AliasedBlockItem(PlantBlocks.GARLIC_CROP, new Item.Settings())
    );

    public static final Item KOIBEAN        = register("koibean");

    public static final Item KOIBEAN_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "koibean_seeds"),
            new AliasedBlockItem(PlantBlocks.KOIBEAN_CROP, new Item.Settings())
    );

    public static final Item LAUGHIN_PEA        = register("laughin_pea");

    public static final Item LAUGHIN_PEA_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "laughin_pea_seeds"),
            new AliasedBlockItem(PlantBlocks.LAUGHIN_PEA_CROP, new Item.Settings())
    );

    public static final Item MEATWHEAT        = register("meatwheat");

    public static final Item MEATWHEAT_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "meatwheat_seeds"),
            new AliasedBlockItem(PlantBlocks.MEATWHEAT_CROP, new Item.Settings())
    );

    public static final Item NETTLE        = register("nettle");

    public static final Item NETTLE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "nettle_seeds"),
            new AliasedBlockItem(PlantBlocks.NETTLE_CROP, new Item.Settings())
    );

    public static final Item OAT        = register("oat");

    public static final Item OAT_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "oat_seeds"),
            new AliasedBlockItem(PlantBlocks.OAT_CROP, new Item.Settings())
    );

    public static final Item ONION        = register("onion");

    public static final Item ONION_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "onion_seeds"),
            new AliasedBlockItem(PlantBlocks.ONION_CROP, new Item.Settings())
    );

    public static final Item ONION_RED        = register("onion_red");

    public static final Item ONION_RED_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "onion_red_seeds"),
            new AliasedBlockItem(PlantBlocks.ONION_RED_CROP, new Item.Settings())
    );

    public static final Item PEA        = register("pea");

    public static final Item PEA_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pea_seeds"),
            new AliasedBlockItem(PlantBlocks.PEA_CROP, new Item.Settings())
    );

    public static final Item TOMATO = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.3f).build()))
    );

    public static final Item TOMATO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato_seeds"),
            new AliasedBlockItem(PlantBlocks.TOMATO_CROP, new Item.Settings())
    );


    public static final Item TOWERCAP_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "towercap_seeds"),
            new AliasedBlockItem(PlantBlocks.TOWERCAP_CROP, new Item.Settings())
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

    private PlantItems() {}

    public static void register() {}
}