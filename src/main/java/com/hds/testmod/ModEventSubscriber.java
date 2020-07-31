package com.hds.testmod;

import com.hds.testmod.armor.ModArmorMaterial;
import com.hds.testmod.blocks.BaseBlockItem;
import com.hds.testmod.blocks.ModBlocks;
import com.hds.testmod.blocks.SapphireBlock;
import com.hds.testmod.creativetabs.ModItemGroups;
import com.hds.testmod.items.BaseItem;
import com.hds.testmod.tools.ModItemTiers;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;


// Telling forge to mark this class as an Event Listener for mod TestMod.MODID
//  If bus is set to MOD it listens to Registry and Mod Loading events
@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                setup(new BaseItem(), "sapphire"),
                setup(new PickaxeItem(ModItemTiers.SAPPHIRE, 4, -2.8F, new Item.Properties().group(ModItemGroups.TOOLS)), "sapphire_pickaxe"),
                setup(new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.ARMORS)), "sapphire_chestplate")
        );
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        Block block1 = setup(new SapphireBlock(), "sapphire_block");
        registry.registerAll(
                block1
        );
        new BaseBlockItem(block1).setRegistryName(block1.getRegistryName());
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(TestMod.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}
