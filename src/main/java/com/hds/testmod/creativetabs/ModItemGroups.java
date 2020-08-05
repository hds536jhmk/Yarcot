package com.hds.testmod.creativetabs;

import com.hds.testmod.TestMod;
import net.minecraft.item.Items;

public class ModItemGroups {

    public static BaseItemGroup ARMORS = new BaseItemGroup(TestMod.MOD_ID + "armors", Items.DIAMOND_CHESTPLATE);
    public static BaseItemGroup BLOCKS = new BaseItemGroup(TestMod.MOD_ID + "blocks", Items.DIAMOND_BLOCK);
    public static BaseItemGroup ITEMS  = new BaseItemGroup(TestMod.MOD_ID + "items" , Items.DIAMOND);
    public static BaseItemGroup TOOLS  = new BaseItemGroup(TestMod.MOD_ID + "tools" , Items.DIAMOND_PICKAXE);

}
