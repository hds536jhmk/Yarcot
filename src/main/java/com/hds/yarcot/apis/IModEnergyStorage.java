package com.hds.yarcot.apis;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;

public interface IModEnergyStorage extends IEnergyStorage, INBTSerializable<CompoundNBT> {

    void onEnergyChanged(int energyAdded);

    void setEnergyRaw(int energy);

    int consumeEnergy(int maxExtract, boolean simulate);
}
