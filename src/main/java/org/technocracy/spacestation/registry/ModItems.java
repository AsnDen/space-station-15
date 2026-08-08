package org.technocracy.spacestation.registry;

import net.minecraft.item.Item;
import org.technocracy.spacestation.registry.items.*;

public final class ModItems {

    // ============ Шорткаты для часто используемых айтемов из других систем ============
    public static final Item CROWBAR  = ToolItems.CROWBAR;


    public static void register() {
        FoodItems.register();
        DrinkItems.register();
        ToolItems.register();
        PlushieItems.register();
        ChemItems.register();
        MiscItems.register();
        PlantItems.register();
        ArmorItems.register();
    }

    private ModItems() {}
}