package org.technocracy.spacestation.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.mutation.Mutation;
import org.technocracy.spacestation.mutation.MutationContext;
import org.technocracy.spacestation.mutation.MutationRegistry;
import org.technocracy.spacestation.mutation.WeightedMutation;
import org.technocracy.spacestation.registry.mutations.Mutations;

import java.util.ArrayList;
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
            List<WeightedMutation> mutations = new ArrayList<>();

            for (MutationRegistry.MutationEntry entry : recipe.mutations()) {
                Mutations.get(entry.id()).ifPresent(mutation -> {
                    mutations.add(
                            new WeightedMutation(
                                    mutation,
                                    entry.chance()
                            )
                    );
                });
            }

            MutationContext mutationContext = new MutationContext(
                    context.getWorld(),
                    context.getBlockPos()
            );

            Mutation mutation = chooseMutation(mutations);

            if (mutation != null) {
                mutation.apply(mutationContext);
                context.getStack().decrement(1);
            }
        });

        return ActionResult.SUCCESS;
    }

    private Mutation chooseMutation(List<WeightedMutation> mutations) {
        double totalWeight = 0;

        for (WeightedMutation entry : mutations) {
            if (entry.weight() > 0) {
                totalWeight += entry.weight();
            }
        }

        if (totalWeight <= 0) {
            return null;
        }

        // roll in [0, totalWeight)
        double roll = Math.random() * totalWeight;

        for (WeightedMutation entry : mutations) {
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
