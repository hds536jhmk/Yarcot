package com.hds.yarcot.apis;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.function.BiConsumer;

public interface IModEnergyStorage extends IEnergyStorage, INBTSerializable<CompoundNBT> {

    /**
     * Called every time an energy change has occurred
     * @param energyAdded How much energy was added in the last energy change (can be negative)
     */
    void onEnergyChanged(int energyAdded);

    /**
     * Sets the current energy without calling onEnergyChanged
     * @param energy The new amount of energy stored in the storage
     */
    void setEnergyRaw(int energy);

    /**
     * Consumes the given amount of energy despite of how much energy cn be extracted from the storage
     * @param maxExtract How much energy should be consumed
     * @param simulate Whether or not the change in energy should be applied
     * @return Returns how much energy was consumed
     */
    int consumeEnergy(int maxExtract, boolean simulate);

    /**
     * This function transfers energy equally to all surrounding neighbours from `world.getTileEntity(pos)`
     * @param world The world where the TileEntity is
     * @param pos The pos in `world` where the TileEntity is
     * @param handleEnergyStorage Used to handle logic of the TileEntity based on surrounding blocks' properties
     *                            (An example can be found in Conduits) this callback shouldn't modify energy from the given handler
     * @return Returns how much energy was given to neighbours
     */
    int transferToNeighbours(World world, BlockPos pos, BiConsumer<IEnergyStorage, Direction> handleEnergyStorage);
}
