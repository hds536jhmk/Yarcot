package com.hds.yarcot.blocks.conduits.sapphire;

import com.hds.yarcot.blocks.conduits.ModConduitTile;
import com.hds.yarcot.registries.ModTileEntities;

public class SapphireConduitTile extends ModConduitTile {
    public SapphireConduitTile() {
        super(
                ModTileEntities.SAPPHIRE_CONDUIT_TILE.get(),
                SapphireConduit.config.ENERGY_INPUT.get(),
                SapphireConduit.config.ENERGY_OUTPUT.get(),
                SapphireConduit.config.ENERGY_BUFFER.get()
        );
    }
}
