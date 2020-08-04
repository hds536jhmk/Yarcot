package com.hds.testmod.registries;

import com.hds.testmod.blocks.SapphireOre;
import com.hds.testmod.util.ModLog;
import com.hds.testmod.world.ModOreGen;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.fml.RegistryObject;

public class ModOres {

    public static RegistryObject<Block> SAPPHIRE_ORE = ModBlocks.registerBlockItem(
            "sapphire_ore", () -> new SapphireOre()
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
