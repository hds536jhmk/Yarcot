package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.blocks.furnaces.ModFurnaceTile;
import com.hds.yarcot.config.blocks.furnaces.SapphireFurnaceConfig;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireFurnaceTile extends ModFurnaceTile {
    public SapphireFurnaceTile() {
        super(
                ModTileEntities.SAPPHIRE_FURNACE_TILE.get(),
                SapphireFurnaceConfig.energyInput.get(),
                SapphireFurnaceConfig.energyConsumption.get(),
                SapphireFurnaceConfig.capacity.get(),
                new Float(SapphireFurnaceConfig.speedFactor.get())
        );
    }
}
