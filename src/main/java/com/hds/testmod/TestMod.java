package com.hds.testmod;

import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.RegistryHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MOD_ID)
public class TestMod
{
    public static final String MOD_ID = "testmod";

    public TestMod() {
        RegistryHandler.init();

        ModLog.info("Mod Initialized!");
    }
}
