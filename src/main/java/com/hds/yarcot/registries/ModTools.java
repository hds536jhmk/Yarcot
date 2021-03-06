package com.hds.yarcot.registries;

import com.hds.yarcot.creativetabs.ModItemGroups;
import com.hds.yarcot.materials.ModItemTiers;
import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.util.RegistryHandler;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

public class ModTools {

    // SAPPHIRE ITEMS
    public static final RegistryObject<SwordItem> SAPPHIRE_SWORD = RegistryHandler.ITEMS.register(
            "sapphire_sword",
            () -> new SwordItem(ModItemTiers.SAPPHIRE, 5, -2.2F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static final RegistryObject<ShovelItem> SAPPHIRE_SHOVEL = RegistryHandler.ITEMS.register(
            "sapphire_shovel",
            () -> new ShovelItem(ModItemTiers.SAPPHIRE, 4, -3.0F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static final RegistryObject<PickaxeItem> SAPPHIRE_PICKAXE = RegistryHandler.ITEMS.register(
            "sapphire_pickaxe",
            () -> new PickaxeItem(ModItemTiers.SAPPHIRE, 3, -2.8F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static final RegistryObject<AxeItem> SAPPHIRE_AXE = RegistryHandler.ITEMS.register(
            "sapphire_axe",
            () -> new AxeItem(ModItemTiers.SAPPHIRE, 8, -2.9F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static final RegistryObject<HoeItem> SAPPHIRE_HOE = RegistryHandler.ITEMS.register(
            "sapphire_hoe",
            () -> new HoeItem(ModItemTiers.SAPPHIRE, -3.0F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    // RUBY ITEMS
    public static final RegistryObject<SwordItem> RUBY_SWORD = RegistryHandler.ITEMS.register(
            "ruby_sword",
            () -> new SwordItem(ModItemTiers.RUBY, 5, -2.2F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static final RegistryObject<ShovelItem> RUBY_SHOVEL = RegistryHandler.ITEMS.register(
            "ruby_shovel",
            () -> new ShovelItem(ModItemTiers.RUBY, 4, -3.0F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = RegistryHandler.ITEMS.register(
            "ruby_pickaxe",
            () -> new PickaxeItem(ModItemTiers.RUBY, 3, -2.8F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static final RegistryObject<AxeItem> RUBY_AXE = RegistryHandler.ITEMS.register(
            "ruby_axe",
            () -> new AxeItem(ModItemTiers.RUBY, 8, -2.9F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static final RegistryObject<HoeItem> RUBY_HOE = RegistryHandler.ITEMS.register(
            "ruby_hoe",
            () -> new HoeItem(ModItemTiers.RUBY, -3.0F, new Item.Properties().group(ModItemGroups.TOOLS))
    );

    public static void registerAll() {

        ModLog.info("All Tools were registered!");

    }

}
