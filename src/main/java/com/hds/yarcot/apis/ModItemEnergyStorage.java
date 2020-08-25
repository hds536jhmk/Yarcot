package com.hds.yarcot.apis;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ModItemEnergyStorage extends ModEnergyStorage {
    private final ItemStack STACK;

    public ModItemEnergyStorage(ItemStack stack, int capacity) {
        this(stack, capacity, 0);
    }

    public ModItemEnergyStorage(ItemStack stack, int capacity, int maxTransfer) {
        this(stack, capacity, maxTransfer, maxTransfer);
    }

    public ModItemEnergyStorage(ItemStack stack, int capacity, int maxReceive, int maxExtract) {
        this(stack, capacity, maxReceive, maxExtract, 0);
    }

    public ModItemEnergyStorage(ItemStack stack, int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
        this.STACK = stack;
    }

    @Override
    public int getEnergyStored() {
        CompoundNBT nbt = STACK.getTag();
        if (nbt == null)
            return 0;

        CompoundNBT entityTag = nbt.getCompound("BlockEntityTag");
        return entityTag.getCompound("energyStorage").getInt("energy");
    }

    @Override
    public void setEnergyRaw(int energy) {
        CompoundNBT nbt = STACK.getTag() == null ? new CompoundNBT() : STACK.getTag();

        CompoundNBT entityTag = nbt.getCompound("BlockEntityTag");
        CompoundNBT energyStorage = entityTag.getCompound("energyStorage");
        energyStorage.putInt("energy", energy);

        entityTag.put("energyStorage", energyStorage);
        nbt.put("BlockEntityTag", entityTag);
        STACK.setTag(nbt);
    }
}
