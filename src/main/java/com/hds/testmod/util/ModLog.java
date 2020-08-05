package com.hds.testmod.util;

import com.hds.testmod.TestMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLog {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LOG_PREFIX = TestMod.MOD_ID + ": ";
    private static int InfoLogged = 0;
    private static int DebugLogged = 0;

    public static void info(String text) {
        LOGGER.info(LOG_PREFIX + text);
        InfoLogged++;
    }

    public static void debug(String text) {
        LOGGER.debug(LOG_PREFIX + text);
        DebugLogged++;
    }

    public static void error(String text) {
        LOGGER.error(LOG_PREFIX + text);
    }
}
