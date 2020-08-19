package com.hds.yarcot.blocks.batteries.sapphire;

import com.hds.yarcot.blocks.batteries.ModBatteryBlockItem;
import com.hds.yarcot.items.batteries.ModBatteryItemCapability;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class SapphireBatteryItem extends ModBatteryBlockItem {
    public SapphireBatteryItem(Block block) {
        super(block);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new ModBatteryItemCapability(
                stack, SapphireBattery.config.ENERGY_INPUT.get(),
                SapphireBattery.config.ENERGY_OUTPUT.get(), SapphireBattery.config.CAPACITY.get()
        );
    }
}
