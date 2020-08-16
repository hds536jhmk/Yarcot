package com.hds.yarcot.blocks.miners.sapphire;

import com.hds.yarcot.blocks.miners.ModMinerTile;
import com.hds.yarcot.config.blocks.miners.SapphireMinerConfig;
import com.hds.yarcot.materials.ModItemTiers;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireMinerTile extends ModMinerTile {
    public SapphireMinerTile() {
        super(
                ModTileEntities.SAPPHIRE_MINER_TILE.get(),
                SapphireMinerConfig.energyInput.get(),
                SapphireMinerConfig.moveConsumption.get(),
                SapphireMinerConfig.digConsumption.get(),
                SapphireMinerConfig.capacity.get(),
                ModItemTiers.SAPPHIRE,
                new Float(SapphireMinerConfig.actionTime.get())
        );
    }
}
