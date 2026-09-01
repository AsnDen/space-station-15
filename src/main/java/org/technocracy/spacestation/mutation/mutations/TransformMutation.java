package org.technocracy.spacestation.mutation.mutations;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.technocracy.spacestation.mutation.Mutation;

import java.util.List;

public class TransformMutation extends Mutation {

    public record WeightedBlock(BlockState state, double weight) {}

    private final List<WeightedBlock> blocks;
    private final double totalWeight;

    public TransformMutation(List<WeightedBlock> blocks) {
        if (blocks.isEmpty()) {
            throw new IllegalArgumentException("TransformMutation requires at least one block.");
        }

        this.blocks = List.copyOf(blocks);
        this.totalWeight = blocks.stream()
                .mapToDouble(WeightedBlock::weight)
                .sum();

        if (totalWeight <= 0) {
            throw new IllegalArgumentException("Total weight must be greater than zero.");
        }
    }

    private void setBlockState(BlockState newState, World world, BlockPos pos) {
        BlockState oldState = world.getBlockState(pos);

        for (Property<?> property : oldState.getProperties()) {
            if (!newState.contains(property)) {
                continue;
            }

            newState = copyProperty(oldState, newState, property);
        }

        world.setBlockState(pos, newState);
    }

    private static <T extends Comparable<T>> BlockState copyProperty(
            BlockState oldState,
            BlockState newState,
            Property<T> property
    ) {
        T value = oldState.get(property);

        if (property.getValues().contains(value)) {
            return newState.with(property, value);
        }

        return newState;
    }

    private BlockState chooseBlock(Random random) {
        double roll = random.nextDouble() * totalWeight;

        double current = 0.0;
        for (WeightedBlock block : blocks) {
            current += block.weight();
            if (roll <= current) {
                return block.state();
            }
        }

        return blocks.getLast().state();
    }

    @Override
    public void apply(MutationContext context) {
        Random random = context.world().getRandom();
        BlockState selected = chooseBlock(random);
        setBlockState(selected, context.world(), context.pos());
    }
}
