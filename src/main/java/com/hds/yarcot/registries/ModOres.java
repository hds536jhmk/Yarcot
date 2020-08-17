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

    public static final RegistryObject<Block> SAPPHIRE_ORE = ModBlocks.registerBlockItem(
            "sapphire_ore", SapphireOre::new
    );

    public static final RegistryObject<Block> RUBY_ORE = ModBlocks.registerBlockItem(
            "ruby_ore", RubyOre::new
    );

    public static final RegistryObject<Block> SULFUR_ORE = ModBlocks.registerBlockItem(
            "sulfur_ore", SulfurOre::new
    );

    public static final RegistryObject<Block> LITHIUM_ORE = ModBlocks.registerBlockItem(
            "lithium_ore", LithiumOre::new
    );

    // ORE GEN CONFIGS

    private static final Biome.Category[] OVERWORLD_ONLY = { Biome.Category.THEEND, Biome.Category.NETHER };

    public static final ModOreGenConfig SAPPHIRE_ORE_GEN = new ModOreGenConfig(
            () -> SAPPHIRE_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            SapphireOre.config.RARITY.get(),
            SapphireOre.config.VEIN_SIZE.get(),
            SapphireOre.config.LOWEST_SPAWN_HEIGHT.get(),
            SapphireOre.config.HIGHEST_SPAWN_HEIGHT.get()
    );

    public static final ModOreGenConfig RUBY_ORE_GEN = new ModOreGenConfig(
            () -> RUBY_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            RubyOre.config.RARITY.get(),
            RubyOre.config.VEIN_SIZE.get(),
            RubyOre.config.LOWEST_SPAWN_HEIGHT.get(),
            RubyOre.config.HIGHEST_SPAWN_HEIGHT.get()
    );

    public static final ModOreGenConfig SULFUR_ORE_GEN = new ModOreGenConfig(
            () -> SULFUR_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            SulfurOre.config.RARITY.get(),
            SulfurOre.config.VEIN_SIZE.get(),
            SulfurOre.config.LOWEST_SPAWN_HEIGHT.get(),
            SulfurOre.config.HIGHEST_SPAWN_HEIGHT.get()
    );

    public static final ModOreGenConfig LITHIUM_ORE_GEN = new ModOreGenConfig(
            () -> LITHIUM_ORE.get(),
            ModOreGenConfig.BIOME_MODE.BLACKLIST,
            null,
            OVERWORLD_ONLY,
            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            LithiumOre.config.RARITY.get(),
            LithiumOre.config.VEIN_SIZE.get(),
            LithiumOre.config.LOWEST_SPAWN_HEIGHT.get(),
            LithiumOre.config.HIGHEST_SPAWN_HEIGHT.get()
    );

    public static void registerAll() {
        ModOreGen.addOre(SAPPHIRE_ORE_GEN);
        ModOreGen.addOre(RUBY_ORE_GEN);
        ModOreGen.addOre(SULFUR_ORE_GEN);
        ModOreGen.addOre(LITHIUM_ORE_GEN);

        ModLog.info("All Ores were queued for registration!");

    }

}
