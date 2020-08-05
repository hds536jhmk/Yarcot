package com.hds.testmod.blocks.conduits.sapphire;

import com.hds.testmod.util.customclasses.DirectionToBooleanProperty;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.shapes.*;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class SapphireConduit extends Block {

    public SapphireConduit() {
        super(
                Block.Properties.create(Material.IRON)
                .hardnessAndResistance(5.0F, 6.0F)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .sound(SoundType.METAL)
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape conduitShape = Block.makeCuboidShape(6, 6, 6, 10, 10, 10);

        ArrayList<VoxelShape> shapesToAdd = new ArrayList<VoxelShape>();
        for (Direction direction : Direction.values()) {
            boolean hasConnection = state.get(DirectionToBooleanProperty.get(direction));
            if (!hasConnection)
                continue;

            Vec3i directionVector = direction.getDirectionVec();
            shapesToAdd.add(Block.makeCuboidShape(
                    7,
                    7,
                    7,
                    directionVector.getX() == 0 ? 9 : (directionVector.getX() > 0 ? 16 : 0),
                    directionVector.getY() == 0 ? 9 : (directionVector.getY() > 0 ? 16 : 0),
                    directionVector.getZ() == 0 ? 9 : (directionVector.getZ() > 0 ? 16 : 0)
            ));
        }

        for (int i = 0; i < shapesToAdd.size(); i++) {
            conduitShape = VoxelShapes.combine(conduitShape, shapesToAdd.get(i), IBooleanFunction.OR);
        }

        return conduitShape.simplify();
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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState()
                .with(BlockStateProperties.WEST, false)
                .with(BlockStateProperties.UP, false)
                .with(BlockStateProperties.SOUTH, false)
                .with(BlockStateProperties.NORTH, false)
                .with(BlockStateProperties.EAST, false)
                .with(BlockStateProperties.DOWN, false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(
                BlockStateProperties.WEST,
                BlockStateProperties.UP,
                BlockStateProperties.SOUTH,
                BlockStateProperties.NORTH,
                BlockStateProperties.EAST,
                BlockStateProperties.DOWN
        );
    }
}
