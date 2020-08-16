package com.hds.yarcot.blocks.conduits.sapphire;

import com.hds.yarcot.blocks.conduits.ModConduitTile;
import com.hds.yarcot.config.blocks.conduits.SapphireConduitConfig;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireConduitTile extends ModConduitTile {
    public SapphireConduitTile() {
        super(
                ModTileEntities.SAPPHIRE_CONDUIT_TILE.get(),
                SapphireConduitConfig.energyInput.get(),
                SapphireConduitConfig.energyOutput.get(),
                SapphireConduitConfig.energyBuffer.get()
        );
    }
}
