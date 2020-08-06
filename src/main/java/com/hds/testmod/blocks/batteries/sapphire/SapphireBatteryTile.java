package com.hds.testmod.blocks.batteries.sapphire;

import com.hds.testmod.registries.ModTileEntities;
import com.hds.testmod.util.customclasses.ModEnergyStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SapphireBatteryTile extends TileEntity {
    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(5000, 10, 50) {
        @Override
        public void onEnergyChanged(int energyAdded) {
            markDirty();
        }
    };

    public SapphireBatteryTile() {
        super(ModTileEntities.SAPPHIRE_BATTERY_TILE.get());
    }

    public float getChargePercentage() {
        return (float)ENERGY_STORAGE.getEnergyStored() / (float)ENERGY_STORAGE.getMaxEnergyStored();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        ENERGY_STORAGE.deserializeNBT(compound.getCompound("energyStorage"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyStorage", ENERGY_STORAGE.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityEnergy.ENERGY)) {
            return LazyOptional.of(() -> ENERGY_STORAGE).cast();
        }
        return super.getCapability(cap, side);
    }
}
