package com.hds.testmod.tool;

import com.hds.testmod.TestMod;
import com.hds.testmod.creativetab.ModItemGroups;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.fml.RegistryObject;

public class ModTools {

    public static final RegistryObject<PickaxeItem> SAPPHIRE_PICKAXE = RegistryHandler.ITEMS.register(
            "sapphire_pickaxe",
            () -> new PickaxeItem(ModItemTiers.SAPPHIRE, 4, -2.8F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static void registerAll() {

        TestMod.logInfo("All Tools were registered!");

    }

}