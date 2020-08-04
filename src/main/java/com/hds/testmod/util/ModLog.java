package com.hds.testmod.util;

import com.hds.testmod.TestMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLog {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LOGPREFIX = TestMod.MODID + ": ";
    private static int InfoLogged = 0;
    private static int DebugLogged = 0;

    public static void info(String text) {
        LOGGER.info(LOGPREFIX + text);
        InfoLogged++;
    }

    public static void debug(String text) {
        LOGGER.debug(LOGPREFIX + text);
        DebugLogged++;
    }

    public static void error(String text) {
        LOGGER.error(LOGPREFIX + text);
    }
}
