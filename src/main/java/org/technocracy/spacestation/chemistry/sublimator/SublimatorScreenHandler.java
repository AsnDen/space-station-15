package org.technocracy.spacestation.chemistry.sublimator;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import org.technocracy.spacestation.chemistry.ChemContainer;
import org.technocracy.spacestation.chemistry.ModScreenHandlers;

public class SublimatorScreenHandler extends ScreenHandler {
    public final SublimatorBlockEntity entity;

    public SublimatorScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        super(ModScreenHandlers.SUBLIMATOR, syncId);
        BlockEntity blockEntity = inventory.player.getWorld().getBlockEntity(pos);
        this.entity = blockEntity instanceof SublimatorBlockEntity sublimator ? sublimator : null;

        if (entity != null) {
            addSlot(new Slot(entity, 0, 32, 36) {
                @Override public boolean canInsert(ItemStack stack) {
                    return stack.getItem() instanceof ChemContainer;
                }
            });
            addSlot(new Slot(entity, 1, 127, 36) {
                @Override public boolean canInsert(ItemStack stack) { return false; }
            });
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 108 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inventory, col, 8 + col * 18, 166));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);
        if (!slot.hasStack()) return result;

        ItemStack stack = slot.getStack();
        result = stack.copy();
        if (slotIndex < 2) {
            if (!insertItem(stack, 2, slots.size(), true)) return ItemStack.EMPTY;
        } else if (stack.getItem() instanceof ChemContainer) {
            if (!insertItem(stack, 0, 1, false)) return ItemStack.EMPTY;
        } else {
            return ItemStack.EMPTY;
        }
        if (stack.isEmpty()) slot.setStack(ItemStack.EMPTY);
        else slot.markDirty();
        return result;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return entity != null && entity.canPlayerUse(player);
    }
}
