package com.hds.yarcot.registries;

import com.hds.yarcot.blocks.ores.LithiumOre;
import com.hds.yarcot.blocks.ores.RubyOre;
import com.hds.yarcot.blocks.ores.SapphireOre;
import com.hds.yarcot.blocks.ores.SulfurOre;
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
            SapphireOre.config.rarity.get(),
            SapphireOre.config.veinSize.get(),
            SapphireOre.config.lowestSpawnHeight.get(),
            SapphireOre.config.highestSpawnHeight.get()
    );

    public static ModOreGenConfig RUBY_ORE_GEN = new ModOreGenConfig(
            () -> RUBY_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            RubyOre.config.rarity.get(),
            RubyOre.config.veinSize.get(),
            RubyOre.config.lowestSpawnHeight.get(),
            RubyOre.config.highestSpawnHeight.get()
    );

    public static ModOreGenConfig SULFUR_ORE_GEN = new ModOreGenConfig(
            () -> SULFUR_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            SulfurOre.config.rarity.get(),
            SulfurOre.config.veinSize.get(),
            SulfurOre.config.lowestSpawnHeight.get(),
            SulfurOre.config.highestSpawnHeight.get()
    );

    public static ModOreGenConfig LITHIUM_ORE_GEN = new ModOreGenConfig(
            () -> LITHIUM_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            LithiumOre.config.rarity.get(),
            LithiumOre.config.veinSize.get(),
            LithiumOre.config.lowestSpawnHeight.get(),
            LithiumOre.config.highestSpawnHeight.get()
    );

    public static void registerAll() {
        ModOreGen.addOre(SAPPHIRE_ORE_GEN);
        ModOreGen.addOre(RUBY_ORE_GEN);
        ModOreGen.addOre(SULFUR_ORE_GEN);
        ModOreGen.addOre(LITHIUM_ORE_GEN);

        ModLog.info("All Ores were queued for registration!");

    }

}
