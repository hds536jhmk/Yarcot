package com.hds.yarcot.registries;

import com.hds.yarcot.blocks.SapphireOre;
import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.world.ModOreGen;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.fml.RegistryObject;

public class ModOres {

    public static RegistryObject<Block> SAPPHIRE_ORE = ModBlocks.registerBlockItem(
            "sapphire_ore", SapphireOre::new
    );

    public static void registerAll() {

        ModOreGen.addOre(
                new ModOreGen.OreGenConfig(
                        () -> SAPPHIRE_ORE.get(),
                        OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                        5,
                        6,
                        5,
                        20
                )
        );

        ModLog.info("All Ores were registered!");

    }

}
