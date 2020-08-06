package com.hds.yarcot.creativetabs;

import com.hds.yarcot.Yarcot;
import net.minecraft.item.Items;

public class ModItemGroups {

    public static BaseItemGroup ARMORS = new BaseItemGroup(Yarcot.MOD_ID + "armors", Items.DIAMOND_CHESTPLATE);
    public static BaseItemGroup BLOCKS = new BaseItemGroup(Yarcot.MOD_ID + "blocks", Items.DIAMOND_BLOCK);
    public static BaseItemGroup ITEMS  = new BaseItemGroup(Yarcot.MOD_ID + "items" , Items.DIAMOND);
    public static BaseItemGroup TOOLS  = new BaseItemGroup(Yarcot.MOD_ID + "tools" , Items.DIAMOND_PICKAXE);

}
