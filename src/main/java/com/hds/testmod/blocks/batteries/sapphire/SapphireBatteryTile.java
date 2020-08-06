package com.hds.testmod.blocks.batteries.sapphire;

import com.hds.testmod.blocks.batteries.ModBatteryTile;
import com.hds.testmod.registries.ModTileEntities;

public class SapphireBatteryTile extends ModBatteryTile {
    public SapphireBatteryTile() {
        super(ModTileEntities.SAPPHIRE_BATTERY_TILE.get(), 10, 50, 5000);
    }
}
