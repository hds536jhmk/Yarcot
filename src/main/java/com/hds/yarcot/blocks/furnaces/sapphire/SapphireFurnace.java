package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.blocks.furnaces.ModFurnace;
import com.hds.yarcot.config.blocks.ModFurnaceConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class SapphireFurnace extends ModFurnace {
    public static ModFurnaceConfig config;

    public SapphireFurnace() {
        super(
                Block.Properties.create(Material.IRON)
                        .hardnessAndResistance(5.0F, 6.0F)
                        .harvestTool(ToolType.PICKAXE)
                        .harvestLevel(2)
                        .sound(SoundType.METAL),
                blockPos -> new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("screen." + Yarcot.MOD_ID + ".sapphire_furnace_title");
                    }

                    @Nullable
                    @Override
                    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new SapphireFurnaceContainer(windowId, playerInventory, blockPos);
                    }
                }
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SapphireFurnaceTile();
    }
}