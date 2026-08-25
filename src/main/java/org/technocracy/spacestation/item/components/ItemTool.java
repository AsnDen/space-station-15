package org.technocracy.spacestation.item.components;

import net.minecraft.component.ComponentMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Language;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.technocracy.spacestation.registry.ModComponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ItemTool extends Item {
    public static final int COLOR_LOW_CHARGE = 0xFF8C00;
    public static final int COLOR_NORMAL_CHARGE = 0xFFA500;

    public final Set<ToolQuality> QUALITIES;
    public final float SPEED;
    public final SoundEvent SOUND_ON_USE;

    public ItemTool(Item.Settings settings, ToolQuality... qualities) {
        this(settings, Set.of(qualities), 1.0f, SoundEvents.BLOCK_LAVA_EXTINGUISH);
    }

    public ItemTool(Item.Settings settings, float speed, ToolQuality... qualities) {
        this(settings, Set.of(qualities), speed, SoundEvents.BLOCK_LAVA_EXTINGUISH);
    }

    public ItemTool(Item.Settings settings, SoundEvent soundOnUse, ToolQuality... qualities) {
        this(settings, Set.of(qualities), 1.0f, soundOnUse);
    }

    public ItemTool(Item.Settings settings, float speed, SoundEvent soundOnUse, ToolQuality... qualities) {
        this(settings, Set.of(qualities), speed, soundOnUse);
    }

    public ItemTool(Item.Settings settings, Set<ToolQuality> qualities, float speed, SoundEvent soundOnUse) {
        super(settings.maxCount(1));
        this.QUALITIES = qualities == null ? Set.of() : Set.copyOf(qualities);
        this.SPEED = speed;
        this.SOUND_ON_USE = soundOnUse != null ? soundOnUse : SoundEvents.BLOCK_LAVA_EXTINGUISH;
    }

    public Set<ToolQuality> getQualities() {
        return QUALITIES;
    }

    public float getSpeed() {
        return SPEED;
    }

    public SoundEvent getSoundOnUse() {
        return SOUND_ON_USE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        ComponentMap allComps = stack.getComponents();
        if (allComps.contains(ModComponents.CHARGE_COMPONENT)) {
            return ChargeData.use(world, user, hand, this);
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Set<ToolQuality> qualities = getQualities();
        if (qualities.contains(ToolQuality.WELDING) || qualities.contains(ToolQuality.IGNITION)) {
            return Utils.ignite(context);
        }
        return super.useOnBlock(context);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        @Nullable
        ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);
        if (data == null) return super.isItemBarVisible(stack);
        return data.charge() < data.maxCharge();
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);
        if (data == null) return super.getItemBarStep(stack);
        return Math.clamp(ChargeData.getBarStep(stack), 0, 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);
        if (data == null) return super.getItemBarColor(stack);
        return data.charge() < data.maxCharge() / 4f ? COLOR_LOW_CHARGE : COLOR_NORMAL_CHARGE;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        ChargeData.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (QUALITIES.isEmpty()) {
            super.appendTooltip(stack, context, tooltip, type);
            return;
        }

        List<String> names = new ArrayList<>();
        Language lang = Language.getInstance();
        for (ToolQuality quality : QUALITIES) {
            names.add(lang.get(quality.getTranslationKey()));
        }

        tooltip.add(Text.translatable("tooltip.spacestation.tool", String.join(", ", names)));

        @Nullable
        ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);
        if (data != null) {
            int color = data.charge() < data.maxCharge() / 4f ? COLOR_LOW_CHARGE : COLOR_NORMAL_CHARGE;
            String curCharge = String.format("%.2f", data.charge());
            String maxCharge = String.format("%.2f", data.maxCharge());
            Text text = Text.literal(curCharge + " / " + maxCharge).styled(style -> style.withColor(color));
            tooltip.add(Text.translatable("tooltip.spacestation.charge", text));
        }
    }
}
