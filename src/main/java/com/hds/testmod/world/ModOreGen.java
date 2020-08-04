package com.hds.testmod.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModOreGen {

    public static class OreGenConfig {
        private OreFeatureConfig.FillerBlockType fillerBlock;
        private Supplier<Block> ore;
        private int veinSize;
        private ConfiguredPlacement placementConfig;

        public OreGenConfig(Supplier<Block> ore, OreFeatureConfig.FillerBlockType fillerBlock, int rarity, int veinSize, int lowestSpawnHeight, int highestSpawnHeight) {
            this.fillerBlock = fillerBlock;
            this.ore = ore;
            this.veinSize = veinSize;
            placementConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(rarity, lowestSpawnHeight, 0, highestSpawnHeight));
        }

        public OreFeatureConfig getOreFeatureConfig() {
            return new OreFeatureConfig(fillerBlock, ore.get().getDefaultState(), veinSize);
        }

        public ConfiguredPlacement getPlacementConfig() {
            return placementConfig;
        }
    }

    private static final ArrayList<OreGenConfig> oresToRegister = new ArrayList<OreGenConfig>();

    public static void addOre(OreGenConfig ore) {
        oresToRegister.add(ore);
    }

    public static void registerOres() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            for (OreGenConfig ore : oresToRegister) {
                biome.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE
                                .withConfiguration(ore.getOreFeatureConfig())
                                .withPlacement(ore.getPlacementConfig())
                );
            }
        }
    }
}
