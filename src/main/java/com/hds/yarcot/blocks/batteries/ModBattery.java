package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.apis.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class ModBattery extends Block {
    private final Function<BlockPos, INamedContainerProvider> CONTAINER_PROVIDER;

    public ModBattery(Properties properties, Function<BlockPos, INamedContainerProvider> containerProvider) {
        super(properties);
        this.CONTAINER_PROVIDER = containerProvider;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape conduitShape = Block.makeCuboidShape(1, 1, 1, 15, 15, 15);

        return conduitShape.simplify();
    }

    @Override
    public abstract boolean hasTileEntity(BlockState state);

    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            if (player.isSneaking()) {
                BlockState newState = ModBlockStateProperties.incrementIntegerProperty(
                        state, ModBlockStateProperties.CONNECTION.getFromDirection(hit.getFace()), 0, 3
                );
                worldIn.setBlockState(pos, newState);
            } else {
                TileEntity tileEntity = worldIn.getTileEntity(pos);
                if (tileEntity instanceof ModBatteryTile) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, this.CONTAINER_PROVIDER.apply(pos), pos);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasTileEntity() && (state.getBlock() != newState.getBlock() || !newState.hasTileEntity())) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof ModBatteryTile) {
                ModBatteryTile batteryTile = (ModBatteryTile) te;
                ItemStackHandler batteryInventory = batteryTile.getItemStorage();
                for (int i = 0; i < batteryInventory.getSlots(); i++) {
                    spawnAsEntity(worldIn, pos, batteryInventory.getStackInSlot(i));
                }
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState()
                .with(ModBlockStateProperties.NORTH_CONNECTION, 0)
                .with(ModBlockStateProperties.SOUTH_CONNECTION, 0)
                .with(ModBlockStateProperties.UP_CONNECTION, 0)
                .with(ModBlockStateProperties.DOWN_CONNECTION, 0)
                .with(ModBlockStateProperties.EAST_CONNECTION, 0)
                .with(ModBlockStateProperties.WEST_CONNECTION, 0);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(
                ModBlockStateProperties.NORTH_CONNECTION,
                ModBlockStateProperties.SOUTH_CONNECTION,
                ModBlockStateProperties.UP_CONNECTION,
                ModBlockStateProperties.DOWN_CONNECTION,
                ModBlockStateProperties.EAST_CONNECTION,
                ModBlockStateProperties.WEST_CONNECTION
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (!(tileEntity instanceof ModBatteryTile))
            return 0;
        ModBatteryTile batteryTile = (ModBatteryTile)tileEntity;
        return (int)(batteryTile.getChargePercentage() * 15.0F);
    }
}
