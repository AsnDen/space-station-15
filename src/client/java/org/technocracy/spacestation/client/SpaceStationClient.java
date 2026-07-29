package org.technocracy.spacestation.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.client.chemistry.ChemMasterScreen;
import org.technocracy.spacestation.client.hud.TimerHud;
import org.technocracy.spacestation.chemistry.ModScreenHandlers;
import org.technocracy.spacestation.registry.ModBlocks;
import org.technocracy.spacestation.registry.blocks.PlantBlocks;
import org.technocracy.spacestation.registry.items.ToolItems;

import static org.technocracy.spacestation.registry.components.ModComponents.ITEM_TOGGLE_COMPONENT;

public class SpaceStationClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModScreenHandlers.register(); // добавь это первым!
        HandledScreens.register(ModScreenHandlers.CHEM_MASTER, ChemMasterScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALL_GIRDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALL_GIRDER_REINFORCED, RenderLayer.getCutout());
        // Crops
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.COTTON_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.TOWERCAP_CROP, RenderLayer.getCutout());
        TimerHud.register();
        registerToggleableItem(ToolItems.WELDER);
    }

    public static void registerToggleableItem(Item item) {
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.of(SpaceStation.MOD_ID, "item_toggle"),
                (stack, world, entity, seed) -> {
                    boolean isActivate = stack.getOrDefault(ITEM_TOGGLE_COMPONENT, false);
                    return isActivate ? 1.0F : 0.0F;
                }
        );
    }
}
