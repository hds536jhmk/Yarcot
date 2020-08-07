package com.hds.yarcot.registries;

import com.hds.yarcot.blocks.barrels.sapphire.SapphireBarrelTile;
import com.hds.yarcot.blocks.batteries.sapphire.SapphireBatteryTile;
import com.hds.yarcot.blocks.conduits.sapphire.SapphireConduitTile;
import com.hds.yarcot.blocks.miners.sapphire.SapphireMinerTile;
import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.util.RegistryHandler;
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

    public static final RegistryObject<TileEntityType<SapphireBatteryTile>> SAPPHIRE_BATTERY_TILE = RegistryHandler.TILE_ENTITIES.register(
            "sapphire_battery",
            () -> TileEntityType.Builder.create(
                    SapphireBatteryTile::new,
                    ModBlocks.SAPPHIRE_BATTERY.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<SapphireConduitTile>> SAPPHIRE_CONDUIT_TILE = RegistryHandler.TILE_ENTITIES.register(
            "sapphire_conduit",
            () -> TileEntityType.Builder.create(
                    SapphireConduitTile::new,
                    ModBlocks.SAPPHIRE_CONDUIT.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<SapphireMinerTile>> SAPPHIRE_MINER_TILE = RegistryHandler.TILE_ENTITIES.register(
            "sapphire_furnace",
            () -> TileEntityType.Builder.create(
                    SapphireMinerTile::new,
                    ModBlocks.SAPPHIRE_MINER.get()
            ).build(null)
    );

    public static void registerAll() {

        ModLog.info("All TileEntities were registered!");

    }

}
