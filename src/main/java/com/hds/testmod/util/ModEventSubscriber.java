package com.hds.testmod.util;

import com.hds.testmod.TestMod;
import net.minecraftforge.fml.common.Mod;


// Telling forge to mark this class as an Event Listener for mod TestMod.MODID
//  If bus is set to MOD it listens to Registry and Mod Loading events
@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber { }
