package org.technocracy.spacestation.chemistry.sublimator;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.technocracy.spacestation.registry.ModComponents;
import org.technocracy.spacestation.chemistry.ChemContainer;
import org.technocracy.spacestation.chemistry.ChemData;
import org.technocracy.spacestation.chemistry.ModBlockEntities;
import org.technocracy.spacestation.chemistry.ChemRegistry;

import java.util.Map;

public class SublimatorBlockEntity extends BlockEntity implements Inventory, ExtendedScreenHandlerFactory<BlockPos> {
    public static final int PROCESS_TIME = 40;
    public final DefaultedList<ItemStack> slots = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private int processTicker;
    private int processProgress;

    public SublimatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SUBLIMATOR, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (++processTicker < 1) return;
        processTicker = 0;

        ItemStack input = slots.get(0);
        ItemStack output = slots.get(1);
        if (input.isEmpty() || !(input.getItem() instanceof ChemContainer)) {
            resetProgress(world, pos, state);
            return;
        }

        ChemData data = input.get(ModComponents.CHEM_DATA);
        if (data == null) {
            resetProgress(world, pos, state);
            return;
        }

        for (Map.Entry<String, Double> entry : data.chemicals().entrySet()) {
            SublimationRecipe recipe = ChemRegistry.getSublimation(entry.getKey()).orElse(null);
            if (recipe == null || entry.getValue() < recipe.units()) continue;

            Item resultItem = Registries.ITEM.get(recipe.output());
            if (resultItem == null) continue;
            if (!canAddResult(output, resultItem)) {
                resetProgress(world, pos, state);
                return;
            }

            processProgress++;
            if (processProgress < PROCESS_TIME) {
                markDirty();
                syncToClients(world, pos, state);
                return;
            }

            slots.set(0, updatedInput(input, data, entry.getKey(), recipe.units()));
            if (output.isEmpty()) slots.set(1, new ItemStack(resultItem));
            else output.increment(1);
            processProgress = 0;
            markDirty();
            syncToClients(world, pos, state);
            return;
        }

        resetProgress(world, pos, state);
    }

    private void resetProgress(World world, BlockPos pos, BlockState state) {
        if (processProgress != 0) {
            processProgress = 0;
            markDirty();
            syncToClients(world, pos, state);
        }
    }

    public int getProcessProgress() {
        return processProgress;
    }

    private ItemStack updatedInput(ItemStack input, ChemData data, String chemical, double units) {
        ItemStack updated = input.copy();
        updated.set(ModComponents.CHEM_DATA, data.remove(chemical, units));
        return updated;
    }

    private boolean canAddResult(ItemStack output, Item item) {
        return output.isEmpty() || (output.isOf(item) && output.getCount() < output.getMaxCount());
    }

    @Override public int size() { return 2; }
    @Override public boolean isEmpty() { return slots.stream().allMatch(ItemStack::isEmpty); }
    @Override public ItemStack getStack(int slot) { return slots.get(slot); }
    @Override public ItemStack removeStack(int slot, int amount) { return Inventories.splitStack(slots, slot, amount); }
    @Override public ItemStack removeStack(int slot) { return Inventories.removeStack(slots, slot); }
    @Override public void setStack(int slot, ItemStack stack) { slots.set(slot, stack); markDirty(); }
    @Override public void clear() { slots.clear(); markDirty(); }
    @Override public boolean canPlayerUse(PlayerEntity player) { return Inventory.canPlayerUse(this, player); }

    @Override
    protected void writeNbt(net.minecraft.nbt.NbtCompound nbt, net.minecraft.registry.RegistryWrapper.WrapperLookup lookup) {
        super.writeNbt(nbt, lookup);
        Inventories.writeNbt(nbt, slots, lookup);
        nbt.putInt("ProcessProgress", processProgress);
    }

    @Override
    protected void readNbt(net.minecraft.nbt.NbtCompound nbt, net.minecraft.registry.RegistryWrapper.WrapperLookup lookup) {
        super.readNbt(nbt, lookup);
        Inventories.readNbt(nbt, slots, lookup);
        processProgress = nbt.getInt("ProcessProgress");
    }

    void syncToClients(World world, BlockPos pos, BlockState state) { world.updateListeners(pos, state, state, 3); }
    @Nullable @Override public Packet<ClientPlayPacketListener> toUpdatePacket() { return BlockEntityUpdateS2CPacket.create(this); }
    @Override public net.minecraft.nbt.NbtCompound toInitialChunkDataNbt(net.minecraft.registry.RegistryWrapper.WrapperLookup lookup) { return createNbt(lookup); }
    @Override public Text getDisplayName() { return Text.translatable("block.spacestation.sublimator"); }
    @Override public BlockPos getScreenOpeningData(ServerPlayerEntity player) { return pos; }
    @Override public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) { return new SublimatorScreenHandler(syncId, inventory, pos); }
}
