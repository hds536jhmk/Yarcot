package com.hds.testmod.blocks.barrels.sapphire_barrel;

import com.hds.testmod.registries.ModTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SapphireBarrelTile extends TileEntity {

    public static int COLUMNS = 11;
    public static int ROWS = 5;

    private ItemStackHandler barrelInventory = new ItemStackHandler(COLUMNS * ROWS) {
        @Override
        protected void onContentsChanged(int slot) {
            markDirty();
        }
    };

    public SapphireBarrelTile() {
        super(ModTileEntities.SAPPHIRE_BARREL_TILE.get());
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    @Override
    public void read(CompoundNBT compound) {
        barrelInventory.deserializeNBT(compound.getCompound("itemStackHandler"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("itemStackHandler", barrelInventory.serializeNBT());
        return super.write(compound);
    }

    public ItemStackHandler getBarrelInventory() {
        return barrelInventory;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) {
            return LazyOptional.of(() -> barrelInventory).cast();
        }
        return super.getCapability(cap, side);
    }
}
