package org.technocracy.spacestation.client.integration.assembly;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.technocracy.spacestation.client.integration.JeiPlugin;
import org.technocracy.spacestation.registry.ModBlocks;
import net.minecraft.item.Item;

public class AssemblyJeiCategory implements IRecipeCategory<AssemblyJeiRecipe> {
    private final IDrawable icon;
    private final RecipeType<AssemblyJeiRecipe> type;
    private final String titleKey;

    public AssemblyJeiCategory(IGuiHelper guiHelper, RecipeType<AssemblyJeiRecipe> type,
                              String titleKey, Item iconItem) {
        icon = guiHelper.createDrawableItemStack(new ItemStack(iconItem));
        this.type = type;
        this.titleKey = titleKey;
    }

    @Override public RecipeType<AssemblyJeiRecipe> getRecipeType() { return type; }
    @Override public Text getTitle() { return Text.translatable(titleKey); }
    @Override public IDrawable getIcon() { return icon; }
    @Override public int getWidth() { return 180; }
    @Override public int getHeight() { return 82; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AssemblyJeiRecipe recipe, IFocusGroup focuses) {
        for (int i = 0; i < recipe.inputs().size(); i++) {
            builder.addInputSlot(8 + (i % 3) * 24, 26 + (i / 3) * 24)
                    .addItemStack(recipe.inputs().get(i));
        }
        for (int i = 0; i < recipe.outputs().size(); i++) {
            builder.addOutputSlot(132 + (i % 2) * 24, 26 + (i / 2) * 24)
                    .addItemStack(recipe.outputs().get(i));
        }
    }
}
