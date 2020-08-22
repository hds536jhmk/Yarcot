package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.apis.ItemHandlerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ModBatteryInventory extends ItemHandlerInventory implements ISidedInventory, INBTSerializable<CompoundNBT> {
    public ModBatteryInventory() {
        super(null);
        this.itemHandler = new ItemStackHandler(2);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        switch (side) {
            case UP:
                return new int[] { 0 };
            case DOWN:
                return new int[] { 0, 1 };
            default:
                return new int[] { 1 };
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        if (direction == Direction.DOWN)
            return false;

        if (index < 0 || index > 1)
            return false;

        AtomicBoolean canExtract = new AtomicBoolean(false);
        AtomicBoolean canReceive = new AtomicBoolean(false);

        itemStackIn.getCapability(CapabilityEnergy.ENERGY).ifPresent(
                handler -> {
                    canExtract.set(handler.canExtract());
                    canReceive.set(handler.canReceive());
                }
        );

        return (canExtract.get() && canReceive.get()) || (canExtract.get() && index == 0) || (canReceive.get() && index == 1);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction != Direction.DOWN)
            return false;

        if (!stack.getCapability(CapabilityEnergy.ENERGY).isPresent())
            return true;

        AtomicInteger capacity = new AtomicInteger();
        AtomicInteger energyStored = new AtomicInteger();
        stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(
                handler -> {
                    capacity.set(handler.getMaxEnergyStored());
                    energyStored.set(handler.getEnergyStored());
                }
        );

        return (index == 0 && energyStored.get() <= 0) || (index == 1 && energyStored.get() >= capacity.get());
    }

    @Override
    public abstract void markDirty();

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("itemStackHandler", itemHandler.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("itemStackHandler"));
    }
}
