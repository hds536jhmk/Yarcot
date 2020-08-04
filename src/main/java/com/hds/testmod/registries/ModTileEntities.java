package com.hds.testmod.registries;

import com.hds.testmod.blocks.barrels.sapphire.SapphireBarrelTile;
import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {

    public static final RegistryObject<TileEntityType<SapphireBarrelTile>> SAPPHIRE_BARREL_TILE = RegistryHandler.TILE_ENTITIES.register(
            "sapphire_barrel",
            () -> TileEntityType.Builder.create(
                    SapphireBarrelTile::new,
                    ModBlocks.SAPPHIRE_BARREL.get()
            ).build(null)
    );

    public static void registerAll() {

        ModLog.info("All TileEntities were registered!");

    }

}
