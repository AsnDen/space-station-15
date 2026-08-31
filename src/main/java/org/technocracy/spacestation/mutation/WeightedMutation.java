package org.technocracy.spacestation.mutation;

public record WeightedMutation(
        Mutation mutation,
        double weight
) {}
