package com.hds.yarcot.blocks.furnaces;

import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.apis.ModEnergyContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ModFurnaceContainer extends ModEnergyContainer {
    protected final ModFurnaceTile FURNACE_TILE;
    protected float furnaceSmeltProgress = 0;

    public ModFurnaceContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(type, windowId, playerInventory, playerInventory.player.world.getTileEntity(pos));

        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        if (!(te instanceof ModFurnaceTile))
            ModLog.error("INVALID TILE ENTITY INSTANCE: " + te.toString());

        this.FURNACE_TILE = (ModFurnaceTile) te;

        this.trackEnergy();
        this.trackInt(
                new IntReferenceHolder() {
                    @Override
                    public int get() {
                        return (int) (FURNACE_TILE.getSmeltProgressPercentage() * 100.0F);
                    }

                    @Override
                    public void set(int value) {
                        furnaceSmeltProgress = (float) value / 100.0F;
                    }
                }
        );
    }

    protected void generateSlots(int inputX, int inputY, int outputX, int outputY) {
        ItemStackHandler furnaceInventory = FURNACE_TILE.getFurnaceInventory().getItemStackHandler();
        this.addSlot(new SlotItemHandler(furnaceInventory, 0, inputX, inputY));
        this.addSlot(new SlotItemHandler(furnaceInventory, 1, outputX, outputY) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {

        Slot slotToTransfer = this.getSlot(index);
        if (index < 2) {
            this.mergeItemStack(slotToTransfer.getStack(), 2, this.getInventory().size(), true);
        } else {
            this.mergeItemStack(slotToTransfer.getStack(), 0, 1, false);
        }

        slotToTransfer.onSlotChanged();
        return ItemStack.EMPTY;
    }

    @Override
    public abstract boolean canInteractWith(PlayerEntity playerIn);
}
