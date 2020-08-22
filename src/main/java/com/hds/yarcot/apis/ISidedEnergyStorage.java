package com.hds.yarcot.apis;

import net.minecraft.util.Direction;

import javax.annotation.Nullable;

public interface ISidedEnergyStorage extends IModEnergyStorage {

    boolean canReceiveFromSide(@Nullable Direction direction);

    boolean canExtractFromSide(@Nullable Direction direction);

}
