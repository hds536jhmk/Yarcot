package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.util.customclasses.ModEnergyStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ModBatteryTile extends TileEntity {
    private final int MAX_INPUT;
    private final int MAX_OUTPUT;
    private final int CAPACITY;

    private final ModEnergyStorage ENERGY_STORAGE;

    public ModBatteryTile(TileEntityType<?> tileEntityTypeIn, int input, int output, int capacity) {
        super(tileEntityTypeIn);
        this.MAX_INPUT = input;
        this.MAX_OUTPUT = output;
        this.CAPACITY = capacity;
        this.ENERGY_STORAGE = new ModEnergyStorage(this.CAPACITY, this.MAX_INPUT, this.MAX_OUTPUT) {
            @Override
            public void onEnergyChanged(int energyAdded) {
                markDirty();
            }
        };
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
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyStorage", ENERGY_STORAGE.serializeNBT());
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
