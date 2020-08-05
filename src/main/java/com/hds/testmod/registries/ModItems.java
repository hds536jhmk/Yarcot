package com.hds.testmod.registries;

import com.hds.testmod.items.BaseItem;
import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> SAPPHIRE = RegistryHandler.ITEMS.register(
            "sapphire",
            BaseItem::createDefaultItem
    );

    public static void registerAll() {

        ModLog.info("All Items were registered!");

    }

}
