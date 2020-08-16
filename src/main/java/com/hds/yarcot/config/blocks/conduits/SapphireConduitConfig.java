package com.hds.yarcot.config.blocks.conduits;

import net.minecraftforge.common.ForgeConfigSpec;

public class SapphireConduitConfig {
    public static ForgeConfigSpec.IntValue energyInput;
    public static ForgeConfigSpec.IntValue energyOutput;
    public static ForgeConfigSpec.IntValue energyBuffer;

    public static void pushConfig(ForgeConfigSpec.Builder builder) {
        ConduitConfigBuilder.pushCategory(builder, "sapphire_conduit");

        energyInput = ConduitConfigBuilder.defineEnergyInput(builder, 100);
        energyOutput = ConduitConfigBuilder.defineEnergyOutput(builder, 100);
        energyBuffer = ConduitConfigBuilder.defineEnergyBuffer(builder, 200);

        builder.pop();
    }
}
