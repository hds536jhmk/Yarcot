package com.hds.testmod.armor;

import com.hds.testmod.creativetabs.ModItemGroups;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModArmors {

    public static final RegistryObject<ArmorItem> SAPPHIRE_CHESTPLATE = RegistryHandler.ITEMS.register(
            "sapphire_chestplate",
            () -> new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.ARMORS))
    );

    public static void Load() {}

}
