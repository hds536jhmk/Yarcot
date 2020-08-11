package com.hds.yarcot.blocks.miners.sapphire;

import com.hds.yarcot.blocks.miners.ModMinerTile;
import com.hds.yarcot.materials.ModItemTiers;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireMinerTile extends ModMinerTile {
    public SapphireMinerTile() {
        super(
                ModTileEntities.SAPPHIRE_MINER_TILE.get(),
                100,
                50,
                50,
                1000,
                ModItemTiers.SAPPHIRE,
                1
        );
    }
}
