package com.hds.testmod;

import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.RegistryHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MODID)
public class TestMod
{
    public static final String MODID = "testmod";

    public TestMod() {
        RegistryHandler.init();

        ModLog.info("Mod Initialized!");
    }
}
