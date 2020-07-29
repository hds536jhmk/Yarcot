package com.hds.testmod.items;

import com.hds.testmod.creativetabs.ItemTab;
import net.minecraft.item.Item;

public class BaseItem extends Item {

    public BaseItem() {
        // Creating a new Item Property and adding the item to a group
        super(
                new Item.Properties()
                    .group(ItemTab.TAB)
        );
    }

}
