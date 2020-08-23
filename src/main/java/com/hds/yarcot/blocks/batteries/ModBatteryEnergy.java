package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.apis.ISidedEnergyStorage;
import com.hds.yarcot.apis.ModBlockStateProperties;
import com.hds.yarcot.apis.ModEnergyStorage;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;

public class ModBatteryEnergy extends ModEnergyStorage implements ISidedEnergyStorage {

    private final ModBatteryTile BATTERY_TILE;

    public ModBatteryEnergy(ModBatteryTile batteryTile, int capacity) {
        this(batteryTile, capacity, 0);
    }

    public ModBatteryEnergy(ModBatteryTile batteryTile, int capacity, int maxTransfer) {
        this(batteryTile, capacity, maxTransfer, maxTransfer);
    }

    public ModBatteryEnergy(ModBatteryTile batteryTile, int capacity, int maxReceive, int maxExtract) {
        this(batteryTile, capacity, maxReceive, maxExtract, 0);
    }

    public ModBatteryEnergy(ModBatteryTile batteryTile, int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
        this.BATTERY_TILE = batteryTile;
    }

    @Override
    public boolean canReceiveFromSide(@Nullable Direction direction) {
        if (direction == null) return false;
        int connectionState = BATTERY_TILE.getBlockState().get(ModBlockStateProperties.CONNECTION.getFromDirection(direction));
        return connectionState == ModBlockStateProperties.CONNECTION_TYPE.ALL.get() ||
                connectionState == ModBlockStateProperties.CONNECTION_TYPE.INPUT.get();
    }

    @Override
    public boolean canExtractFromSide(@Nullable Direction direction) {
        if (direction == null) return false;
        int connectionState = BATTERY_TILE.getBlockState().get(ModBlockStateProperties.CONNECTION.getFromDirection(direction));
        return connectionState == ModBlockStateProperties.CONNECTION_TYPE.ALL.get() ||
                connectionState == ModBlockStateProperties.CONNECTION_TYPE.OUTPUT.get();
    }
}
