package org.technocracy.spacestation.registry.mutations;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.mutation.Mutation;
import org.technocracy.spacestation.mutation.mutations.TransformMutation;
import org.technocracy.spacestation.registry.blocks.PlantBlocks;

import java.util.*;

public class Mutations {

    private static final Map<Identifier, Mutation> MUTATIONS = new HashMap<>();

    public static final Mutation DEATH_MUTATION = register(
            "death_mutation",
            new TransformMutation(List.of(
                    new TransformMutation.WeightedBlock(
                            Blocks.DEAD_BUSH.getDefaultState(),
                            1
                    )
            ))
    );

    public static final Mutation WHEAT_MUTATION = register(
            "wheat_mutation",
            new TransformMutation(List.of(
                    new TransformMutation.WeightedBlock(
                            PlantBlocks.MEATWHEAT_CROP.getDefaultState(),
                            1
                    )
            ))
    );

    private static Mutation register(String id, Mutation mutation) {
        Identifier identifier = Identifier.of(SpaceStation.MOD_ID, id);

        if (MUTATIONS.containsKey(identifier)) {
            throw new IllegalStateException(
                    "Mutation already registered: " + identifier
            );
        }

        MUTATIONS.put(identifier, mutation);

        return mutation;
    }

    public static Optional<Mutation> get(Identifier id) {
        return Optional.ofNullable(MUTATIONS.get(id));
    }

    public static Map<Identifier, Mutation> getAll() {
        return Collections.unmodifiableMap(MUTATIONS);
    }

    private Mutations() {}

    public static void register() {}
}
