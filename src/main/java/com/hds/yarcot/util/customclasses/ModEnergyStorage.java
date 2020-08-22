package com.hds.yarcot.util.customclasses;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.IEnergyStorage;

public class ModEnergyStorage implements IModEnergyStorage {

    private int energy;
    private int capacity;
    protected int maxReceive;
    protected int maxExtract;

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
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0 , Math.min(capacity, energy));
    }

    public void onEnergyChanged(int energyAdded) {}

    public void setEnergyRaw(int energy) {
        this.energy = Math.max(Math.min(energy, this.getMaxEnergyStored()), 0);
    }

    public void setEnergy(int energy) {
        int initialEnergy = this.getEnergyStored();
        this.setEnergyRaw(energy);
        onEnergyChanged(this.getEnergyStored() - initialEnergy);
    }

    public int consumeEnergy(int maxExtract, boolean simulate) {
        int extractedEnergy = Math.max(0, Math.min(maxExtract, this.energy));
        if (!simulate) {
            this.energy -= extractedEnergy;
            onEnergyChanged(-extractedEnergy);
        }
        return extractedEnergy;
    }

    public static int transferEnergy(IEnergyStorage from, IEnergyStorage to, int energy) {
        int extractableEnergy = from.extractEnergy(energy, true);
        int energyExtracted = to.receiveEnergy(extractableEnergy, false);
        from.extractEnergy(energyExtracted, false);
        return energyExtracted;
    }

    public static void copyEnergy(IEnergyStorage from, IEnergyStorage to) {
        ((ModEnergyStorage) to).setEnergy(from.getEnergyStored());
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = 0;

        if (canReceive())
            energyReceived = Math.min(this.getMaxEnergyStored() - this.getEnergyStored(), Math.min(this.maxReceive, maxReceive));

        if (!simulate)
            this.setEnergy(this.getEnergyStored() + energyReceived);

        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = 0;

        if (canExtract())
            energyExtracted = Math.min(this.getEnergyStored(), Math.min(this.maxExtract, maxExtract));

        if (!simulate)
            this.setEnergy(this.getEnergyStored() - energyExtracted);

        return energyExtracted;
    }

    @Override
    public int getEnergyStored()
    {
        return this.energy;
    }

    @Override
    public int getMaxEnergyStored()
    {
        return this.capacity;
    }

    @Override
    public boolean canExtract()
    {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive()
    {
        return this.maxReceive > 0;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("energy", this.getEnergyStored());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.setEnergy(nbt.getInt("energy"));
    }
}
