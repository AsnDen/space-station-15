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
import org.technocracy.spacestation.mutation.Mutation;
import org.technocracy.spacestation.mutation.MutationRegistry;

import java.util.List;
import java.util.Optional;

public class MutatorItem extends Item {

    public MutatorItem(Item.Settings settings) {
        super(settings);
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

        Mutation mutation = chooseMutation(recipe.get().mutations());

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

        ServerWorld serverWorld = (ServerWorld) context.getWorld();

        serverWorld.spawnParticles(
                ParticleTypes.HAPPY_VILLAGER,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                8,
                0.3,
                0.5,
                0.3,
                0.1
        );

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
