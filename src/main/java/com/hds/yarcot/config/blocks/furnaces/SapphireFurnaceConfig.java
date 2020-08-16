package com.hds.yarcot.config.blocks.furnaces;

import net.minecraftforge.common.ForgeConfigSpec;

public class SapphireFurnaceConfig {
    public static ForgeConfigSpec.IntValue energyInput;
    public static ForgeConfigSpec.IntValue energyConsumption;
    public static ForgeConfigSpec.IntValue capacity;
    public static ForgeConfigSpec.DoubleValue speedFactor;

    public static void pushConfig(ForgeConfigSpec.Builder builder) {
        FurnaceConfigBuilder.pushCategory(builder, "sapphire_furnace");

        energyInput = FurnaceConfigBuilder.defineEnergyInput(builder, 200);
        energyConsumption = FurnaceConfigBuilder.defineEnergyConsumption(builder, 2);
        capacity = FurnaceConfigBuilder.defineCapacity(builder, 1000);
        speedFactor = FurnaceConfigBuilder.defineSpeedFactor(builder, 0.5F);

        builder.pop();
    }
}
