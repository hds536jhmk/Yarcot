package com.hds.yarcot.blocks.barrels;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ModBarrelTile extends TileEntity {

    public final int COLUMNS;
    public final int ROWS;

    private final ItemStackHandler BARREL_INVENTORY;

    public ModBarrelTile(TileEntityType<?> tileEntityTypeIn, int columns, int rows) {
        super(tileEntityTypeIn);
        this.COLUMNS = columns;
        this.ROWS = rows;
        this.BARREL_INVENTORY = new ItemStackHandler(this.COLUMNS * this.ROWS) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
    }

    @Override
    public void read(@Nonnull CompoundNBT compound) {
        BARREL_INVENTORY.deserializeNBT(compound.getCompound("itemStackHandler"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        compound.put("itemStackHandler", BARREL_INVENTORY.serializeNBT());
        return super.write(compound);
    }

    public ItemStackHandler getBarrelInventory() {
        return BARREL_INVENTORY;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) {
            return LazyOptional.of(() -> BARREL_INVENTORY).cast();
        }
        return super.getCapability(cap, side);
    }
}
