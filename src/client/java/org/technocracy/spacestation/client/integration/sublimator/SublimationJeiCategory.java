package org.technocracy.spacestation.client.integration.sublimator;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.client.integration.JeiPlugin;
import org.technocracy.spacestation.registry.ModBlocks;

public class SublimationJeiCategory implements IRecipeCategory<SublimationJeiRecipe> {
    private final IDrawable icon;

    public SublimationJeiCategory(IGuiHelper guiHelper) {
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.SUBLIMATOR));
    }

    @Override
    public RecipeType<SublimationJeiRecipe> getRecipeType() {
        return JeiPlugin.SUBLIMATION;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("jei.spacestation.sublimation");
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return 140;
    }

    @Override
    public int getHeight() {
        return 60;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SublimationJeiRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(20, 22)
                .addItemStack(recipe.input())
                .setSlotName("25u " + recipe.chemical());
        builder.addOutputSlot(100, 22)
                .addItemStack(recipe.output());
    }
}
