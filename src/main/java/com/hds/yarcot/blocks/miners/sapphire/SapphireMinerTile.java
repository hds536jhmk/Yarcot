package com.hds.yarcot.blocks.miners.sapphire;

import com.hds.yarcot.blocks.miners.ModMinerTile;
import com.hds.yarcot.materials.ModItemTiers;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireMinerTile extends ModMinerTile {
    public SapphireMinerTile() {
        super(
                ModTileEntities.SAPPHIRE_MINER_TILE.get(),
                SapphireMiner.config.ENERGY_INPUT.get(),
                SapphireMiner.config.MOVE_CONSUMPTION.get(),
                SapphireMiner.config.DIG_CONSUMPTION.get(),
                SapphireMiner.config.CAPACITY.get(),
                ModItemTiers.SAPPHIRE,
                new Float(SapphireMiner.config.ACTION_TIME.get())
        );
    }
}
