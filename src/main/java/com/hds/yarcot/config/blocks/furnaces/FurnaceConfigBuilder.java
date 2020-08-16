package com.hds.yarcot.config.blocks.furnaces;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class FurnaceConfigBuilder extends ModConfigBuilder {
    public static ForgeConfigSpec.IntValue defineEnergyInput(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the furnace accepts as an input. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineEnergyConsumption(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the furnace uses every tick. (FE)")
                .worldRestart()
                .defineInRange("energyConsumption", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineCapacity(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the furnace can store. (FE)")
                .worldRestart()
                .defineInRange("capacity", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.DoubleValue defineSpeedFactor(ForgeConfigSpec.Builder builder, float defaultValue) {
        return builder.comment("How fast the furnace smelts. (e.g. 0.5 is double the speed of a standard furnace)")
                .worldRestart()
                .defineInRange("speedFactor", defaultValue, 0, Float.MAX_VALUE);
    }
}
