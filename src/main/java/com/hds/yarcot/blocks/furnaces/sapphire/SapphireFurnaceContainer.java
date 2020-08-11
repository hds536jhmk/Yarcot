package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.registries.ModContainers;
import com.hds.yarcot.util.ModLog;
import com.hds.yarcot.util.customclasses.ModEnergyContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SapphireFurnaceContainer extends ModEnergyContainer {
    protected final SapphireFurnaceTile FURNACE_TILE;
    protected float furnaceProgress = 0;

    public SapphireFurnaceContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModContainers.SAPPHIRE_FURNACE_CONTAINER.get(), windowId, playerInventory, playerInventory.player.world.getTileEntity(pos));

        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        if (!(te instanceof SapphireFurnaceTile))
            ModLog.error("INVALID TILE ENTITY INSTANCE: " + te.toString());

        this.FURNACE_TILE = (SapphireFurnaceTile) te;

        this.trackEnergy();
        this.trackInt(
                new IntReferenceHolder() {
                    @Override
                    public int get() {
                        return SapphireFurnaceContainer.this.FURNACE_TILE.getSmeltProgress();
                    }

                    @Override
                    public void set(int value) {
                        SapphireFurnaceContainer.this.furnaceProgress = (float) value / 100.0F;
                    }
                }
        );

        ItemStackHandler furnaceInventory = FURNACE_TILE.getFurnaceInventory().getItemStackHandler();
        this.addSlot(new SlotItemHandler(furnaceInventory, 0, 56, 34));
        this.addSlot(new SlotItemHandler(furnaceInventory, 1, 116, 35) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });

        this.generatePlayerInventory(0, 84);
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
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
