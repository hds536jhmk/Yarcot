package com.hds.yarcot;

import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.util.RegistryHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(Yarcot.MOD_ID)
public class Yarcot
{
    public static final String MOD_ID = "yarcot";

    public Yarcot() {
        RegistryHandler.init();

        ModLog.info("Mod Initialized!");
    }
}
