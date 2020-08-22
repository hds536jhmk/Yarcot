package com.hds.yarcot.apis;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;

public class ModSidedEnergyWrapper implements IModEnergyStorage {

    private ISidedEnergyStorage energyStorage;
    private Direction side;

    public ModSidedEnergyWrapper(ISidedEnergyStorage energyStorage, Direction side) {
        this.energyStorage = energyStorage;
        this.side = side;
    }

    public static LazyOptional<IModEnergyStorage>[] create(ISidedEnergyStorage energyStorage, Direction... sides) {
        LazyOptional<IModEnergyStorage>[] wrappers = new LazyOptional[sides.length];
        for (int i = 0; i < sides.length; i++) {
            final Direction side = sides[i];
            wrappers[i] = LazyOptional.of(() -> new ModSidedEnergyWrapper(energyStorage, side));
        }
        return wrappers;
    }

    @Override
    public void onEnergyChanged(int energyAdded) {
        this.energyStorage.onEnergyChanged(energyAdded);
    }

    @Override
    public void setEnergyRaw(int energy) {
        energyStorage.setEnergyRaw(energy);
    }

    @Override
    public int consumeEnergy(int maxExtract, boolean simulate) {
        return energyStorage.consumeEnergy(maxExtract, simulate);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return energyStorage.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        energyStorage.deserializeNBT(nbt);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (this.canReceive())
            return energyStorage.receiveEnergy(maxReceive, simulate);
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (this.canExtract())
            return energyStorage.extractEnergy(maxExtract, simulate);
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return this.energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return this.energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return this.energyStorage.canExtract() && this.energyStorage.canExtractFromSide(this.side);
    }

    @Override
    public boolean canReceive() {
        return this.energyStorage.canReceive() && this.energyStorage.canReceiveFromSide(this.side);
    }
}
