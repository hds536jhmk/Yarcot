package com.hds.yarcot.config.world;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModOreConfig extends ModConfigBuilder {

    public ForgeConfigSpec.IntValue rarity;
    public ForgeConfigSpec.IntValue veinSize;
    public ForgeConfigSpec.IntValue lowestSpawnHeight;
    public ForgeConfigSpec.IntValue highestSpawnHeight;

    public ModOreConfig(ForgeConfigSpec.Builder builder, String blockName, int defaultRarity, int defaultVeinSize, int defaultLowestSpawnHeight, int defaultHighestSpawnHeight) {
        super(builder, blockName);

        rarity = builder
                .comment("How rare the ore is. (Higher is more common)")
                .worldRestart()
                .defineInRange("rarity", defaultRarity, 0, 40);
        veinSize = builder
                .comment("How big a vein is. (How many ore blocks can be found in a vein)")
                .worldRestart()
                .defineInRange("veinSize", defaultVeinSize, 0, 20);
        lowestSpawnHeight = builder
                .comment("At what height the ore starts spawning.")
                .worldRestart()
                .defineInRange("lowestSpawnHeight", defaultLowestSpawnHeight, 0, 255);
        highestSpawnHeight = builder
                .comment("At what height the ore stops spawning.")
                .worldRestart()
                .defineInRange("highestSpawnHeight", defaultHighestSpawnHeight, 0, 255);

        builder.pop();
    }
}
