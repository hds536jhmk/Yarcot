package com.hds.testmod.blocks.conduits.sapphire;

import com.hds.testmod.blocks.conduits.ModConduit;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class SapphireConduit extends ModConduit {

    public SapphireConduit() {
        super(
                Block.Properties.create(Material.IRON)
                .hardnessAndResistance(5.0F, 6.0F)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .sound(SoundType.METAL)
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SapphireConduitTile();
    }
}
