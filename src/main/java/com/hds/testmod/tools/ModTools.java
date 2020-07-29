package com.hds.testmod.tools;

import com.hds.testmod.creativetabs.ToolsTab;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.fml.RegistryObject;

public class ModTools {

    public static final RegistryObject<PickaxeItem> SAPPHIRE_PICKAXE = RegistryHandler.ITEMS.register(
            "sapphire_pickaxe",
            () -> new PickaxeItem(ModItemTiers.SAPPHIRE, 4, -2.8F, new Item.Properties().group(ToolsTab.TAB))
    );

    public static void Load() {}

}
