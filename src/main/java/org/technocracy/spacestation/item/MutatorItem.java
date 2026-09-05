package org.technocracy.spacestation.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.technocracy.spacestation.mutation.Mutation;
import org.technocracy.spacestation.mutation.MutationRegistry;

import java.util.List;
import java.util.Optional;

public class MutatorItem extends Item {

    private boolean ignoreNegative;

    public MutatorItem(Settings settings) {
        super(settings);
        this.ignoreNegative = false;
    }
    public void ignoreNegative() {
        this.ignoreNegative = true;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) {
            return ActionResult.PASS;
        }

        BlockPos pos = context.getBlockPos();
        Block block = context.getWorld().getBlockState(pos).getBlock();
        Identifier blockId = Registries.BLOCK.getId(block);

        Optional<MutationRegistry.MutationRecipe> recipe = MutationRegistry.get(blockId);

        if (recipe.isEmpty()) {
            return ActionResult.PASS;
        }

        Random random = context.getWorld().random;
        Mutation mutation = chooseMutation(recipe.get().mutations(), random);

        if (mutation == null) {
            return ActionResult.PASS;
        }

        Mutation.MutationContext mutationContext = new Mutation.MutationContext(
                context.getWorld(),
                pos
        );

        boolean applied = mutation.apply(mutationContext);

        if (!applied) {
            return ActionResult.PASS;
        }

        context.getStack().decrement(1);

        mutation.spawnParticles(mutationContext);

        return ActionResult.SUCCESS;
    }

    private Mutation chooseMutation(List<MutationRegistry.MutationEntry> mutations, Random random) {
        double totalWeight = 0;

        for (MutationRegistry.MutationEntry entry : mutations) {
            if (this.ignoreNegative && entry.isNegative()) {
                continue;
            }

            if (entry.weight() > 0) {
                totalWeight += entry.weight();
            }
        }

        if (totalWeight <= 0) {
            return null;
        }

        // roll in [0, totalWeight)
        double roll = random.nextDouble() * totalWeight;

        for (MutationRegistry.MutationEntry entry : mutations) {
            if (this.ignoreNegative && entry.isNegative()) {
                continue;
            }

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
