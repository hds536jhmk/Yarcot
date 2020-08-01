package com.hds.testmod;

import com.hds.testmod.blocks.ModBlocks;
import com.hds.testmod.util.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.ModDirTransformerDiscoverer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TestMod.MODID)
public class TestMod
{

    public static final String MODID = "testmod";
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOGINTRO = MODID + ": ";

    private static int InfoLogged = 0;
    private static int DebugLogged = 0;

    public TestMod() {
        RegistryHandler.init();

        logInfo("Mod Initialized!");
    }

    public static void logInfo(String text) {
        LOGGER.info(LOGINTRO + text);
        InfoLogged++;
    }

    public static void logDebug(String text) {
        LOGGER.debug(LOGINTRO + text);
        DebugLogged++;
    }

}
