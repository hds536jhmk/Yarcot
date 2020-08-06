package com.hds.yarcot.util;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.world.ModOreGen;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod.EventBusSubscriber(modid = Yarcot.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        ModOreGen.registerOres();

        ModLog.info("All Ores were registered into world gen!");
    }
}
