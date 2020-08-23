package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.apis.IEnergeticTileEntity;
import com.hds.yarcot.apis.IModEnergyStorage;
import com.hds.yarcot.apis.ModEnergyStorage;
import com.hds.yarcot.apis.ModSidedEnergyWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ModBatteryTile extends TileEntity implements ITickableTileEntity, IEnergeticTileEntity {
    private final int MAX_INPUT;
    private final int MAX_OUTPUT;
    private final int CAPACITY;

    private final ModBatteryInventory BATTERY_INVENTORY;
    private final ModBatteryEnergy ENERGY_STORAGE;

    private final LazyOptional<IItemHandlerModifiable>[] LAZY_INVENTORY;
    private final LazyOptional<IModEnergyStorage>[] LAZY_ENERGY_STORAGE;

    public ModBatteryTile(TileEntityType<?> tileEntityTypeIn, int input, int output, int capacity) {
        super(tileEntityTypeIn);
        this.MAX_INPUT = input;
        this.MAX_OUTPUT = output;
        this.CAPACITY = capacity;

        this.BATTERY_INVENTORY = new ModBatteryInventory() {
            @Override
            public void markDirty() {
                ModBatteryTile.this.markDirty();
            }
        };
        this.ENERGY_STORAGE = new ModBatteryEnergy(this, this.CAPACITY, this.MAX_INPUT, this.MAX_OUTPUT, 0) {
            @Override
            public void onEnergyChanged(int energyAdded) {
                markDirty();
            }
        };

        this.LAZY_INVENTORY = SidedInvWrapper.create(BATTERY_INVENTORY, Direction.UP, Direction.SOUTH, Direction.DOWN);
        this.LAZY_ENERGY_STORAGE = ModSidedEnergyWrapper.create(
                ENERGY_STORAGE,
                Direction.UP, Direction.DOWN,
                Direction.NORTH, Direction.SOUTH,
                Direction.EAST, Direction.WEST
        );
    }

    private LazyOptional<IModEnergyStorage> getLazyEnergyFromDirection(Direction direction) {
        if (direction == null)
            return LazyOptional.of(() -> ENERGY_STORAGE);

        switch (direction) {
            case UP:
                return this.LAZY_ENERGY_STORAGE[0];
            case DOWN:
                return this.LAZY_ENERGY_STORAGE[1];
            case NORTH:
                return this.LAZY_ENERGY_STORAGE[2];
            case SOUTH:
                return this.LAZY_ENERGY_STORAGE[3];
            case EAST:
                return this.LAZY_ENERGY_STORAGE[4];
            default:
                return this.LAZY_ENERGY_STORAGE[5];
        }
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;

        ItemStack inputSlot = BATTERY_INVENTORY.getStackInSlot(0);
        if (!inputSlot.isEmpty())
            inputSlot.getCapability(CapabilityEnergy.ENERGY).ifPresent(
                    handler -> {
                        ModEnergyStorage.transferEnergy(handler, this.ENERGY_STORAGE, this.MAX_INPUT);
                    }
            );

        ItemStack outputSlot = BATTERY_INVENTORY.getStackInSlot(1);
        if (!outputSlot.isEmpty())
            outputSlot.getCapability(CapabilityEnergy.ENERGY).ifPresent(
                    handler -> {
                        ModEnergyStorage.transferEnergy(this.ENERGY_STORAGE, handler, this.MAX_OUTPUT);
                    }
            );
    }

    public ItemStackHandler getItemStorage() {
        return this.BATTERY_INVENTORY.getItemStackHandler();
    }

    @Override
    public ModEnergyStorage getEnergyStorage() {
        return this.ENERGY_STORAGE;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        ENERGY_STORAGE.deserializeNBT(compound.getCompound("energyStorage"));
        BATTERY_INVENTORY.deserializeNBT(compound.getCompound("batteryInventory"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyStorage", ENERGY_STORAGE.serializeNBT());
        compound.put("batteryInventory", BATTERY_INVENTORY.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
            if (side == Direction.UP)
                return LAZY_INVENTORY[0].cast();
            else if (side == Direction.DOWN)
                return LAZY_INVENTORY[2].cast();
            else
                return LAZY_INVENTORY[1].cast();
        else if (cap.equals(CapabilityEnergy.ENERGY))
            return this.getLazyEnergyFromDirection(side).cast();
        return super.getCapability(cap, side);
    }
}
