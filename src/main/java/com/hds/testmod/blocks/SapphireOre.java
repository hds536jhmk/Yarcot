package com.hds.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class SapphireOre extends Block {
    public SapphireOre() {
        super(
                Block.Properties.create(Material.IRON)
                .hardnessAndResistance(5.0F, 6.0F)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .sound(SoundType.STONE)
        );
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, 2, 5) : 0;
    }
}
