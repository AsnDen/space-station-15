package org.technocracy.spacestation.registry.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.chemistry.ChemContainer;
import org.technocracy.spacestation.chemistry.ChemData;
import org.technocracy.spacestation.registry.ModComponents;

public final class ChemItems {

    public static final Item BEAKER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "beaker"),
            new ChemContainer(new Item.Settings()
                    .maxCount(1)
                    .component(ModComponents.CHEM_DATA, ChemData.EMPTY_BEAKER))
    );

    public static final Item CANISTER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "canister"),
            new ChemContainer(new Item.Settings()
                    .maxCount(1)
                    .component(ModComponents.CHEM_DATA, ChemData.EMPTY_CANISTER))
    );

    public static final Item COFFEE_POWDER = register("coffee_powder");
    public static final Item COPPER_SULFATE = register("copper_sulfate");
    public static final Item SALT = register("salt");
    public static final Item UNSTABLE_MUTAGEN_POWDER = register("unstable_mutagen_powder");

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    private ChemItems() {}

    public static void register() {}
}