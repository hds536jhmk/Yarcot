package com.hds.yarcot.blocks.batteries;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Function;

public abstract class ModBattery extends Block {
    private final Function<BlockPos, INamedContainerProvider> CONTAINER_PROVIDER;

    public ModBattery(Properties properties, Function<BlockPos, INamedContainerProvider> containerProvider) {
        super(properties);
        this.CONTAINER_PROVIDER = containerProvider;
    }

    @Override
    public abstract boolean hasTileEntity(BlockState state);

    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof ModBatteryTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player, this.CONTAINER_PROVIDER.apply(pos), pos);
            }
        }
        return ActionResultType.SUCCESS;
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
