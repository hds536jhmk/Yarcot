package com.hds.yarcot.config.world;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModOreConfig extends ModConfigBuilder {

    public final ForgeConfigSpec.IntValue RARITY;
    public final ForgeConfigSpec.IntValue VEIN_SIZE;
    public final ForgeConfigSpec.IntValue LOWEST_SPAWN_HEIGHT;
    public final ForgeConfigSpec.IntValue HIGHEST_SPAWN_HEIGHT;

    public ModOreConfig(ForgeConfigSpec.Builder builder, String blockName, int defaultRarity, int defaultVeinSize, int defaultLowestSpawnHeight, int defaultHighestSpawnHeight) {
        super(builder, blockName);

        RARITY = builder
                .comment("How rare the ore is. (Higher is more common)")
                .worldRestart()
                .defineInRange("rarity", defaultRarity, 0, 40);
        VEIN_SIZE = builder
                .comment("How big a vein is. (How many ore blocks can be found in a vein)")
                .worldRestart()
                .defineInRange("veinSize", defaultVeinSize, 0, 20);
        LOWEST_SPAWN_HEIGHT = builder
                .comment("At what height the ore starts spawning.")
                .worldRestart()
                .defineInRange("lowestSpawnHeight", defaultLowestSpawnHeight, 0, 255);
        HIGHEST_SPAWN_HEIGHT = builder
                .comment("At what height the ore stops spawning.")
                .worldRestart()
                .defineInRange("highestSpawnHeight", defaultHighestSpawnHeight, 0, 255);

        builder.pop();
    }
}
