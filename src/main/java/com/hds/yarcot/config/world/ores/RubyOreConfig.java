package com.hds.yarcot.config.world.ores;

import net.minecraftforge.common.ForgeConfigSpec;

public class RubyOreConfig {
    public static ForgeConfigSpec.IntValue rarity;
    public static ForgeConfigSpec.IntValue veinSize;
    public static ForgeConfigSpec.IntValue lowestSpawnHeight;
    public static ForgeConfigSpec.IntValue highestSpawnHeight;

    public static void pushConfig(ForgeConfigSpec.Builder builder) {
        OreConfigBuilder.pushCategory(builder, "ruby_ore");

        rarity = OreConfigBuilder.defineRarity(builder, 3);
        veinSize = OreConfigBuilder.defineVeinSize(builder, 5);
        lowestSpawnHeight = OreConfigBuilder.defineLowestSpawnHeight(builder, 5);
        highestSpawnHeight = OreConfigBuilder.defineHighestSpawnHeight(builder, 20);

        builder.pop();
    }
}
