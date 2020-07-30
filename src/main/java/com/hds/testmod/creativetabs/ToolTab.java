package com.hds.testmod.creativetabs;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ToolTab {

    public static final ItemGroup TAB = new ItemGroup("testmodtools") {
        @Override
        public ItemStack createIcon() { return new ItemStack(Items.DIAMOND_PICKAXE); }
    };

}
