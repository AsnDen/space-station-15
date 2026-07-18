package org.technocracy.spacestation.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import org.technocracy.spacestation.client.chemistry.ChemMasterScreen;
import org.technocracy.spacestation.client.hud.TimerHud;
import org.technocracy.spacestation.chemistry.ModScreenHandlers;
import org.technocracy.spacestation.registry.ModBlocks;
import org.technocracy.spacestation.registry.blocks.PlantBlocks;

public class SpaceStationClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModScreenHandlers.register(); // добавь это первым!
        HandledScreens.register(ModScreenHandlers.CHEM_MASTER, ChemMasterScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALL_GIRDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALL_GIRDER_REINFORCED, RenderLayer.getCutout());
        // Crops
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.ALOE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.AMBROSIA_DEUS_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.AMBROSIA_VULGARIS_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.BLOOD_TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.BLOONION_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.BLUE_TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.BUNGO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.CABBAGE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.CHILI_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.CHILLI_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.CORN_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.COTTON_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.DEATH_NETTLE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.EGGPLANT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.EGGY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.GARLIC_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.KOIBEAN_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.LAUGHIN_PEA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.MEATWHEAT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.NETTLE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.TOWERCAP_CROP, RenderLayer.getCutout());
        TimerHud.register();
    }
}
