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
}
