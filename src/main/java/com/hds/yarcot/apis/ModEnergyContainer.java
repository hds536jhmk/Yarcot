package com.hds.yarcot.apis;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IntReferenceHolder;

import javax.annotation.Nullable;

public abstract class ModEnergyContainer extends ModInventoryContainer {
    protected final IEnergeticTileEntity CONTAINER_TILE_ENTITY;

    public ModEnergyContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, IEnergeticTileEntity tileEntity) {
        super(type, windowId, playerInventory);
        CONTAINER_TILE_ENTITY = tileEntity;
    }

    protected void trackEnergy() {
        trackInt(new IntReferenceHolder() {
            @Override
            // This is ran on the Server to get the energy from the tile entity
            public int get() {
                // The returned value gets sent to the client
                return CONTAINER_TILE_ENTITY.getEnergyStored() & 0xffff;
            }

            @Override
            // This is ran on the client to set values on the tileentity that's stored on the client
            public void set(int serverEnergy) {
                // Sets battery's energy to be the same as the server's
                CONTAINER_TILE_ENTITY.getEnergyStorage().setEnergy(
                        (CONTAINER_TILE_ENTITY.getEnergyStored() & 0xffff0000) + (serverEnergy & 0xffff)
                );
            }
        });
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return (CONTAINER_TILE_ENTITY.getEnergyStored() >> 16) & 0xffff;
            }

            @Override
            public void set(int serverEnergy) {
                CONTAINER_TILE_ENTITY.getEnergyStorage().setEnergy(
                        (CONTAINER_TILE_ENTITY.getEnergyStored() & 0x0000ffff) | (serverEnergy << 16)
                );
            }
        });
    }
}
