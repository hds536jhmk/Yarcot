package com.hds.yarcot.config.blocks;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModFurnaceConfig extends ModConfigBuilder {

    public final ForgeConfigSpec.IntValue ENERGY_INPUT;
    public final ForgeConfigSpec.IntValue ENERGY_OUTPUT;
    public final ForgeConfigSpec.IntValue CAPACITY;
    public final ForgeConfigSpec.DoubleValue SPEED_FACTOR;

    public ModFurnaceConfig(ForgeConfigSpec.Builder builder, String blockName, int defaultEnergyInput, int defaultEnergyOutput, int defaultCapacity, float defaultSpeedFactor) {
        super(builder, blockName);

        ENERGY_INPUT = builder
                .comment("How much energy the furnace accepts as an input. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultEnergyInput, 0, Integer.MAX_VALUE);
        ENERGY_OUTPUT = builder
                .comment("How much energy the furnace uses every tick. (FE)")
                .worldRestart()
                .defineInRange("energyConsumption", defaultEnergyOutput, 0, Integer.MAX_VALUE);
        CAPACITY = builder
                .comment("How much energy the furnace can store. (FE)")
                .worldRestart()
                .defineInRange("capacity", defaultCapacity, 0, Integer.MAX_VALUE);
        SPEED_FACTOR = builder
                .comment("How fast the furnace smelts. (e.g. 0.5 is double the speed of a standard furnace)")
                .worldRestart()
                .defineInRange("speedFactor", defaultSpeedFactor, 0, Float.MAX_VALUE);

        builder.pop();
    }
}
