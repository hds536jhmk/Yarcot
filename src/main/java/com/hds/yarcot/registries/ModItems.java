package com.hds.yarcot.registries;

import com.hds.yarcot.items.BaseItem;
import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.util.RegistryHandler;
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
