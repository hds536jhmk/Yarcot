package com.hds.testmod.container;

import com.hds.testmod.TestMod;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {

    public static RegistryObject<ContainerType<SapphireBarrelContainer>> SAPPHIRE_BARREL_CONTAINER = RegistryHandler.CONTAINERS.register(
            "sapphire_chest",
            () -> IForgeContainerType.create(
                    (windowId, inv, data) -> {
                        return new SapphireBarrelContainer(
                                windowId,
                                inv,
                                data.readBlockPos()
                        );
                    }
            )
    );

    public static void registerAll() {

        TestMod.logInfo("All Containers were registered!");

    }

}
