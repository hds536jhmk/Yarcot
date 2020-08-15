package com.hds.yarcot.registries;

import com.hds.yarcot.blocks.LithiumOre;
import com.hds.yarcot.blocks.RubyOre;
import com.hds.yarcot.blocks.SapphireOre;
import com.hds.yarcot.blocks.SulfurOre;
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

    public static RegistryObject<Block> RUBY_ORE = ModBlocks.registerBlockItem(
            "ruby_ore", RubyOre::new
    );

    public static RegistryObject<Block> SULFUR_ORE = ModBlocks.registerBlockItem(
            "sulfur_ore", SulfurOre::new
    );

    public static RegistryObject<Block> LITHIUM_ORE = ModBlocks.registerBlockItem(
            "lithium_ore", LithiumOre::new
    );

    // ORE GEN CONFIGS

    private static final Biome.Category[] OVERWORLD_ONLY = { Biome.Category.THEEND, Biome.Category.NETHER };

    public static ModOreGenConfig SAPPHIRE_ORE_GEN = new ModOreGenConfig(
            () -> SAPPHIRE_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            3,
            5,
            5,
            20
    );

    public static ModOreGenConfig RUBY_ORE_GEN = new ModOreGenConfig(
            () -> RUBY_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            3,
            5,
            5,
            20
    );

    public static ModOreGenConfig SULFUR_ORE_GEN = new ModOreGenConfig(
            () -> SULFUR_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            25,
            3,
            10,
            40
    );

    public static ModOreGenConfig LITHIUM_ORE_GEN = new ModOreGenConfig(
            () -> LITHIUM_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            20,
            3,
            10,
            30
    );

    public static void registerAll() {
        ModOreGen.addOre(SAPPHIRE_ORE_GEN);
        ModOreGen.addOre(RUBY_ORE_GEN);
        ModOreGen.addOre(SULFUR_ORE_GEN);
        ModOreGen.addOre(LITHIUM_ORE_GEN);

        ModLog.info("All Ores were queued for registration!");

    }

}
