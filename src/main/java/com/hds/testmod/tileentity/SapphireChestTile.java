package com.hds.testmod.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SapphireChestTile extends TileEntity {

    private ItemStackHandler itemStackHandler = this.createItemStackHandler(27);

    public SapphireChestTile() {
        super(ModTileEntities.SAPPHIRE_CHEST_TILE.get());
    }

    private ItemStackHandler createItemStackHandler(int size) {
        return new ItemStackHandler(size) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    @Override
    public void read(CompoundNBT compound) {
        itemStackHandler.deserializeNBT(compound.getCompound("itemStackHandler"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("itemStackHandler", itemStackHandler.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) {
            return LazyOptional.of(() -> itemStackHandler).cast();
        }
        return super.getCapability(cap, side);
    }
}
