package com.hds.testmod.blocks.barrels.sapphire;

import com.hds.testmod.registries.ModContainers;
import com.hds.testmod.util.ModLog;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class SapphireBarrelContainer extends Container {

    public static final int CELLSIZE = 18;

    private final SapphireBarrelTile BARREL_TILE;
    private final PlayerEntity PLAYER;

    public SapphireBarrelContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModContainers.SAPPHIRE_BARREL_CONTAINER.get(), windowId);
        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        if (!(te instanceof SapphireBarrelTile))
            ModLog.error("INVALID TILEENTITY INSTANCE");

        this.BARREL_TILE = (SapphireBarrelTile) te;
        this.PLAYER = playerInventory.player;

        generateLayout(playerInventory, SapphireBarrelTile.COLUMNS, SapphireBarrelTile.ROWS, this.BARREL_TILE.getBarrelInventory());
    }

    private void generateLayout(PlayerInventory playerInventory, int cols, int rows, IItemHandler extraInventory) {
        int xOffset = (cols - 9) * CELLSIZE / 2;

        int yCell;
        {
            int currentSlot = 0;
            for (yCell = 0; yCell < rows; yCell++) {
                if (currentSlot >= extraInventory.getSlots())
                    break;
                for (int xCell = 0; xCell < cols; xCell++) {
                    addSlot(new SlotItemHandler(extraInventory, currentSlot, Math.min(xOffset, 0) + 8 + xCell * CELLSIZE, 18 + yCell * CELLSIZE));
                    currentSlot++;
                }
            }
        }

        {
            IItemHandler playerItemHandler = new InvWrapper(playerInventory);
            int xCell = 0;
            for (int currentSlot = 0; currentSlot < 36; currentSlot++) {
                int xPosition = Math.max(xOffset, 0) + 8 + xCell * CELLSIZE;
                if (currentSlot < 27) { // INVENTORY
                    addSlot(new SlotItemHandler(playerItemHandler, currentSlot + 9, xPosition, 30 + yCell * CELLSIZE));
                } else { // HOTBAR
                    addSlot(new SlotItemHandler(playerItemHandler, currentSlot - 27, xPosition, 34 + yCell * CELLSIZE));
                }
                xCell++;
                if (xCell >= 9) { // NEW ROW
                    xCell = 0;
                    yCell++;
                }
            }
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

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
