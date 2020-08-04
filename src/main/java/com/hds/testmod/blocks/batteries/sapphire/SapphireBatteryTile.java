package com.hds.testmod.blocks.batteries.sapphire;

import com.hds.testmod.registries.ModTileEntities;
import com.hds.testmod.util.customclasses.ModEnergyStorage;
import com.hds.testmod.util.customclasses.TickTimer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SapphireBatteryTile extends TileEntity implements ITickableTileEntity {
    private ModEnergyStorage energyStorage = new ModEnergyStorage(5000) {
        @Override
        public void onEnergyChanged(int energyAdded) {
            markDirty();
        }
    };

    // TODO: Make it actually work, it shouldn't generate power
    private TickTimer energyGenerationTimer = new TickTimer(2) {
        @Override
        public void onTimeout() {
            energyStorage.receiveEnergy(10, false);
        }
    };

    public SapphireBatteryTile() {
        super(ModTileEntities.SAPPHIRE_BATTERY_TILE.get());
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;
        energyGenerationTimer.tick();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        energyStorage.deserializeNBT(compound.getCompound("energyStorage"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyStorage", energyStorage.serializeNBT());
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
            return LazyOptional.of(() -> energyStorage).cast();
        }
        return super.getCapability(cap, side);
    }
}
