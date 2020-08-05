package com.hds.testmod.util;

import com.hds.testmod.TestMod;
import com.hds.testmod.world.ModOreGen;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        ModOreGen.registerOres();

        ModLog.info("All Ores were registered into world gen!");
    }
}
