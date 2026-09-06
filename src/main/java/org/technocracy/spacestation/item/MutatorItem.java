package org.technocracy.spacestation.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.technocracy.spacestation.mutation.Mutation;
import org.technocracy.spacestation.mutation.MutationRegistry;

import java.util.List;
import java.util.Optional;

public class MutatorItem extends Item {

    private final double negativeMultiplier;
    private final SoundEvent mutationSound;

    public MutatorItem(Settings settings) {
        super(settings);

        if (settings instanceof MutatorSettings mutatorSettings) {
            this.negativeMultiplier = mutatorSettings.negativeMultiplier;
            this.mutationSound = mutatorSettings.mutationSound;
        } else {
            this.negativeMultiplier = 1.0;
            this.mutationSound = SoundEvents.ITEM_BONE_MEAL_USE;
        }
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

        context.getWorld().playSound(
                null,
                pos,
                this.mutationSound,
                SoundCategory.BLOCKS,
                1.0F,
                1.0F
        );

        return ActionResult.SUCCESS;
    }

    private Mutation chooseMutation(List<MutationRegistry.MutationEntry> mutations, Random random) {
        double totalWeight = 0;

        for (MutationRegistry.MutationEntry entry : mutations) {
            double weight = entry.weight();

            if (entry.isNegative()) {
                weight *= this.negativeMultiplier;
            }

            if (entry.weight() > 0) {
                totalWeight += weight;
            }
        }

        if (totalWeight <= 0) {
            return null;
        }

        double roll = random.nextDouble() * totalWeight;

        for (MutationRegistry.MutationEntry entry : mutations) {
            double weight = entry.weight();

            if (entry.isNegative()) {
                weight *= this.negativeMultiplier;
            }

            if (weight <= 0) {
                continue;
            }

            roll -= weight;

            if (roll < 0) {
                return entry.mutation();
            }
        }

        return null;
    }

    public static class MutatorSettings extends Item.Settings {
        private double negativeMultiplier = 1.0;
        private SoundEvent mutationSound = SoundEvents.ITEM_BONE_MEAL_USE;

        public MutatorSettings negativeMultiplier(double negativeMultiplier) {
            this.negativeMultiplier = negativeMultiplier;
            return this;
        }

        public MutatorSettings mutationSound(SoundEvent mutationSound) {
            this.mutationSound = mutationSound;
            return this;
        }
    }
}