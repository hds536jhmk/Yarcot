package com.hds.testmod.blocks.batteries.sapphire;

import com.hds.testmod.registries.ModContainers;
import com.hds.testmod.util.customclasses.ModEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class SapphireBatteryContainer extends Container {

    private BlockPos blockPos;
    private PlayerEntity player;
    private TileEntity battery;

    public SapphireBatteryContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModContainers.SAPPHIRE_BATTERY_CONTAINER.get(), windowId);
        this.blockPos = pos;
        this.player = playerInventory.player;
        this.battery = this.player.world.getTileEntity(this.blockPos);
        trackEnergy();
    }

    public void trackEnergy() {
        trackInt(new IntReferenceHolder() {
            @Override
            // This is ran on the Server to get the energy from the tileentity
            public int get() {
                // The returned value gets sent to the client
                return getEnergy() & 0xffff;
            }

            @Override
            // This is ran on the client to set values on the tileentity that's stored on the client
            public void set(int serverEnergy) {
                // Sets battery's energy to be the same as the server's
                battery.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorageHandler -> {
                    int energyStored = energyStorageHandler.getEnergyStored() & 0xffff0000;
                    ((ModEnergyStorage)energyStorageHandler).setEnergy(energyStored + (serverEnergy & 0xffff));
                });
            }
        });
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }

            @Override
            public void set(int serverEnergy) {
                battery.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorageHandler -> {
                    int energyStored = energyStorageHandler.getEnergyStored() & 0x0000ffff;
                    ((ModEnergyStorage)energyStorageHandler).setEnergy(energyStored | (serverEnergy << 16));
                });
            }
        });
    }

    public int getEnergy() {
        return battery.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
