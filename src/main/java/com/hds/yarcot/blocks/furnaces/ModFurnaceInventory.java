package com.hds.yarcot.blocks.furnaces;

import com.hds.yarcot.apis.ItemHandlerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public abstract class ModFurnaceInventory extends ItemHandlerInventory implements ISidedInventory, INBTSerializable<CompoundNBT> {

    public ModFurnaceInventory() {
        super(true);
        this.itemHandler = new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
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
    public abstract void markDirty();

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("itemStackHandler", itemHandler.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("itemStackHandler"));
    }
}
