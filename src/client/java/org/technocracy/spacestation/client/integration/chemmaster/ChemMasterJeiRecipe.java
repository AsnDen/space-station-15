package org.technocracy.spacestation.client.integration.chemmaster;

import net.minecraft.item.ItemStack;
import java.util.List;

public record ChemMasterJeiRecipe(List<ItemStack> inputs, List<ItemStack> outputs) {
}
