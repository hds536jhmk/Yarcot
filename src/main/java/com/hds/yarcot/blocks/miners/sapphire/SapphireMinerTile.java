package com.hds.yarcot.blocks.miners.sapphire;

import com.hds.yarcot.blocks.miners.ModMinerTile;
import com.hds.yarcot.materials.ModItemTiers;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireMinerTile extends ModMinerTile {
    public SapphireMinerTile() {
        super(
                ModTileEntities.SAPPHIRE_MINER_TILE.get(),
                SapphireMiner.config.energyInput.get(),
                SapphireMiner.config.moveConsumption.get(),
                SapphireMiner.config.digConsumption.get(),
                SapphireMiner.config.capacity.get(),
                ModItemTiers.SAPPHIRE,
                new Float(SapphireMiner.config.actionTime.get())
        );
    }
}
