package com.hds.testmod.item;

import com.hds.testmod.TestMod;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> SAPPHIRE = RegistryHandler.ITEMS.register(
            "sapphire",
            () -> BaseItem.createDefaultItem()
    );

    public static void registerAll() {

        TestMod.logInfo("All Items were registered!");

    }

}
