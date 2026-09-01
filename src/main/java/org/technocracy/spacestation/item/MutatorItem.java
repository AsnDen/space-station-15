package org.technocracy.spacestation.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.mutation.Mutation;
import org.technocracy.spacestation.mutation.MutationRegistry;

import java.util.List;

public class MutatorItem extends Item {

    public MutatorItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) {
            return ActionResult.SUCCESS;
        }

        Block block = context.getWorld()
                .getBlockState(context.getBlockPos())
                .getBlock();

        Identifier blockId = net.minecraft.registry.Registries.BLOCK.getId(block);

        MutationRegistry.get(blockId).ifPresent(recipe -> {

            Mutation.MutationContext mutationContext = new Mutation.MutationContext(
                    context.getWorld(),
                    context.getBlockPos()
            );

            Mutation mutation = chooseMutation(recipe.mutations());

            if (mutation != null) {
                mutation.apply(mutationContext);
                context.getStack().decrement(1);
            }
        });

        return ActionResult.SUCCESS;
    }

    private Mutation chooseMutation(List<MutationRegistry.MutationEntry> mutations) {
        double totalWeight = 0;

        for (MutationRegistry.MutationEntry entry : mutations) {
            if (entry.weight() > 0) {
                totalWeight += entry.weight();
            }
        }

        if (totalWeight <= 0) {
            return null;
        }

        // roll in [0, totalWeight)
        double roll = Math.random() * totalWeight;

        for (MutationRegistry.MutationEntry entry : mutations) {
            if (entry.weight() <= 0) {
                continue;
            }

            roll -= entry.weight();

            if (roll < 0) {
                return entry.mutation();
            }
        }

        return null;
    }

}
