package com.hds.yarcot.config.blocks.miners;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class MinerConfigBuilder extends ModConfigBuilder {
    public static ForgeConfigSpec.IntValue defineEnergyInput(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the miner accepts as an input. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineMoveConsumption(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the miner uses when moving. (FE)")
                .worldRestart()
                .defineInRange("moveConsumption", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineDigConsumption(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the miner uses to dig a block. (FE)")
                .worldRestart()
                .defineInRange("digConsumption", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.IntValue defineCapacity(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How much energy the miner can store. (FE)")
                .worldRestart()
                .defineInRange("capacity", defaultValue, 0, Integer.MAX_VALUE);
    }

    public static ForgeConfigSpec.DoubleValue defineActionTime(ForgeConfigSpec.Builder builder, float defaultValue) {
        return builder.comment("How much time the miner takes to make a move/action. (seconds)")
                .worldRestart()
                .defineInRange("actionTime", defaultValue, 0, Float.MAX_VALUE);
    }
}
