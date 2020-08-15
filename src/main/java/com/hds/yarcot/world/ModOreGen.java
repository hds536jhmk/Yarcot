package com.hds.yarcot.world;

import com.hds.yarcot.util.ModLog;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class ModOreGen {

    private static final ArrayList<ModOreGenConfig> oresToRegister = new ArrayList<>();

    public static void addOre(ModOreGenConfig ore) {
        oresToRegister.add(ore);
    }

    public static void registerOres() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            for (ModOreGenConfig ore : oresToRegister) {
                if (!ore.isBiomeValid(biome))
                    continue;
                biome.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE
                                .withConfiguration(ore.getOreFeatureConfig())
                                .withPlacement(ore.getPlacementConfig())
                );
            }
        }

        ModLog.info("All Ores were registered into world gen!");
    }
}
