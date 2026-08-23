package org.technocracy.spacestation.client.integration.assembly;

import net.minecraft.item.ItemStack;
import java.util.List;

public record AssemblyJeiRecipe(List<ItemStack> inputs, List<ItemStack> outputs,
                                boolean disassembly, float time, float fuelCost) {
}
