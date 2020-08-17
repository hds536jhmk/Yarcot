package com.hds.yarcot.config.blocks;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModMinerConfig extends ModConfigBuilder {

    public ForgeConfigSpec.IntValue energyInput;
    public ForgeConfigSpec.IntValue moveConsumption;
    public ForgeConfigSpec.IntValue digConsumption;
    public ForgeConfigSpec.IntValue capacity;
    public ForgeConfigSpec.DoubleValue actionTime;

    public ModMinerConfig(ForgeConfigSpec.Builder builder, String blockName, int defaultEnergyInput, int defaultMoveConsumption, int defaultDigConsumption, int defaultCapacity, float defaultActionTime) {
        super(builder, blockName);

        energyInput = builder
                .comment("How much energy the miner accepts as an input. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultEnergyInput, 0, Integer.MAX_VALUE);
        moveConsumption = builder
                .comment("How much energy the miner uses when moving. (FE)")
                .worldRestart()
                .defineInRange("moveConsumption", defaultMoveConsumption, 0, Integer.MAX_VALUE);
        digConsumption = builder
                .comment("How much energy the miner uses to dig a block. (FE)")
                .worldRestart()
                .defineInRange("digConsumption", defaultDigConsumption, 0, Integer.MAX_VALUE);
        capacity = builder
                .comment("How much energy the miner can store. (FE)")
                .worldRestart()
                .defineInRange("capacity", defaultCapacity, 0, Integer.MAX_VALUE);
        actionTime = builder
                .comment("How much time the miner takes to make a move/action. (seconds)")
                .worldRestart()
                .defineInRange("actionTime", defaultActionTime, 0, Float.MAX_VALUE);

        builder.pop();
    }
}
