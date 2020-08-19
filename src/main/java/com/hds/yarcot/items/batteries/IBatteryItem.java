package com.hds.yarcot.items.batteries;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.extensions.IForgeItem;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public interface IBatteryItem extends IForgeItem {

    @Override
    default int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Nullable
    @Override
    ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt);

    default int getEnergyStored(ItemStack stack) {
        return stack.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    default int getMaxEnergyStored(ItemStack stack) {
        return stack.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
    }

    default float getEnergyPercentage(ItemStack stack) {
        float capacity = (float) getMaxEnergyStored(stack);
        if (capacity == 0)
            return 0.0F;
        return (float) getEnergyStored(stack) / capacity;
    }

    @Override
    default boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    default double getDurabilityForDisplay(ItemStack stack) {
        return 1.0F - getEnergyPercentage(stack);
    }

    @Override
    default int getRGBDurabilityForDisplay(ItemStack stack) {
        float energyPercentage = getEnergyPercentage(stack);
        if (energyPercentage < 0.33F)
            return 0xff0000;
        else if (energyPercentage < 0.66F)
            return 0xffff00;
        return 0x00ff00;
    }
}
