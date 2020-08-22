package com.hds.yarcot.apis;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public abstract class ModEnergyContainer extends ModInventoryContainer {
    protected final TileEntity CONTAINER_TILE_ENTITY;

    public ModEnergyContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, TileEntity tileEntity) {
        super(type, windowId, playerInventory);
        CONTAINER_TILE_ENTITY = tileEntity;
    }

    protected void trackEnergy() {
        trackInt(new IntReferenceHolder() {
            @Override
            // This is ran on the Server to get the energy from the tile entity
            public int get() {
                // The returned value gets sent to the client
                return CONTAINER_TILE_ENTITY.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0) & 0xffff;
            }

            @Override
            // This is ran on the client to set values on the tileentity that's stored on the client
            public void set(int serverEnergy) {
                // Sets battery's energy to be the same as the server's
                CONTAINER_TILE_ENTITY.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorageHandler -> {
                    int energyStored = energyStorageHandler.getEnergyStored() & 0xffff0000;
                    ((ModEnergyStorage)energyStorageHandler).setEnergy(energyStored + (serverEnergy & 0xffff));
                });
            }
        });
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return (CONTAINER_TILE_ENTITY.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0) >> 16) & 0xffff;
            }

            @Override
            public void set(int serverEnergy) {
                CONTAINER_TILE_ENTITY.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorageHandler -> {
                    int energyStored = energyStorageHandler.getEnergyStored() & 0x0000ffff;
                    ((ModEnergyStorage)energyStorageHandler).setEnergy(energyStored | (serverEnergy << 16));
                });
            }
        });
    }
}
