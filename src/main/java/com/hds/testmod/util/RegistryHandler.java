package com.hds.testmod.util;

import com.hds.testmod.armor.ModArmors;
import com.hds.testmod.item.ModItems;
import com.hds.testmod.block.ModBlocks;
import com.hds.testmod.TestMod;
import com.hds.testmod.tool.ModTools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, TestMod.MODID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, TestMod.MODID);

    public static void init() {
        ModArmors.registerAll();
        ModBlocks.registerAll();
        ModItems.registerAll();
        ModTools.registerAll();

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
