package com.hds.yarcot.config.blocks;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModFurnaceConfig extends ModConfigBuilder {

    public ForgeConfigSpec.IntValue energyInput;
    public ForgeConfigSpec.IntValue energyConsumption;
    public ForgeConfigSpec.IntValue capacity;
    public ForgeConfigSpec.DoubleValue speedFactor;

    public ModFurnaceConfig(ForgeConfigSpec.Builder builder, String blockName, int defaultEnergyInput, int defaultEnergyOutput, int defaultCapacity, float defaultSpeedFactor) {
        super(builder, blockName);

        energyInput = builder
                .comment("How much energy the furnace accepts as an input. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultEnergyInput, 0, Integer.MAX_VALUE);
        energyConsumption = builder
                .comment("How much energy the furnace uses every tick. (FE)")
                .worldRestart()
                .defineInRange("energyConsumption", defaultEnergyOutput, 0, Integer.MAX_VALUE);
        capacity = builder
                .comment("How much energy the furnace can store. (FE)")
                .worldRestart()
                .defineInRange("capacity", defaultCapacity, 0, Integer.MAX_VALUE);
        speedFactor = builder
                .comment("How fast the furnace smelts. (e.g. 0.5 is double the speed of a standard furnace)")
                .worldRestart()
                .defineInRange("speedFactor", defaultSpeedFactor, 0, Float.MAX_VALUE);

        builder.pop();
    }
}
