package org.technocracy.spacestation.chemistry.sublimator;

import net.minecraft.util.Identifier;

public record SublimationRecipe(String chemical, Identifier output, double units) {
    public static final double DEFAULT_UNITS = 25.0;
}
