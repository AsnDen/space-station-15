package org.technocracy.spacestation.item.components;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public record ToolIngredient(Set<Item> needItems, Set<ToolQuality> needQualities) {

    public static final ToolIngredient EMPTY = new ToolIngredient(Set.of(), Set.of());

    public ToolIngredient {
        needItems = needItems == null ? Set.of() : Set.copyOf(needItems);
        needQualities = needQualities == null ? Set.of() : Set.copyOf(needQualities);
    }

    public static ToolIngredient empty() {
        return EMPTY;
    }

    public static ToolIngredient of() {
        return EMPTY;
    }

    public static ToolIngredient of(ToolQuality... qualities) {
        return new ToolIngredient(Set.of(), Set.of(qualities));
    }

    public static ToolIngredient of(Item... items) {
        return new ToolIngredient(Set.of(items), Set.of());
    }

    public static ToolIngredient of(Set<Item> items, Set<ToolQuality> qualities) {
        return new ToolIngredient(items, qualities);
    }

    public static ToolIngredient of(Object... ingredients) {
        if (ingredients == null || ingredients.length == 0) {
            return EMPTY;
        }

        Set<Item> items = new HashSet<>();
        Set<ToolQuality> qualities = new HashSet<>();

        for (Object obj : ingredients) {
            if (obj instanceof Item item) {
                items.add(item);
            } else if (obj instanceof ToolQuality quality) {
                qualities.add(quality);
            }
        }

        return new ToolIngredient(items, qualities);
    }

    public boolean isEmpty() {
        return needItems.isEmpty() && needQualities.isEmpty();
    }

    public boolean matches(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        return matches(stack.getItem());
    }

    public boolean matches(Item item) {
        if (item == null) {
            return false;
        }
        if (!needQualities.isEmpty() && item instanceof ItemTool tool) {
            if (tool.getQualities().containsAll(needQualities)) {
                return true;
            }
        }
        return needItems.contains(item);
    }

    public boolean contains(ItemStack stack) {
        return matches(stack);
    }

    public boolean contains(Item item) {
        return matches(item);
    }

    public boolean contains(Object obj) {
        if (obj instanceof ItemStack stack) {
            return matches(stack);
        } else if (obj instanceof Item item) {
            return matches(item);
        }
        return false;
    }
}
