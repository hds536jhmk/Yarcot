package com.hds.testmod.container;

import com.hds.testmod.block.ModBlocks;
import com.hds.testmod.tileentity.SapphireChestTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class SapphireChestContainer extends Container {

    private TileEntity tileEntity;
    private PlayerEntity playerEntity;

    public SapphireChestContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModContainers.SAPPHIRE_CHEST_CONTAINER.get(), windowId);
        this.tileEntity = playerInventory.player.world.getTileEntity(pos);
        this.playerEntity = playerInventory.player;

        TileEntity te = this.playerEntity.world.getTileEntity(pos);
        if (te != null && te instanceof SapphireChestTile) {
            te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(
                    tileInventory -> {
                        generateLayout(playerInventory, 9, 3, tileInventory);
                    }
            );
        }
    }

    private void generateLayout(PlayerInventory playerInventory, int cols, int rows, IItemHandler extraInventory) {
        int currentY;
        {
            int i = 0;
            for (currentY = 0; currentY < rows; currentY++) {
                if (i >= extraInventory.getSlots())
                    break;
                for (int x = 0; x < cols; x++) {
                    addSlot(new SlotItemHandler(extraInventory, i, 8 + x * 18, 18 + currentY * 18));
                    i++;
                }
            }
        }

        {
            IItemHandler playerItemHandler = new InvWrapper(playerInventory);
            int x = 0;
            for (int i = 0; i < 36; i++) {
                if (i < 27) { // INVENTORY
                    addSlot(new SlotItemHandler(playerItemHandler, i + 9, 8 + x * 18, 30 + currentY * 18));
                } else { // HOTBAR
                    addSlot(new SlotItemHandler(playerItemHandler, i - 27, 8 + x * 18, 34 + currentY * 18));
                }
                x++;
                if (x >= 9) {
                    x = 0;
                    currentY++;
                }
            }
        }
    }

    protected SapphireChestContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.SAPPHIRE_CHEST.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return super.transferStackInSlot(playerIn, index);
    }
}
