package com.hds.yarcot.apis;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerInventory implements IInventory {

    protected ItemStackHandler itemHandler;

    public ItemHandlerInventory(boolean nullItemHandler) {
        if (!nullItemHandler)
            this.itemHandler = new ItemStackHandler(0);
    }

    public ItemHandlerInventory(IItemHandler handler) {
        this.itemHandler = (ItemStackHandler) handler;
    }

    public ItemStackHandler getItemStackHandler() {
        return itemHandler;
    }

    @Override
    public int getSizeInventory() {
        return itemHandler.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (itemHandler.getStackInSlot(i).isEmpty())
                return true;
        }
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return itemHandler.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack slot = itemHandler.getStackInSlot(index);
        if (slot.isEmpty() || count <= 0)
            return ItemStack.EMPTY;

        int itemsToRemove = Math.min(count, slot.getCount());

        ItemStack removedStack = slot.copy();
        removedStack.setCount(itemsToRemove);

        slot.setCount(slot.getCount() - itemsToRemove);

        return removedStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack removed = itemHandler.getStackInSlot(index);
        itemHandler.setStackInSlot(index, ItemStack.EMPTY);
        return removed;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        itemHandler.setStackInSlot(index, stack);
    }

    @Override
    public void markDirty() { }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < itemHandler.getSlots(); i++)
            itemHandler.setStackInSlot(i, ItemStack.EMPTY);
    }
}
