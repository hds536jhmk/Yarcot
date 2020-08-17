package com.hds.yarcot.config;

import com.hds.yarcot.util.StringUtilities;
import net.minecraftforge.common.ForgeConfigSpec;

public abstract class ModConfigBuilder {
    public ModConfigBuilder(ForgeConfigSpec.Builder builder, String blockName) {
        ModConfigBuilder.pushCategory(builder, blockName);
    }

    public static void pushCategory(ForgeConfigSpec.Builder builder, String blockName) {
        builder.comment(StringUtilities.getBlockName(blockName) + "'s configuration.")
                .push(blockName);
    }
}
