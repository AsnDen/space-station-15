package org.technocracy.spacestation.mutation.mutations;

import net.minecraft.block.BlockState;
import org.technocracy.spacestation.mutation.Mutation;

import static org.technocracy.spacestation.mutation.BlockStateReplacer.replaceBlockState;

public class MutationDeath extends Mutation {

    private final BlockState state;

    public MutationDeath(BlockState state) {
        this.state = state;
    }

    @Override
    public boolean apply(Mutation.MutationContext context) {
        BlockState newState = replaceBlockState(this.state, context.world(), context.pos());
        context.world().setBlockState(context.pos(), newState);
        return true;
    }

}
