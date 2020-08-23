package com.hds.yarcot.blocks.conduits;

import com.hds.yarcot.apis.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class ModConduit extends Block {

    public ModConduit(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape conduitShape = Block.makeCuboidShape(6, 6, 6, 10, 10, 10);

        ArrayList<VoxelShape> shapesToAdd = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            boolean hasConnection = state.get(ModBlockStateProperties.BOOLEAN_DIRECTION.getFromDirection(direction));
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

        for (VoxelShape voxelShape : shapesToAdd) {
            conduitShape = VoxelShapes.combine(conduitShape, voxelShape, IBooleanFunction.OR);
        }

        return conduitShape.simplify();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public abstract boolean hasTileEntity(BlockState state);

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

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
