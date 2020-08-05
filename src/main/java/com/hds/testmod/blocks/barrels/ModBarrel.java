package com.hds.testmod.blocks.barrels;

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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class ModBarrel extends Block {
    private final Function<BlockPos, INamedContainerProvider> CONTAINER_PROVIDER;

    public ModBarrel(Properties properties, Function<BlockPos, INamedContainerProvider> containerProvider) {
        super(properties);
        CONTAINER_PROVIDER = containerProvider;
    }

    @Override
    public abstract boolean hasTileEntity(BlockState state);

    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasTileEntity() && (state.getBlock() != newState.getBlock() || !newState.hasTileEntity())) {
            TileEntity te = worldIn.getTileEntity(pos);
            te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(
                    handler -> {
                        for (int i=0; i < handler.getSlots(); i++) {
                            spawnAsEntity(worldIn, pos, handler.getStackInSlot(i));
                        }
                    }
            );
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof ModBarrelTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player, this.CONTAINER_PROVIDER.apply(pos), pos);
                worldIn.setBlockState(
                        pos,
                        state.with(BlockStateProperties.OPEN, true)
                );
                worldIn.playSound(null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, .5F, 1);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState()
                .with(BlockStateProperties.FACING, context.getFace())
                .with(BlockStateProperties.OPEN, false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, BlockStateProperties.OPEN);
    }
}
