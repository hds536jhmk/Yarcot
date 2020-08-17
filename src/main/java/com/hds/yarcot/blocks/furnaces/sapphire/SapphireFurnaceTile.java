package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.blocks.furnaces.ModFurnaceTile;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireFurnaceTile extends ModFurnaceTile {
    public SapphireFurnaceTile() {
        super(
                ModTileEntities.SAPPHIRE_FURNACE_TILE.get(),
                SapphireFurnace.config.energyInput.get(),
                SapphireFurnace.config.energyConsumption.get(),
                SapphireFurnace.config.capacity.get(),
                new Float(SapphireFurnace.config.speedFactor.get())
        );
    }
}
