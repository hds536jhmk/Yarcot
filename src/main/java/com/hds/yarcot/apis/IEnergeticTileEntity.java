package com.hds.yarcot.apis;

import net.minecraftforge.common.extensions.IForgeTileEntity;

public interface IEnergeticTileEntity extends IForgeTileEntity {
    /**
     * @return This TileEntity's energy storage
     */
    ModEnergyStorage getEnergyStorage();

    /**
     * @return How much energy is stored
     */
    default int getEnergyStored() {
        return getEnergyStorage().getEnergyStored();
    }

    /**
     * @return How much energy can be stored
     */
    default int getEnergyCapacity() {
        return getEnergyStorage().getMaxEnergyStored();
    }

    /**
     * @return Returns the charge percentage
     */
    default float getChargePercentage() {
        return (float) getEnergyStored() / (float) getEnergyCapacity();
    }
}
