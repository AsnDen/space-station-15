package org.technocracy.spacestation.mutation.mutations;

import org.technocracy.spacestation.mutation.Mutation;

public class MutationNothing extends Mutation {

    @Override
    public boolean apply(MutationContext context) {
        // Literally nothing
        return true;
    }
}
