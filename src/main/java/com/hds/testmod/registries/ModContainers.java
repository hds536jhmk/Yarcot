package com.hds.testmod.registries;

import com.hds.testmod.blocks.barrels.sapphire.SapphireBarrelContainer;
import com.hds.testmod.blocks.batteries.sapphire.SapphireBatteryContainer;
import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {

    public static RegistryObject<ContainerType<SapphireBarrelContainer>> SAPPHIRE_BARREL_CONTAINER = RegistryHandler.CONTAINERS.register(
            "sapphire_barrel",
            () -> IForgeContainerType.create(
                    (windowId, inv, data) -> new SapphireBarrelContainer(windowId, inv, data.readBlockPos())
            )
    );

    public static RegistryObject<ContainerType<SapphireBatteryContainer>> SAPPHIRE_BATTERY_CONTAINER = RegistryHandler.CONTAINERS.register(
            "sapphire_battery",
            () -> IForgeContainerType.create(
                    (windowId, inv, data) -> new SapphireBatteryContainer(windowId, inv, data.readBlockPos())
            )
    );

    public static void registerAll() {

        ModLog.info("All Containers were registered!");

    }

}
