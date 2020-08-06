package com.hds.yarcot.items;

import com.hds.yarcot.creativetabs.ModItemGroups;
import net.minecraft.item.Item;

public class BaseItem {

    public static Item.Properties createDefaultProperties() {
        // Creating a new Item Property and adding the item to a group
        return new Item.Properties().group(ModItemGroups.ITEMS);
    }

    public static Item createDefaultItem() {
        return new Item(createDefaultProperties());
    }

}
