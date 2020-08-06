package com.hds.testmod.blocks.batteries;

import com.hds.testmod.util.ModLog;
import com.hds.testmod.util.customclasses.ModEnergyStorage;
import com.hds.testmod.util.customclasses.ModInventoryContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public abstract class ModBatteryContainer extends ModInventoryContainer {
    protected final ModBatteryTile BATTERY_TILE;

    public ModBatteryContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(type, windowId, playerInventory);

        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        if (!(te instanceof ModBatteryTile))
            ModLog.error("INVALID TILE ENTITY INSTANCE: " + te.toString());

        this.BATTERY_TILE = (ModBatteryTile) te;

        this.trackEnergy();
    }

    public void trackEnergy() {
        trackInt(new IntReferenceHolder() {
            @Override
            // This is ran on the Server to get the energy from the tile entity
            public int get() {
                // The returned value gets sent to the client
                return BATTERY_TILE.getCharge() & 0xffff;
            }

            @Override
            // This is ran on the client to set values on the tileentity that's stored on the client
            public void set(int serverEnergy) {
                // Sets battery's energy to be the same as the server's
                BATTERY_TILE.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorageHandler -> {
                    int energyStored = energyStorageHandler.getEnergyStored() & 0xffff0000;
                    ((ModEnergyStorage)energyStorageHandler).setEnergy(energyStored + (serverEnergy & 0xffff));
                });
            }
        });
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return (BATTERY_TILE.getCharge() >> 16) & 0xffff;
            }

            @Override
            public void set(int serverEnergy) {
                BATTERY_TILE.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorageHandler -> {
                    int energyStored = energyStorageHandler.getEnergyStored() & 0x0000ffff;
                    ((ModEnergyStorage)energyStorageHandler).setEnergy(energyStored | (serverEnergy << 16));
                });
            }
        });
    }

    @Override
    public abstract boolean canInteractWith(PlayerEntity playerIn);

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
