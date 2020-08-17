package com.hds.yarcot.blocks.furnaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public abstract class ModFurnaceInventory implements ISidedInventory, INBTSerializable<CompoundNBT> {
    private final ItemStackHandler ITEM_STACK_HANDLER = new ItemStackHandler(2);

    public ItemStackHandler getItemStackHandler() {
        return ITEM_STACK_HANDLER;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return new int[]{1};
        } else {
            return new int[]{0};
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return direction != Direction.DOWN && index == 0;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return direction == Direction.DOWN && index == 1;
    }

    @Override
    public int getSizeInventory() {
        return ITEM_STACK_HANDLER.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < ITEM_STACK_HANDLER.getSlots(); i++) {
            if (!ITEM_STACK_HANDLER.getStackInSlot(i).isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return ITEM_STACK_HANDLER.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack slotToModify = ITEM_STACK_HANDLER.getStackInSlot(index);
        ItemStack removed = slotToModify.copy();
        removed.setCount(count);
        slotToModify.setCount(slotToModify.getCount() - count);
        return removed;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack slotToModify = ITEM_STACK_HANDLER.getStackInSlot(index);
        ItemStack oldStack = slotToModify.copy();
        slotToModify.setCount(0);
        return oldStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ITEM_STACK_HANDLER.setStackInSlot(index, stack);
    }

    @Override
    public abstract void markDirty();

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < ITEM_STACK_HANDLER.getSlots(); i++)
            ITEM_STACK_HANDLER.getStackInSlot(i).setCount(0);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("itemStackHandler", ITEM_STACK_HANDLER.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        ITEM_STACK_HANDLER.deserializeNBT(nbt.getCompound("itemStackHandler"));
    }
}
