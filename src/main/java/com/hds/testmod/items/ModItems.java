package com.hds.testmod.items;

import com.hds.testmod.util.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> SAPPHIRE = RegistryHandler.ITEMS.register(
            "sapphire", /* Pointer to item's constructor */ BaseItem::new
    );

    public static void Load() {}

}
