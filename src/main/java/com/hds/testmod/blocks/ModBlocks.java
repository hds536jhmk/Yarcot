package com.hds.testmod.blocks;

import com.hds.testmod.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraftforge.fml.RegistryObject;

public class ModBlocks {

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = RegistryHandler.BLOCKS.register(
            "sapphire_block", /* Giving the pointer to SapphireBlock constructor */ SapphireBlock::new
    );

    public static final RegistryObject<BlockItem> SAPPHIRE_BLOCKITEM = RegistryHandler.ITEMS.register(
            "sapphire_block", () -> new BaseBlockItem(SAPPHIRE_BLOCK.get())
    );

    public static void Load() {}

}
