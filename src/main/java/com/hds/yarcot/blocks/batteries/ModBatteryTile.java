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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ModBatteryTile extends TileEntity implements ITickableTileEntity {
    private final int MAX_INPUT;
    private final int MAX_OUTPUT;
    private final int CAPACITY;

    private final ModBatteryInventory BATTERY_INVENTORY;
    private final ModEnergyStorage ENERGY_STORAGE;

    private final LazyOptional<ModEnergyStorage> LAZY_ENERGY_STORAGE;

    public ModBatteryTile(TileEntityType<?> tileEntityTypeIn, int input, int output, int capacity) {
        super(tileEntityTypeIn);
        this.MAX_INPUT = input;
        this.MAX_OUTPUT = output;
        this.CAPACITY = capacity;

        this.BATTERY_INVENTORY = new ModBatteryInventory() {
            @Override
            public void markDirty() {
                ModBatteryTile.this.markDirty();
            }
        };
        this.ENERGY_STORAGE = new ModEnergyStorage(this.CAPACITY, this.MAX_INPUT, this.MAX_OUTPUT) {
            @Override
            public void onEnergyChanged(int energyAdded) {
                markDirty();
            }
        };

        this.LAZY_ENERGY_STORAGE = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;

        ItemStack inputSlot = BATTERY_INVENTORY.getStackInSlot(0);
        if (!inputSlot.isEmpty())
            inputSlot.getCapability(CapabilityEnergy.ENERGY).ifPresent(
                    handler -> {
                        ModEnergyStorage.transferEnergy(handler, this.ENERGY_STORAGE, this.MAX_INPUT);
                    }
            );

        ItemStack outputSlot = BATTERY_INVENTORY.getStackInSlot(1);
        if (!outputSlot.isEmpty())
            outputSlot.getCapability(CapabilityEnergy.ENERGY).ifPresent(
                    handler -> {
                        ModEnergyStorage.transferEnergy(this.ENERGY_STORAGE, handler, this.MAX_OUTPUT);
                    }
            );
    }

    public ItemStackHandler getItemStorage() {
        return this.BATTERY_INVENTORY.getItemStackHandler();
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
        BATTERY_INVENTORY.deserializeNBT(compound.getCompound("batteryInventory"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyStorage", ENERGY_STORAGE.serializeNBT());
        compound.put("batteryInventory", BATTERY_INVENTORY.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityEnergy.ENERGY))
            return LAZY_ENERGY_STORAGE.cast();
        else if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
            return LazyOptional.of(() -> new SidedInvWrapper(BATTERY_INVENTORY, side)).cast();
        return super.getCapability(cap, side);
    }
}
