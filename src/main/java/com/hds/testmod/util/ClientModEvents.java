package com.hds.testmod.util;

import com.hds.testmod.TestMod;
import com.hds.testmod.registries.ModContainers;
import com.hds.testmod.blocks.barrels.sapphire.SapphireBarrelScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


// Telling forge to mark this class as an Event Listener for mod TestMod.MODID
//  If bus is set to MOD it listens to Registry and Mod Loading events
@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(
                ModContainers.SAPPHIRE_BARREL_CONTAINER.get(),
                SapphireBarrelScreen::new
        );

        ModLog.info("Client was set up!");

    }

}
