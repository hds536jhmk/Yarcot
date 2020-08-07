package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.util.customclasses.ModEnergyContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class ModBatteryContainer extends ModEnergyContainer {
    protected final ModBatteryTile BATTERY_TILE;

    public ModBatteryContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(type, windowId, playerInventory, playerInventory.player.world.getTileEntity(pos));

        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        if (!(te instanceof ModBatteryTile))
            ModLog.error("INVALID TILE ENTITY INSTANCE: " + te.toString());

        this.BATTERY_TILE = (ModBatteryTile) te;

        this.trackEnergy();
    }

    @Override
    public abstract boolean canInteractWith(PlayerEntity playerIn);

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
