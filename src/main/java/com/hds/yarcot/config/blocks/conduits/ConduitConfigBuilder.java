package com.hds.yarcot.config.blocks.conduits;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConduitConfigBuilder extends ModConfigBuilder {
    public static ForgeConfigSpec.IntValue defineEnergyInput(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the conduit can extract from an adjacent block. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineEnergyOutput(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the conduit can insert into an adjacent block. (FE)")
                .worldRestart()
                .defineInRange("energyOutput", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineEnergyBuffer(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the conduit can hold into its internal buffer. (FE, keep it similar to input/output)")
                .worldRestart()
                .defineInRange("energyBuffer", defaultValue, 0, Integer.MAX_VALUE);
    }
}
