package com.hds.testmod.creativetabs;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ArmorTab {

    public static final ItemGroup TAB = new ItemGroup("testmodarmors") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.DIAMOND_CHESTPLATE);
        }
    };

}
