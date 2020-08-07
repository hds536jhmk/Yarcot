package com.hds.yarcot.blocks.miners.sapphire;

import com.hds.yarcot.materials.ModItemTiers;
import com.hds.yarcot.registries.ModTileEntities;
import com.hds.yarcot.util.customclasses.ModEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
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

public class SapphireMinerTile extends TileEntity implements ITickableTileEntity {
    private int currentYOffset;
    private ModEnergyStorage energyStorage = new ModEnergyStorage(1000, 100, 0) {
        @Override
        public void onEnergyChanged(int energyAdded) {
            SapphireMinerTile.this.markDirty();
        }
    };

    private final int CONSUMPTION_MOVE = 50;
    private final int CONSUMPTION_BLOCK = 50;

    public SapphireMinerTile() {
        super(ModTileEntities.SAPPHIRE_MINER_TILE.get());
        currentYOffset = -1;
    }

    private boolean canMove() {
        return energyStorage.getEnergyStored() >= CONSUMPTION_MOVE;
    }

    private boolean canDig() {
        return energyStorage.getEnergyStored() >= CONSUMPTION_BLOCK;
    }

    private void setPowered(boolean state) {
        world.setBlockState(this.getPos(), world.getBlockState(this.getPos()).with(BlockStateProperties.POWERED, state));
    }

    private boolean putItemInInventory(IItemHandler inventory, ItemStack item, boolean simulate) {
        boolean wasInserted = false;
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack remainder = inventory.insertItem(i, item, simulate);
            if (remainder.isEmpty()) {
                wasInserted = true;
            }
        }

        return wasInserted;
    }

    @Override
    public void tick() {
        if (world.isRemote)
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

        if (this.canMove() && world.isAirBlock(currentBlockPos)) {
            this.setPowered(true);
            energyStorage.extractEnergy(CONSUMPTION_MOVE, false);
            currentYOffset--;
        } else if (this.canDig()) {
            BlockState currentBlockState = world.getBlockState(currentBlockPos);
            Block currentBlock = currentBlockState.getBlock();
            if (currentBlockState.getBlockHardness(this.world, currentBlockPos) >= 0 && currentBlock.getHarvestLevel(currentBlockState) <= ModItemTiers.SAPPHIRE.getHarvestLevel()) {
                this.setPowered(true);

                List<ItemStack> itemsToStore = Block.getDrops(currentBlockState, (ServerWorld) world, currentBlockPos, world.getTileEntity(currentBlockPos));

                storage.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(
                        storageHandler -> {
                            // Making sure that there's space in the chest for all the drops
                            boolean enoughSpace = true;
                            for (ItemStack item : itemsToStore) {
                                enoughSpace = putItemInInventory(storageHandler, item, true);
                                if (!enoughSpace) {
                                    this.setPowered(false);
                                    return;
                                }
                            }

                            // If there was space then don't simulate insertion
                            for (ItemStack item : itemsToStore) {
                                putItemInInventory(storageHandler, item, false);
                            }

                            // Consume digging energy
                            energyStorage.extractEnergy(CONSUMPTION_BLOCK, false);
                            world.removeBlock(currentBlockPos, false);
                            // If you can move then do it
                            if (this.canMove()) {
                                energyStorage.extractEnergy(CONSUMPTION_MOVE, false);
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
        energyStorage.deserializeNBT(compound.getCompound("energyStorage"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyStorage", energyStorage.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityEnergy.ENERGY))
            return LazyOptional.of(() -> energyStorage).cast();
        return super.getCapability(cap, side);
    }
}
