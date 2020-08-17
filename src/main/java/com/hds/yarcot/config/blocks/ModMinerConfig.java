package com.hds.yarcot.config.blocks;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModMinerConfig extends ModConfigBuilder {

    public final ForgeConfigSpec.IntValue ENERGY_INPUT;
    public final ForgeConfigSpec.IntValue MOVE_CONSUMPTION;
    public final ForgeConfigSpec.IntValue DIG_CONSUMPTION;
    public final ForgeConfigSpec.IntValue CAPACITY;
    public final ForgeConfigSpec.DoubleValue ACTION_TIME;

    public ModMinerConfig(ForgeConfigSpec.Builder builder, String blockName, int defaultEnergyInput, int defaultMoveConsumption, int defaultDigConsumption, int defaultCapacity, float defaultActionTime) {
        super(builder, blockName);

        ENERGY_INPUT = builder
                .comment("How much energy the miner accepts as an input. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultEnergyInput, 0, Integer.MAX_VALUE);
        MOVE_CONSUMPTION = builder
                .comment("How much energy the miner uses when moving. (FE)")
                .worldRestart()
                .defineInRange("moveConsumption", defaultMoveConsumption, 0, Integer.MAX_VALUE);
        DIG_CONSUMPTION = builder
                .comment("How much energy the miner uses to dig a block. (FE)")
                .worldRestart()
                .defineInRange("digConsumption", defaultDigConsumption, 0, Integer.MAX_VALUE);
        CAPACITY = builder
                .comment("How much energy the miner can store. (FE)")
                .worldRestart()
                .defineInRange("capacity", defaultCapacity, 0, Integer.MAX_VALUE);
        ACTION_TIME = builder
                .comment("How much time the miner takes to make a move/action. (seconds)")
                .worldRestart()
                .defineInRange("actionTime", defaultActionTime, 0, Float.MAX_VALUE);

        builder.pop();
    }
}
