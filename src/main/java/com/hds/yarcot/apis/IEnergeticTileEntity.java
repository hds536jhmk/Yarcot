package com.hds.yarcot.apis;

import net.minecraftforge.common.extensions.IForgeTileEntity;

public interface IEnergeticTileEntity extends IForgeTileEntity {
    ModEnergyStorage getEnergyStorage();

    default int getEnergyStored() {
        return getEnergyStorage().getEnergyStored();
    }

    default int getEnergyCapacity() {
        return getEnergyStorage().getMaxEnergyStored();
    }

    default float getChargePercentage() {
        return (float) getEnergyStored() / (float) getEnergyCapacity();
    }
}
