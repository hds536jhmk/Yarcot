package com.hds.yarcot.apis;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.function.BiConsumer;

public class ModSidedEnergyWrapper implements IModEnergyStorage {

    private final ISidedEnergyStorage ENERGY_STORAGE;
    private final Direction SIDE;

    public ModSidedEnergyWrapper(ISidedEnergyStorage energyStorage, Direction side) {
        this.ENERGY_STORAGE = energyStorage;
        this.SIDE = side;
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
        this.ENERGY_STORAGE.onEnergyChanged(energyAdded);
    }

    @Override
    public void setEnergyRaw(int energy) {
        ENERGY_STORAGE.setEnergyRaw(energy);
    }

    @Override
    public int consumeEnergy(int maxExtract, boolean simulate) {
        return ENERGY_STORAGE.consumeEnergy(maxExtract, simulate);
    }

    @Override
    public int transferToNeighbours(World world, BlockPos pos, BiConsumer<IEnergyStorage, Direction> handleEnergyStorage) {
        return ENERGY_STORAGE.transferToNeighbours(world, pos, handleEnergyStorage);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return ENERGY_STORAGE.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        ENERGY_STORAGE.deserializeNBT(nbt);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (this.canReceive())
            return ENERGY_STORAGE.receiveEnergy(maxReceive, simulate);
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (this.canExtract())
            return ENERGY_STORAGE.extractEnergy(maxExtract, simulate);
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return this.ENERGY_STORAGE.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return this.ENERGY_STORAGE.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return this.ENERGY_STORAGE.canExtract() && this.ENERGY_STORAGE.canExtractFromSide(this.SIDE);
    }

    @Override
    public boolean canReceive() {
        return this.ENERGY_STORAGE.canReceive() && this.ENERGY_STORAGE.canReceiveFromSide(this.SIDE);
    }
}
