package com.hds.testmod.creativetab;

import com.hds.testmod.TestMod;
import net.minecraft.item.Items;

public class ModItemGroups {

    public static BaseItemGroup ARMORS = new BaseItemGroup(TestMod.MODID + "armors", Items.DIAMOND_CHESTPLATE);
    public static BaseItemGroup BLOCKS = new BaseItemGroup(TestMod.MODID + "blocks", Items.DIAMOND_BLOCK);
    public static BaseItemGroup ITEMS  = new BaseItemGroup(TestMod.MODID + "items" , Items.DIAMOND);
    public static BaseItemGroup TOOLS  = new BaseItemGroup(TestMod.MODID + "tools" , Items.DIAMOND_PICKAXE);

}
