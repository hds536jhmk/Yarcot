package com.hds.yarcot.blocks.batteries.sapphire;

import com.hds.yarcot.blocks.batteries.ModBatteryTile;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireBatteryTile extends ModBatteryTile {
    public SapphireBatteryTile() {
        super(
                ModTileEntities.SAPPHIRE_BATTERY_TILE.get(),
                SapphireBattery.config.energyInput.get(),
                SapphireBattery.config.energyOutput.get(),
                SapphireBattery.config.capacity.get()
        );
    }
}
