package org.technocracy.spacestation.client.integration.chemmaster;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.client.integration.JeiPlugin;
import org.technocracy.spacestation.registry.ModBlocks;

public class ChemMasterJeiCategory implements IRecipeCategory<ChemMasterJeiRecipe> {
    private final IDrawable icon;
    private final RecipeType<ChemMasterJeiRecipe> type;
    private final String titleKey;

    public ChemMasterJeiCategory(IGuiHelper guiHelper, RecipeType<ChemMasterJeiRecipe> type, String titleKey) {
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.CHEM_MASTER_BLOCK));
        this.type = type;
        this.titleKey = titleKey;
    }

    @Override public RecipeType<ChemMasterJeiRecipe> getRecipeType() { return type; }
    @Override public Text getTitle() { return Text.translatable(titleKey); }
    @Override public IDrawable getIcon() { return icon; }
    @Override public int getWidth() { return 160; }
    @Override public int getHeight() { return 80; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ChemMasterJeiRecipe recipe, IFocusGroup focuses) {
        for (int i = 0; i < recipe.inputs().size(); i++) {
            builder.addInputSlot(8 + (i % 4) * 24, 24 + (i / 4) * 24)
                    .addItemStack(recipe.inputs().get(i));
        }
        for (int i = 0; i < recipe.outputs().size(); i++) {
            builder.addOutputSlot(112 + (i % 2) * 24, 24 + (i / 2) * 24)
                    .addItemStack(recipe.outputs().get(i));
        }
    }
}
