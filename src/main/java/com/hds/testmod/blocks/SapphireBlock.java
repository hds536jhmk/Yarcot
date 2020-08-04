package com.hds.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SapphireBlock extends Block {

    public SapphireBlock() {
        super(
                Block.Properties.create(Material.IRON)
                .hardnessAndResistance(5.0F, 6.0F)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .sound(SoundType.METAL)
        );
    }

}
