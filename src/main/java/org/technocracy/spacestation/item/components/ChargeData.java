package org.technocracy.spacestation.item.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.technocracy.spacestation.registry.ModComponents;

import java.util.Map;

public record ChargeData(float charge, float maxCharge, float chargeUsage) {
    public ChargeData {
        charge = Math.max(0f, Math.min(charge, maxCharge));
    }
    public ChargeData(float charge, float maxCharge) {
        this(charge, maxCharge, 0.025F);
    }

    public static final ChargeData DEFAULT = new ChargeData(0f, 0f, 0f);
    public static final Codec<ChargeData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("charge").forGetter(ChargeData::charge),
            Codec.FLOAT.fieldOf("max_charge").forGetter(ChargeData::maxCharge),
            Codec.FLOAT.fieldOf("charge_usage").forGetter(ChargeData::chargeUsage)
    ).apply(instance, ChargeData::new));

    public static final PacketCodec<RegistryByteBuf, ChargeData> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, ChargeData::charge,
            PacketCodecs.FLOAT, ChargeData::maxCharge,
            PacketCodecs.FLOAT, ChargeData::chargeUsage,
            ChargeData::new
    );

    public ChargeData withCharge(float newCharge) {
        float clamped = Math.clamp(newCharge, 0F, this.maxCharge);
        return new ChargeData(clamped, this.maxCharge, this.chargeUsage);
    }

    public ChargeData withMaxCharge(float newMaxCharge) {
        return new ChargeData(this.charge, Math.max(0F, newMaxCharge), this.chargeUsage);
    }

    public ChargeData withChargeUsage(float newChargeUsage) {
        return new ChargeData(this.charge, this.maxCharge, newChargeUsage);
    }

    public static void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        if (!world.isClient() && stack.getOrDefault(ModComponents.ITEM_TOGGLE_COMPONENT, false)) {// every 1 second

            ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);

            if (data == null) {
                stack.set(ModComponents.ITEM_TOGGLE_COMPONENT, false);
                return;
            }

            if (data.charge() > 0) {
                stack.set(ModComponents.CHARGE_COMPONENT, data.withCharge(data.charge() - data.chargeUsage()));
            } else {
                stack.set(ModComponents.CHARGE_COMPONENT, data.withCharge(0));
                stack.set(ModComponents.ITEM_TOGGLE_COMPONENT, false);

                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        net.minecraft.sound.SoundEvents.BLOCK_LAVA_EXTINGUISH,
                        net.minecraft.sound.SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
    }

    public static TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand, Item item) {
        ItemStack stack = user.getStackInHand(hand);


        ChargeData chargeData = stack.get(ModComponents.CHARGE_COMPONENT);
        if (chargeData == null) return TypedActionResult.pass(stack);

        if (chargeData.charge() <= 0 && !stack.getOrDefault(ModComponents.ITEM_TOGGLE_COMPONENT, false)) {
            return TypedActionResult.fail(stack);
        }

        return ItemToggle.toggle(world, user, hand, item);
    }

    public static final Map<Item, Float> FUELS = Map.of(
            net.minecraft.item.Items.LAVA_BUCKET, 100F,
            net.minecraft.item.Items.COAL, 50F,
            net.minecraft.item.Items.CHARCOAL, 50F
    );

    public static boolean tryRefuel(PlayerEntity player, ItemStack fuelStack, ItemStack targetStack) {
        if (fuelStack == null || fuelStack.isEmpty() || targetStack == null || targetStack.isEmpty()) {
            return false;
        }

        ChargeData data = targetStack.get(ModComponents.CHARGE_COMPONENT);
        if (data == null || data.charge() >= data.maxCharge()) {
            return false;
        }

        Float fuelAmount = FUELS.get(fuelStack.getItem());
        if (fuelAmount == null) {
            if (fuelStack.isOf(org.technocracy.spacestation.registry.items.MiscItems.SOLID_FUEL)) {
                fuelAmount = 50f;
            } else {
                return false;
            }
        }

        targetStack.set(ModComponents.CHARGE_COMPONENT, data.withCharge(data.charge() + fuelAmount));

        if (!player.getAbilities().creativeMode) {
            if (fuelStack.isOf(net.minecraft.item.Items.LAVA_BUCKET)) {
                player.setStackInHand(Hand.MAIN_HAND, new ItemStack(net.minecraft.item.Items.BUCKET));
            } else {
                fuelStack.decrement(1);
            }
        }
        return true;
    }

    public static int getBarStep(ItemStack stack) {
        ChargeData chargeData = stack.getOrDefault(ModComponents.CHARGE_COMPONENT, new ChargeData(0F, 100F, 1F));
        return Math.round(chargeData.charge() * 13 / chargeData.maxCharge());
    }
}
