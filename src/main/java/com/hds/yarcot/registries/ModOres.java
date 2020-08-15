package com.hds.yarcot.registries;

import com.hds.yarcot.blocks.SapphireOre;
import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.world.ModOreGen;
import com.hds.yarcot.world.ModOreGenConfig;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.fml.RegistryObject;

public class ModOres {

    public static RegistryObject<Block> SAPPHIRE_ORE = ModBlocks.registerBlockItem(
            "sapphire_ore", SapphireOre::new
    );

    public static void registerAll() {

        ModOreGen.addOre(
                new ModOreGenConfig(
                        () -> SAPPHIRE_ORE.get(),
                        ModOreGenConfig.BIOME_MODE.BLACKLIST,
                        null,
                        new Biome.Category[] { Biome.Category.THEEND, Biome.Category.NETHER },
                        OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                        5,
                        6,
                        5,
                        20
                )
        );

        ModLog.info("All Ores were queued for registration!");

    }

}
