package com.hds.yarcot.config.blocks;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModConduitConfig extends ModConfigBuilder {

    public ForgeConfigSpec.IntValue energyInput;
    public ForgeConfigSpec.IntValue energyOutput;
    public ForgeConfigSpec.IntValue energyBuffer;

    public ModConduitConfig(ForgeConfigSpec.Builder builder, String blockName, int defaultEnergyInput, int defaultEnergyOutput, int defaultEnergyBuffer) {
        super(builder, blockName);

        energyInput = builder
                .comment("How much energy the conduit can extract from an adjacent block. (FE)")
                .worldRestart()
                .defineInRange("energyInput", defaultEnergyInput, 0, Integer.MAX_VALUE);;
        energyOutput = builder
                .comment("How much energy the conduit can insert into an adjacent block. (FE)")
                .worldRestart()
                .defineInRange("energyOutput", defaultEnergyOutput, 0, Integer.MAX_VALUE);
        energyBuffer = builder
                .comment("How much energy the conduit can hold into its internal buffer. (FE, keep it similar to input/output)")
                .worldRestart()
                .defineInRange("energyBuffer", defaultEnergyBuffer, 0, Integer.MAX_VALUE);

        builder.pop();
    }
}
