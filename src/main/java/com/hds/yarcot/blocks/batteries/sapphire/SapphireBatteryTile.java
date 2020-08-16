package com.hds.yarcot.blocks.batteries.sapphire;

import com.hds.yarcot.blocks.batteries.ModBatteryTile;
import com.hds.yarcot.config.blocks.batteries.SapphireBatteryConfig;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireBatteryTile extends ModBatteryTile {
    public SapphireBatteryTile() {
        super(
                ModTileEntities.SAPPHIRE_BATTERY_TILE.get(),
                SapphireBatteryConfig.energyInput.get(),
                SapphireBatteryConfig.energyOutput.get(),
                SapphireBatteryConfig.capacity.get()
        );
    }
}
