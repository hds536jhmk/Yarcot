package com.hds.testmod.util;

import com.hds.testmod.registries.ModArmors;
import com.hds.testmod.registries.ModContainers;
import com.hds.testmod.registries.ModItems;
import com.hds.testmod.registries.ModBlocks;
import com.hds.testmod.TestMod;
import com.hds.testmod.registries.ModTileEntities;
import com.hds.testmod.registries.ModTools;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, TestMod.MODID
    );
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, TestMod.MODID
    );
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(
            ForgeRegistries.TILE_ENTITIES, TestMod.MODID
    );
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(
            ForgeRegistries.CONTAINERS, TestMod.MODID
    );

    public static void init() {
        ModArmors.registerAll();
        ModBlocks.registerAll();
        ModItems.registerAll();
        ModTools.registerAll();
        ModTileEntities.registerAll();
        ModContainers.registerAll();

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
