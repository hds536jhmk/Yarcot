package com.hds.testmod.tileentity;

import com.hds.testmod.TestMod;
import com.hds.testmod.block.ModBlocks;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {

    public static final RegistryObject<TileEntityType<SapphireChestTile>> SAPPHIRE_CHEST_TILE = RegistryHandler.TILEENTITIES.register(
            "sapphire_chest",
            () -> TileEntityType.Builder.create(
                    SapphireChestTile::new,
                    ModBlocks.SAPPHIRE_CHEST.get()
            ).build(null)
    );

    public static void registerAll() {

        TestMod.logInfo("All TileEntities were registered!");

    }

}
