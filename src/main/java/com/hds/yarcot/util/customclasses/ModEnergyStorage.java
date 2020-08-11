package com.hds.yarcot.util.customclasses;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public abstract class ModEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {

    public ModEnergyStorage(int capacity) {
        this(capacity, 0, 0, 0);
    }

    public ModEnergyStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public abstract void onEnergyChanged(int energyAdded);

    public void setEnergy(int energy) {
        int initialEnergy = this.energy;
        this.energy = Math.max(Math.min(energy, this.capacity), 0);
        onEnergyChanged(this.energy - initialEnergy);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receivedEnergy = super.receiveEnergy(maxReceive, simulate);
        if (!simulate)
            onEnergyChanged(receivedEnergy);
        return receivedEnergy;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractedEnergy = super.extractEnergy(maxExtract, simulate);
        if (!simulate)
            onEnergyChanged(-extractedEnergy);
        return extractedEnergy;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("energy", this.energy);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.energy = nbt.getInt("energy");
    }
}
