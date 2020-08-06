package com.hds.yarcot.registries;

import com.hds.yarcot.blocks.barrels.sapphire.SapphireBarrelContainer;
import com.hds.yarcot.blocks.batteries.sapphire.SapphireBatteryContainer;
import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.util.RegistryHandler;
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
