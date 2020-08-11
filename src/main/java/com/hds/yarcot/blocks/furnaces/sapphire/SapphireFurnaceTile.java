package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.blocks.furnaces.ModFurnaceTile;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireFurnaceTile extends ModFurnaceTile {
    public SapphireFurnaceTile() {
        super(ModTileEntities.SAPPHIRE_FURNACE_TILE.get(), 200, 2, 1000, 0.5F);
    }
}
