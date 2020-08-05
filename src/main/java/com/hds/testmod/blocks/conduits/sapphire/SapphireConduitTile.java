package com.hds.testmod.blocks.conduits.sapphire;

import com.hds.testmod.registries.ModTileEntities;
import com.hds.testmod.util.customclasses.DirectionToBooleanProperty;
import com.hds.testmod.util.customclasses.ModEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public class SapphireConduitTile extends TileEntity implements ITickableTileEntity {
    private static final int MAX_INPUT = 100;
    private static final int MAX_OUTPUT = 100;
    private static final int CAPACITY = 200;

    private final ModEnergyStorage CONDUIT_ENERGY_BUFFER = new ModEnergyStorage(CAPACITY, MAX_INPUT, MAX_OUTPUT, 0) {
        @Override
        public void onEnergyChanged(int energyAdded) {
            markDirty();
        }
    };

    public SapphireConduitTile() {
        super(ModTileEntities.SAPPHIRE_CONDUIT_TILE.get());
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;
        BlockPos thisPos = this.getPos();
        AtomicReference<BlockState> thisBlockState = new AtomicReference<>(world.getBlockState(thisPos));
        for (Direction direction : Direction.values()) {
            BooleanProperty attachedFace = DirectionToBooleanProperty.get(direction);
            thisBlockState.set(thisBlockState.get().with(attachedFace, false));
            TileEntity tileEntity = world.getTileEntity(thisPos.offset(direction));
            if (tileEntity == null)
                continue;
            tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(
                    energyHandler -> {
                        thisBlockState.set(thisBlockState.get().with(attachedFace, true));
                        if (energyHandler.canReceive() && (energyHandler.getEnergyStored() < CONDUIT_ENERGY_BUFFER.getEnergyStored() || CONDUIT_ENERGY_BUFFER.getEnergyStored() >= CONDUIT_ENERGY_BUFFER.getMaxEnergyStored())) {
                            int receivedEnergy = energyHandler.receiveEnergy(MAX_OUTPUT, true);
                            int takenEnergy = CONDUIT_ENERGY_BUFFER.extractEnergy(receivedEnergy, false);
                            energyHandler.receiveEnergy(takenEnergy, false);

                        } else if (energyHandler.canExtract()) {
                            int takenEnergy = energyHandler.extractEnergy(MAX_INPUT, true);
                            int receivedEnergy = CONDUIT_ENERGY_BUFFER.receiveEnergy(takenEnergy, false);
                            energyHandler.extractEnergy(receivedEnergy, false);

                        }
                    }
            );
        }
        world.setBlockState(thisPos, thisBlockState.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        CONDUIT_ENERGY_BUFFER.deserializeNBT(compound.getCompound("energyBuffer"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyBuffer", CONDUIT_ENERGY_BUFFER.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityEnergy.ENERGY)) {
            return LazyOptional.of(() -> CONDUIT_ENERGY_BUFFER).cast();
        }
        return super.getCapability(cap, side);
    }
}
