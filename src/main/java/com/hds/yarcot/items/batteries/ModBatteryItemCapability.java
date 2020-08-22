package com.hds.yarcot.items.batteries;

import com.hds.yarcot.apis.ModEnergyStorage;
import com.hds.yarcot.apis.ModItemEnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModBatteryItemCapability implements ICapabilityProvider {

    private final ModEnergyStorage ENERGY_STORAGE;

    public ModBatteryItemCapability(ItemStack stack, int input, int output, int capacity) {
        ENERGY_STORAGE = new ModItemEnergyStorage(stack, capacity, input, output, 0);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> ENERGY_STORAGE).cast();
        return LazyOptional.empty();
    }
}
