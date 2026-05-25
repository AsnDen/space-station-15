package org.corvaxcraft.spacestation;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import org.corvaxcraft.spacestation.ModItems;

public class TomatoCropBlock extends CropBlock {
    public TomatoCropBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.TOMATO_SEEDS;
    }
}