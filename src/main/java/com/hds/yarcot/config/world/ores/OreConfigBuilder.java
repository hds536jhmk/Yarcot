package com.hds.yarcot.config.world.ores;

import com.hds.yarcot.config.ModConfigBuilder;
import net.minecraftforge.common.ForgeConfigSpec;

public class OreConfigBuilder extends ModConfigBuilder {
    public static ForgeConfigSpec.IntValue defineRarity(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How rare the ore is. (Higher is more common)")
                .worldRestart()
                .defineInRange("rarity", defaultValue, 0, 40);
    }

    public static ForgeConfigSpec.IntValue defineVeinSize(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("How big a vein is. (How many ore blocks can be found in a vein)")
                .worldRestart()
                .defineInRange("veinSize", defaultValue, 0, 20);
    }

    public static ForgeConfigSpec.IntValue defineLowestSpawnHeight(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("At what height the ore starts spawning.")
                .worldRestart()
                .defineInRange("lowestSpawnHeight", defaultValue, 0, 255);
    }

    public static ForgeConfigSpec.IntValue defineHighestSpawnHeight(ForgeConfigSpec.Builder builder, int defaultValue) {
        return builder.comment("At what height the ore stops spawning.")
                .worldRestart()
                .defineInRange("highestSpawnHeight", defaultValue, 0, 255);
    }
}
