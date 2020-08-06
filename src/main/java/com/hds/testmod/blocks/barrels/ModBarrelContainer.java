package com.hds.testmod.blocks.barrels;

import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.customclasses.ModInventoryContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public abstract class ModBarrelContainer extends ModInventoryContainer {
    protected final ModBarrelTile BARREL_TILE;

    public ModBarrelContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(type, windowId, playerInventory);

        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        if (!(te instanceof ModBarrelTile))
            ModLog.error("INVALID TILE ENTITY INSTANCE: " + te.toString());

        this.BARREL_TILE = (ModBarrelTile) te;
    }

    protected void generateLayout(PlayerInventory playerInventory, int cols, int rows, IItemHandler extraInventory) {
        int xOffset = (cols - 9) * ModInventoryContainer.CELL_SIZE / 2;

        int yCell;
        {
            int currentSlot = 0;
            for (yCell = 0; yCell < rows; yCell++) {
                if (currentSlot >= extraInventory.getSlots())
                    break;
                for (int xCell = 0; xCell < cols; xCell++) {
                    addSlot(new SlotItemHandler(extraInventory, currentSlot, Math.min(xOffset, 0) + 8 + xCell * ModInventoryContainer.CELL_SIZE, 18 + yCell * ModInventoryContainer.CELL_SIZE));
                    currentSlot++;
                }
            }
        }

        this.generatePlayerInventory(Math.max(xOffset, 0), 30 + yCell * ModInventoryContainer.CELL_SIZE);
    }

    @Override
    public abstract boolean canInteractWith(PlayerEntity playerIn);

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {

        Slot slotToTransfer = this.getSlot(index);
        int barrelSlots = BARREL_TILE.getBarrelInventory().getSlots();
        if (index < barrelSlots) { // FROM BARREL TO INVENTORY
            this.mergeItemStack(slotToTransfer.getStack(), barrelSlots, this.getInventory().size(), true);
        } else { // FROM INVENTORY TO BARREL
            this.mergeItemStack(slotToTransfer.getStack(), 0, barrelSlots, false);
        }

        slotToTransfer.onSlotChanged();
        return ItemStack.EMPTY;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        playerIn.world.setBlockState(
                this.BARREL_TILE.getPos(),
                this.BARREL_TILE.getBlockState().with(BlockStateProperties.OPEN, false)
        );
        playerIn.world.playSound(null, this.BARREL_TILE.getPos(), SoundEvents.BLOCK_BARREL_CLOSE, SoundCategory.BLOCKS, .5F, 1);
    }
}
