package com.hds.yarcot.config;

import com.hds.yarcot.util.StringUtilities;
import net.minecraftforge.common.ForgeConfigSpec;

public abstract class ModConfigBuilder {
    public static void pushCategory(ForgeConfigSpec.Builder builder, String blockId) {
        builder.comment(StringUtilities.getBlockName(blockId) + "'s configuration.")
                .push(blockId);
    }
}
