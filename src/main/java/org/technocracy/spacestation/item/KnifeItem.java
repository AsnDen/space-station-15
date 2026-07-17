package org.technocracy.spacestation.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class KnifeItem extends Item {

    public KnifeItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setDamage(copy.getDamage() + 1);

        if (copy.getDamage() >= copy.getMaxDamage()) {

            return ItemStack.EMPTY;
        }

        return copy;
    }
}