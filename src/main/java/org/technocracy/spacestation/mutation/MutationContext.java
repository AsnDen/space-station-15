package org.technocracy.spacestation.mutation;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public record MutationContext(World world, BlockPos pos) {}
