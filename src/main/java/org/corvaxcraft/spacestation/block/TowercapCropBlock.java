package org.corvaxcraft.spacestation.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import org.corvaxcraft.spacestation.registry.ModItems;

public class TowercapCropBlock extends CropBlock {
    public TowercapCropBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.TOWERCAP_SEEDS;
    }
}
