package org.technocracy.spacestation.registry.items;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import org.technocracy.spacestation.item.ModArmorMaterials;
import org.technocracy.spacestation.item.armor.NukeopsArmorItem;

public class ArmorItems {

    public static final Item NUKEOPS_HELMET = register("nukeops_helmet",
            new NukeopsArmorItem(ModArmorMaterials.NUKEOPS, ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(520))));

    public static final Item NUKEOPS_CHESTPLATE = register("nukeops_chestplate",
            new NukeopsArmorItem(ModArmorMaterials.NUKEOPS, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(520))));

    public static final Item NUKEOPS_LEGGINGS = register("nukeops_leggings",
            new NukeopsArmorItem(ModArmorMaterials.NUKEOPS, ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(520))));

    public static final Item NUKEOPS_BOOTS = register("nukeops_boots",
            new NukeopsArmorItem(ModArmorMaterials.NUKEOPS, ArmorItem.Type.BOOTS,
                    new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(520))));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of("spacestation", name), item);
    }

    public static void register() {
    }
}