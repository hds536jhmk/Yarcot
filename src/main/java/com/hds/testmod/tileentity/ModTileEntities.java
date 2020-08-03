package com.hds.testmod.tileentity;

import com.hds.testmod.TestMod;
import com.hds.testmod.block.ModBlocks;
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

        TestMod.logInfo("All TileEntities were registered!");

    }

}
