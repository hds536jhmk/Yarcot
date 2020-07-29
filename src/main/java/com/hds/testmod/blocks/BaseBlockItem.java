package com.hds.testmod.blocks;

import com.hds.testmod.creativetabs.BlockTab;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BaseBlockItem extends BlockItem {

    public BaseBlockItem(Block block) {
        super(
                block,
                new Item.Properties()
                    .group(BlockTab.TAB)
        );
    }

}
