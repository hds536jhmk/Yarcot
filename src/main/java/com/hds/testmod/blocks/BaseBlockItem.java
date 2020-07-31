package com.hds.testmod.blocks;

import com.hds.testmod.creativetabs.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BaseBlockItem extends BlockItem {

    public BaseBlockItem(Block block) {
        super(
                block,
                new Item.Properties()
                    .group(ModItemGroups.BLOCKS)
        );
    }

}
