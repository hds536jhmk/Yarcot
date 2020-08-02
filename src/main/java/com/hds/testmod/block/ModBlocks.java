package com.hds.testmod.block;

import com.hds.testmod.TestMod;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlockItem(
            "sapphire_block",
            () -> new SapphireBlock()
    );

    public static void registerAll() {

        TestMod.logInfo("All Blocks were registered!");

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
