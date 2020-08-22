package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.apis.ModEnergyContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

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

    protected void generateSlots(ItemStackHandler handler, int x, int y) {
        this.addSlot(new SlotItemHandler(handler, 0, x, y));
        this.addSlot(new SlotItemHandler(handler, 1, x, y + 21));
    }

    @Override
    public abstract boolean canInteractWith(PlayerEntity playerIn);

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {

        Slot slotToTransfer = this.getSlot(index);
        if (index < 2) {
            this.mergeItemStack(slotToTransfer.getStack(), 2, this.getInventory().size(), true);
        } else {
            slotToTransfer.getStack().getCapability(CapabilityEnergy.ENERGY).ifPresent(
                    handler -> {
                        if (handler.canExtract())
                            if (handler.canReceive())
                                this.mergeItemStack(slotToTransfer.getStack(), 0, 2, false);
                            else
                                this.mergeItemStack(slotToTransfer.getStack(), 0, 1, false);
                        else if (handler.canReceive())
                            this.mergeItemStack(slotToTransfer.getStack(), 1, 2, false);
                    }
            );
        }

        slotToTransfer.onSlotChanged();
        return ItemStack.EMPTY;
    }
}
