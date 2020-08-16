package com.hds.yarcot.config.blocks.batteries;

import net.minecraftforge.common.ForgeConfigSpec;

public class SapphireBatteryConfig {
    public static ForgeConfigSpec.IntValue energyInput;
    public static ForgeConfigSpec.IntValue energyOutput;
    public static ForgeConfigSpec.IntValue capacity;

    public static void pushConfig(ForgeConfigSpec.Builder builder) {
        BatteryConfigBuilder.pushCategory(builder, "sapphire_battery");

        energyInput = BatteryConfigBuilder.defineEnergyInput(builder, 10);
        energyOutput = BatteryConfigBuilder.defineEnergyOutput(builder, 50);
        capacity = BatteryConfigBuilder.defineCapacity(builder, 1000);

        builder.pop();
    }
}
