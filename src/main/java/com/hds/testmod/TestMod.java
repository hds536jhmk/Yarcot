package com.hds.testmod;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TestMod.MODID)
public class TestMod
{

    public static final String MODID = "testmod";

    private static final Logger LOGGER = LogManager.getLogger();

    public TestMod() {
        LOGGER.info(MODID + " was Contructed!");
    }

}
