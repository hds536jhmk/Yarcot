package com.hds.yarcot.world;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModOreGenConfig {
    public enum BIOME_MODE {
        WHITELIST,
        BLACKLIST,
        NONE,
        ALL
    }

    private final OreFeatureConfig.FillerBlockType fillerBlock;
    private final Supplier<Block> ore;
    private final int veinSize;
    private final ConfiguredPlacement placementConfig;

    private final BIOME_MODE biomeMode;
    private final Biome.Category[] whitelist;
    private final Biome.Category[] blacklist;

    public ModOreGenConfig(Supplier<Block> ore, @Nullable BIOME_MODE biomeMode, @Nullable Biome.Category[] biomeWhitelist, @Nullable Biome.Category[] biomeBlacklist, OreFeatureConfig.FillerBlockType fillerBlock, int rarity, int veinSize, int lowestSpawnHeight, int highestSpawnHeight) {
        this.fillerBlock = fillerBlock;
        this.ore = ore;
        this.veinSize = veinSize;
        placementConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(rarity, lowestSpawnHeight, 0, highestSpawnHeight));

        this.biomeMode = biomeMode == null ? BIOME_MODE.ALL : biomeMode;
        this.whitelist = biomeWhitelist == null ? new Biome.Category[0] : biomeWhitelist;
        this.blacklist = biomeBlacklist == null ? new Biome.Category[0] : biomeBlacklist;
    }

    public boolean isBiomeValid(Biome biome) {
        switch (biomeMode) {
            case ALL:
                return true;

            case WHITELIST:
                for (Biome.Category category : whitelist) {
                    if (biome.getCategory().equals(category))
                        return true;
                }
                return false;

            case BLACKLIST:
                for (Biome.Category category : blacklist) {
                    if (biome.getCategory().equals(category))
                        return false;
                }
                return true;

            default:
                return false;
        }
    }

    public OreFeatureConfig getOreFeatureConfig() {
        return new OreFeatureConfig(fillerBlock, ore.get().getDefaultState(), veinSize);
    }

    public ConfiguredPlacement getPlacementConfig() {
        return placementConfig;
    }
}
