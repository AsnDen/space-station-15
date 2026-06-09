package org.corvaxcraft.spacestation;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;

public class TowercapCropBlock extends CropBlock {
    public TowercapCropBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.TOWERCAP_SEEDS;
    }
}