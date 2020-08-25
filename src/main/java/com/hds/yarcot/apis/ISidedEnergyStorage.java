package com.hds.yarcot.apis;

import net.minecraft.util.Direction;

import javax.annotation.Nullable;

public interface ISidedEnergyStorage extends IModEnergyStorage {

    /**
     * Whether or not this storage can receive from the specified direction
     * @param direction The direction to test for
     * @return Whether or not energy can be received
     */
    boolean canReceiveFromSide(@Nullable Direction direction);

    /**
     * Whether or not energy can be extracted from the specified direction
     * @param direction The direction to test for
     * @return Whether or not energy can be extracted
     */
    boolean canExtractFromSide(@Nullable Direction direction);

}
