package com.hds.yarcot.config.blocks;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModBatteryConfig extends ModConfigBuilder {

    public ForgeConfigSpec.IntValue energyInput;
    public ForgeConfigSpec.IntValue energyOutput;
    public ForgeConfigSpec.IntValue capacity;

    public ModBatteryConfig(ForgeConfigSpec.Builder builder, String blockName, int defaultEnergyInput, int defaultEnergyOutput, int defaultCapacity) {
        super(builder, blockName);

        energyInput = builder
                .comment("How much energy the battery can receive from another block. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultEnergyInput, 0, Integer.MAX_VALUE);
        energyOutput = builder
                .comment("How much energy can be extracted from the battery by another block. (FE)")
                .worldRestart()
                .defineInRange("energyOutput", defaultEnergyOutput, 0, Integer.MAX_VALUE);
        capacity = builder
                .comment("How much energy be stored by the battery. (FE)")
                .worldRestart()
                .defineInRange("capacity", defaultCapacity, 0, Integer.MAX_VALUE);

        builder.pop();
    }
}
