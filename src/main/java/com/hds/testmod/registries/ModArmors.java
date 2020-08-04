package com.hds.testmod.registries;

import com.hds.testmod.materials.ModArmorMaterial;
import com.hds.testmod.creativetabs.ModItemGroups;
import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.RegistryHandler;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModArmors {

    public static final RegistryObject<ArmorItem> SAPPHIRE_HELMET = RegistryHandler.ITEMS.register(
            "sapphire_helmet",
            () -> new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.ARMORS))
    );

    public static final RegistryObject<ArmorItem> SAPPHIRE_CHESTPLATE = RegistryHandler.ITEMS.register(
            "sapphire_chestplate",
            () -> new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.ARMORS))
    );

    public static final RegistryObject<ArmorItem> SAPPHIRE_LEGGINGS = RegistryHandler.ITEMS.register(
            "sapphire_leggings",
            () -> new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.ARMORS))
    );

    public static final RegistryObject<ArmorItem> SAPPHIRE_BOOTS = RegistryHandler.ITEMS.register(
            "sapphire_boots",
            () -> new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.ARMORS))
    );

    public static void registerAll() {

        ModLog.info("All Armors were registered!");

    }

}
