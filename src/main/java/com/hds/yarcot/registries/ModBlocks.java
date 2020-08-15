package com.hds.yarcot.registries;

import com.hds.yarcot.blocks.BaseBlock;
import com.hds.yarcot.blocks.RubyBlock;
import com.hds.yarcot.blocks.barrels.sapphire.SapphireBarrel;
import com.hds.yarcot.blocks.SapphireBlock;
import com.hds.yarcot.blocks.batteries.sapphire.SapphireBattery;
import com.hds.yarcot.blocks.conduits.sapphire.SapphireConduit;
import com.hds.yarcot.blocks.furnaces.sapphire.SapphireFurnace;
import com.hds.yarcot.blocks.miners.sapphire.SapphireMiner;
import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlockItem(
            "sapphire_block",
            SapphireBlock::new
    );

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlockItem(
            "ruby_block",
            RubyBlock::new
    );

    public static final RegistryObject<Block> SAPPHIRE_BARREL = ModBlocks.registerBlockItem(
            "sapphire_barrel",
            SapphireBarrel::new
    );

    public static final RegistryObject<Block> SAPPHIRE_BATTERY = ModBlocks.registerBlockItem(
            "sapphire_battery",
            SapphireBattery::new
    );

    public static final RegistryObject<Block> SAPPHIRE_CONDUIT = ModBlocks.registerBlockItem(
            "sapphire_conduit",
            SapphireConduit::new
    );

    public static final RegistryObject<Block> SAPPHIRE_MINER = ModBlocks.registerBlockItem(
            "sapphire_miner",
            SapphireMiner::new
    );

    public static final RegistryObject<Block> SAPPHIRE_FURNACE = ModBlocks.registerBlockItem(
            "sapphire_furnace",
            SapphireFurnace::new
    );

    public static void registerAll() {

        ModLog.info("All Blocks were registered!");

    }

    public static RegistryObject<Block> registerBlockItem(String name, Supplier<Block> blockSupplier) {

        RegistryObject<Block> block = RegistryHandler.BLOCKS.register(
                name,
                blockSupplier
        );
        RegistryHandler.ITEMS.register(
                name,
                () -> BaseBlock.createDefaultBlockItem(block.get())
        );

        return block;

    }

}
