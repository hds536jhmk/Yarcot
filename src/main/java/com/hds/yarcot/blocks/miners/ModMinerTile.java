package com.hds.yarcot.blocks.miners;

import com.hds.yarcot.util.customclasses.ModEnergyStorage;
import com.hds.yarcot.util.customclasses.TickTimer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ModMinerTile extends TileEntity implements ITickableTileEntity {
    private final ModEnergyStorage ENERGY_STORAGE;
    private final TickTimer ACTION_TIMER;

    private final IItemTier MINER_TIER;
    private final int MOVE_CONSUMPTION;
    private final int DIG_CONSUMPTION;

    private int currentYOffset;

    public ModMinerTile(TileEntityType<?> tileEntityTypeIn, int input, int moveConsumption, int digConsumption, int capacity, IItemTier minerTier, float actionTime) {
        super(tileEntityTypeIn);

        this.ENERGY_STORAGE = new ModEnergyStorage(capacity, input, 0) {
            @Override
            public void onEnergyChanged(int energyAdded) {
                ModMinerTile.this.markDirty();
            }
        };
        this.ACTION_TIMER = new TickTimer(actionTime);

        this.MINER_TIER = minerTier;
        this.MOVE_CONSUMPTION = moveConsumption;
        this.DIG_CONSUMPTION = digConsumption;

        this.currentYOffset = -1;
    }

    private boolean hasEnergyToMove() {
        return ENERGY_STORAGE.getEnergyStored() >= MOVE_CONSUMPTION;
    }

    private boolean hasEnergyToDig() {
        return ENERGY_STORAGE.getEnergyStored() >= DIG_CONSUMPTION;
    }

    private void setPowered(boolean state) {
        world.setBlockState(this.getPos(), world.getBlockState(this.getPos()).with(BlockStateProperties.POWERED, state));
    }

    private boolean putItemInStorage(IItemHandler storage, ItemStack item, boolean simulate) {
        ItemStack remainder;
        for (int i = 0; i < storage.getSlots(); i++) {
            remainder = storage.insertItem(i, item, simulate);
            if (remainder.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;

        if (!ACTION_TIMER.tick())
            return;

        BlockPos currentBlockPos = this.getPos().add(0, currentYOffset, 0);
        if (currentBlockPos.getY() <= 0) {
            this.setPowered(false);
            return;
        }

        TileEntity storage = world.getTileEntity(this.getPos().offset(Direction.UP));
        if (storage == null || !storage.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).isPresent()) {
            this.setPowered(false);
            return;
        }

        if (this.hasEnergyToMove() && world.isAirBlock(currentBlockPos)) {
            this.setPowered(true);
            ENERGY_STORAGE.setEnergy(ENERGY_STORAGE.getEnergyStored() - MOVE_CONSUMPTION);
            currentYOffset--;
        } else if (this.hasEnergyToDig()) {
            BlockState currentBlockState = world.getBlockState(currentBlockPos);
            Block currentBlock = currentBlockState.getBlock();
            if (currentBlockState.getBlockHardness(this.world, currentBlockPos) >= 0 && currentBlock.getHarvestLevel(currentBlockState) <= MINER_TIER.getHarvestLevel()) {
                this.setPowered(true);

                List<ItemStack> itemsToStore = Block.getDrops(currentBlockState, (ServerWorld) world, currentBlockPos, world.getTileEntity(currentBlockPos));

                storage.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(
                        storageHandler -> {
                            // Making sure that there's space in the chest for all the drops
                            for (ItemStack item : itemsToStore) {
                                boolean enoughSpace = putItemInStorage(storageHandler, item, true);
                                if (!enoughSpace) {
                                    this.setPowered(false);
                                    return;
                                }
                            }

                            // If there was space then don't simulate insertion
                            for (ItemStack item : itemsToStore) {
                                putItemInStorage(storageHandler, item, false);
                            }

                            // Consume digging energy
                            ENERGY_STORAGE.setEnergy(ENERGY_STORAGE.getEnergyStored() - DIG_CONSUMPTION);
                            world.removeBlock(currentBlockPos, false);
                            // If you can move then do it
                            if (this.hasEnergyToMove()) {;
                                ENERGY_STORAGE.setEnergy(ENERGY_STORAGE.getEnergyStored() - MOVE_CONSUMPTION);
                                currentYOffset--;
                            }
                        }
                );
            } else {
                this.setPowered(false);
            }
        } else {
            this.setPowered(false);
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        ENERGY_STORAGE.deserializeNBT(compound.getCompound("energyStorage"));
        currentYOffset = compound.getInt("currentYOffset");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyStorage", ENERGY_STORAGE.serializeNBT());
        compound.putInt("currentYOffset", currentYOffset);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityEnergy.ENERGY))
            return LazyOptional.of(() -> ENERGY_STORAGE).cast();
        return super.getCapability(cap, side);
    }
}
