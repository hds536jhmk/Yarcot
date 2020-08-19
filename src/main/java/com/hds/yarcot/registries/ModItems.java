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

    public static final RegistryObject<Item> RUBY = RegistryHandler.ITEMS.register(
            "ruby",
            BaseItem::createDefaultItem
    );

    public static final RegistryObject<Item> SULFUR = RegistryHandler.ITEMS.register(
            "sulfur",
            BaseItem::createDefaultItem
    );

    public static final RegistryObject<Item> LITHIUM = RegistryHandler.ITEMS.register(
            "lithium",
            BaseItem::createDefaultItem
    );

    public static final RegistryObject<Item> BATTERY_FRAME = RegistryHandler.ITEMS.register(
            "battery_frame",
            BaseItem::createDefaultItem
    );

    public static final RegistryObject<Item> BATTERY_CORE = RegistryHandler.ITEMS.register(
            "battery_core",
            BaseItem::createDefaultItem
    );

    public static void registerAll() {

        ModLog.info("All Items were registered!");

    }

}
