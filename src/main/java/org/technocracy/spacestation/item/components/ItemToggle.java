package org.technocracy.spacestation.item.components;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.technocracy.spacestation.registry.ModComponents;

public class ItemToggle extends Item {
    public ItemToggle(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return toggle(world, user, hand, this);
    };

    public static TypedActionResult<ItemStack> toggle(World world, PlayerEntity user, Hand hand, Item item) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.getItemCooldownManager().isCoolingDown(item)) {
            return TypedActionResult.fail(stack);
        }

        if (!world.isClient()) {
            boolean isActivated = stack.getOrDefault(ModComponents.ITEM_TOGGLE_COMPONENT, false);

            stack.set(ModComponents.ITEM_TOGGLE_COMPONENT, !isActivated);


            int cooldown = Math.round(stack.getOrDefault(ModComponents.USE_DELAY_COMPONENT, 0.0F) * 20); // Time in component in seconds, there must be in ticks
            if (cooldown > 0) {
                user.getItemCooldownManager().set(item, cooldown);
            }

            SoundEvent sound = stack.getItem() instanceof ItemTool tool ? tool.SOUND_ON_USE : SoundEvents.BLOCK_LAVA_EXTINGUISH;

            // TODO: maybe add normal sound?
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    sound, SoundCategory.PLAYERS, 0.25F, !isActivated ? 0.5F : 0.6F);
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
