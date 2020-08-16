package com.hds.yarcot.config.blocks.miners;

import net.minecraftforge.common.ForgeConfigSpec;

public class SapphireMinerConfig {

    public static ForgeConfigSpec.IntValue energyInput;
    public static ForgeConfigSpec.IntValue moveConsumption;
    public static ForgeConfigSpec.IntValue digConsumption;
    public static ForgeConfigSpec.IntValue capacity;
    public static ForgeConfigSpec.DoubleValue actionTime;

    public static void pushConfig(ForgeConfigSpec.Builder builder) {
        MinerConfigBuilder.pushCategory(builder, "sapphire_miner");

        energyInput = MinerConfigBuilder.defineEnergyInput(builder, 100);
        moveConsumption = MinerConfigBuilder.defineMoveConsumption(builder, 50);
        digConsumption = MinerConfigBuilder.defineDigConsumption(builder, 100);
        capacity = MinerConfigBuilder.defineCapacity(builder, 1000);
        actionTime = MinerConfigBuilder.defineActionTime(builder, 1.0F);

        builder.pop();
    }
}
