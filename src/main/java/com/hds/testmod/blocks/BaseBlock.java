package com.hds.testmod.blocks;

import com.hds.testmod.creativetabs.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BaseBlock {

    public static Item.Properties createDefaultProperties() {
        return new Item.Properties().group(ModItemGroups.BLOCKS);
    }

    public static BlockItem createDefaultBlockItem(Block block) {
        return new BlockItem(
                block,
                new Item.Properties()
                        .group(ModItemGroups.BLOCKS)
        );
    }

}
