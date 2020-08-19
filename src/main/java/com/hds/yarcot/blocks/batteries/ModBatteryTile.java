package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.util.customclasses.ModEnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ModBatteryTile extends TileEntity implements ITickableTileEntity {
    private final int MAX_INPUT;
    private final int MAX_OUTPUT;
    private final int CAPACITY;

    private final ItemStackHandler ITEM_STORAGE;

    private final ModEnergyStorage ENERGY_STORAGE;

    public ModBatteryTile(TileEntityType<?> tileEntityTypeIn, int input, int output, int capacity) {
        super(tileEntityTypeIn);
        this.MAX_INPUT = input;
        this.MAX_OUTPUT = output;
        this.CAPACITY = capacity;
        this.ITEM_STORAGE = new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
        this.ENERGY_STORAGE = new ModEnergyStorage(this.CAPACITY, this.MAX_INPUT, this.MAX_OUTPUT) {
            @Override
            public void onEnergyChanged(int energyAdded) {
                markDirty();
            }
        };
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;

        ItemStack inputSlot = ITEM_STORAGE.getStackInSlot(0);
        if (!inputSlot.isEmpty())
            inputSlot.getCapability(CapabilityEnergy.ENERGY).ifPresent(
                    handler -> {
                        ModEnergyStorage.transferEnergy(handler, this.ENERGY_STORAGE, this.MAX_INPUT);
                    }
            );

        ItemStack outputSlot = ITEM_STORAGE.getStackInSlot(1);
        if (!outputSlot.isEmpty())
            outputSlot.getCapability(CapabilityEnergy.ENERGY).ifPresent(
                    handler -> {
                        ModEnergyStorage.transferEnergy(this.ENERGY_STORAGE, handler, this.MAX_OUTPUT);
                    }
            );
    }

    public ItemStackHandler getItemStorage() {
        return this.ITEM_STORAGE;
    }

    public float getChargePercentage() {
        return (float)ENERGY_STORAGE.getEnergyStored() / (float)this.CAPACITY;
    }

    public int getCharge() {
        return ENERGY_STORAGE.getEnergyStored();
    }

    public int getCapacity() {
        return this.CAPACITY;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        ENERGY_STORAGE.deserializeNBT(compound.getCompound("energyStorage"));
        ITEM_STORAGE.deserializeNBT(compound.getCompound("itemStackHandler"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyStorage", ENERGY_STORAGE.serializeNBT());
        compound.put("itemStackHandler", ITEM_STORAGE.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityEnergy.ENERGY)) {
            return LazyOptional.of(() -> ENERGY_STORAGE).cast();
        }
        return super.getCapability(cap, side);
    }
}
