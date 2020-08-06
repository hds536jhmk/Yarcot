package com.hds.yarcot.util;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.blocks.batteries.sapphire.SapphireBatteryScreen;
import com.hds.yarcot.registries.ModContainers;
import com.hds.yarcot.blocks.barrels.sapphire.SapphireBarrelScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


// Telling forge to mark this class as an Event Listener for mod Yarcot.MODID
//  If bus is set to MOD it listens to Registry and Mod Loading events
@Mod.EventBusSubscriber(modid = Yarcot.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(
                ModContainers.SAPPHIRE_BARREL_CONTAINER.get(),
                SapphireBarrelScreen::new
        );

        ScreenManager.registerFactory(
                ModContainers.SAPPHIRE_BATTERY_CONTAINER.get(),
                SapphireBatteryScreen::new
        );

        ModLog.info("Client was set up!");

    }

}
