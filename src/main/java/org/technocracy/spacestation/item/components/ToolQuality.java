package org.technocracy.spacestation.item.components;

import java.util.Locale;
import java.util.Optional;

public enum ToolQuality {
    SCREWING("screwing"),
    WELDING("welding"),
    IGNITION("ignition"),
    PRYING("prying"),
    ANCHORING("anchoring");

    public static final ToolQuality[] ALL = values();

    private final String id;

    ToolQuality(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTranslationKey() {
        return "quality.spacestation." + id;
    }

    public static Optional<ToolQuality> fromName(String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }
        String lower = name.trim().toLowerCase(Locale.ROOT);
        for (ToolQuality quality : ALL) {
            if (quality.id.equals(lower) || quality.name().equalsIgnoreCase(name)) {
                return Optional.of(quality);
            }
        }
        return Optional.empty();
    }
}
