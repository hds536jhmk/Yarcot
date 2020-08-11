package com.hds.yarcot.blocks.furnaces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class ModFurnace extends Block {
    private final Function<BlockPos, INamedContainerProvider> CONTAINER_PROVIDER;

    public ModFurnace(Properties properties, Function<BlockPos, INamedContainerProvider> containerProvider) {
        super(properties);
        this.CONTAINER_PROVIDER = containerProvider;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof ModFurnaceTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player, this.CONTAINER_PROVIDER.apply(pos), pos);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasTileEntity() && (state.getBlock() != newState.getBlock() || !newState.hasTileEntity())) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof ModFurnaceTile) {
                ModFurnaceTile furnaceTile = (ModFurnaceTile) te;
                for (int i = 0; i < furnaceTile.getFurnaceInventory().getSizeInventory(); i++) {
                    spawnAsEntity(worldIn, pos, furnaceTile.getFurnaceInventory().getStackInSlot(i));
                }
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState()
                .with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite())
                .with(BlockStateProperties.POWERED, false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);
    }

    @Override
    public abstract boolean hasTileEntity(BlockState state);

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);
}
