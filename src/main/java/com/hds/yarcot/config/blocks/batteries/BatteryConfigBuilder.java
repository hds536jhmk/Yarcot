package com.hds.yarcot.config.blocks.batteries;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class BatteryConfigBuilder extends ModConfigBuilder {
    public static ForgeConfigSpec.IntValue defineEnergyInput(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the battery can receive from another block. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineEnergyOutput(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy can be extracted from the battery by another block. (FE)")
                .worldRestart()
                .defineInRange("energyOutput", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineCapacity(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy be stored by the battery. (FE)")
                .worldRestart()
                .defineInRange("capacity", defaultValue, 0, Integer.MAX_VALUE);
    }
}
