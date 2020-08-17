package com.hds.yarcot.blocks.ores;

import com.hds.yarcot.config.world.ModOreConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class SulfurOre extends Block {
    public static ModOreConfig config;

    public SulfurOre() {
        super(
                Block.Properties.create(Material.ROCK)
                        .hardnessAndResistance(3.0F, 3.0F)
                        .harvestTool(ToolType.PICKAXE)
                        .harvestLevel(1)
                        .sound(SoundType.STONE)
        );
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, 2, 3) : 0;
    }
}
