package com.hds.testmod.creativetabs;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BlockTab {

    public static final ItemGroup TAB = new ItemGroup("testmodblocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.DIAMOND_BLOCK);
        }
    };

}
