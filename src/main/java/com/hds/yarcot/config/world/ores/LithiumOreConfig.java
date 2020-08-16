package com.hds.yarcot.config.world.ores;

import net.minecraftforge.common.ForgeConfigSpec;

public class LithiumOreConfig {
    public static ForgeConfigSpec.IntValue rarity;
    public static ForgeConfigSpec.IntValue veinSize;
    public static ForgeConfigSpec.IntValue lowestSpawnHeight;
    public static ForgeConfigSpec.IntValue highestSpawnHeight;

    public static void pushConfig(ForgeConfigSpec.Builder builder) {
        OreConfigBuilder.pushCategory(builder, "lithium_ore");

        rarity = OreConfigBuilder.defineRarity(builder, 20);
        veinSize = OreConfigBuilder.defineVeinSize(builder, 3);
        lowestSpawnHeight = OreConfigBuilder.defineLowestSpawnHeight(builder, 10);
        highestSpawnHeight = OreConfigBuilder.defineHighestSpawnHeight(builder, 30);

        builder.pop();
    }
}
