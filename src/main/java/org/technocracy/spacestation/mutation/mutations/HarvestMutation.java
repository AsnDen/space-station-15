package org.technocracy.spacestation.mutation.mutations;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.IntProperty;
import org.technocracy.spacestation.mutation.Mutation;

public class HarvestMutation extends Mutation {

    @Override
    public void apply(MutationContext context) {
        BlockState state = context.world().getBlockState(context.pos());

        for (var property : state.getProperties()) {
            if (property instanceof IntProperty intProperty
                    && intProperty.getName().equals("age")) {

                int maxAge = intProperty.getValues().stream()
                        .mapToInt(Integer::intValue)
                        .max()
                        .orElse(0);

                context.world().setBlockState(
                        context.pos(),
                        state.with(intProperty, maxAge)
                );

                return;
            }
        }
    }
}
