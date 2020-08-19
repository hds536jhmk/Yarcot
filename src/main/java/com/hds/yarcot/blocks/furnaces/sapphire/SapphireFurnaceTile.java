package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.blocks.furnaces.ModFurnaceTile;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireFurnaceTile extends ModFurnaceTile {
    public SapphireFurnaceTile() {
        super(
                ModTileEntities.SAPPHIRE_FURNACE_TILE.get(),
                SapphireFurnace.config.ENERGY_INPUT.get(),
                SapphireFurnace.config.ENERGY_CONSUMPTION.get(),
                SapphireFurnace.config.CAPACITY.get(),
                new Float(SapphireFurnace.config.SPEED_FACTOR.get())
        );
    }
}
