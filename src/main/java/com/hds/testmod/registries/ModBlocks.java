package com.hds.testmod.registries;

import com.hds.testmod.blocks.BaseBlock;
import com.hds.testmod.blocks.barrels.sapphire.SapphireBarrel;
import com.hds.testmod.blocks.SapphireBlock;
import com.hds.testmod.blocks.batteries.sapphire.SapphireBattery;
import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlockItem(
            "sapphire_block",
            () -> new SapphireBlock()
    );

    public static final RegistryObject<Block> SAPPHIRE_BARREL = ModBlocks.registerBlockItem(
            "sapphire_barrel",
            () -> new SapphireBarrel()
    );

    public static final RegistryObject<Block> SAPPHIRE_BATTERY = ModBlocks.registerBlockItem(
            "sapphire_battery",
            () -> new SapphireBattery()
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
