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
    private static final int MAXINPUT = 100;
    private static final int MAXOUTPUT = 100;
    private static final int CAPACITY = 200;
    private ModEnergyStorage conduitEnergyBuffer = new ModEnergyStorage(CAPACITY, MAXINPUT, MAXOUTPUT, 0) {
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
        AtomicReference<BlockState> thisBlockState = new AtomicReference<BlockState>(world.getBlockState(thisPos));
        for (Direction direction : Direction.values()) {
            BooleanProperty attachedFace = DirectionToBooleanProperty.get(direction);
            thisBlockState.set(thisBlockState.get().with(attachedFace, false));
            TileEntity tileEntity = world.getTileEntity(thisPos.offset(direction));
            if (tileEntity == null)
                continue;
            tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(
                    energyHandler -> {
                        thisBlockState.set(thisBlockState.get().with(attachedFace, true));
                        if (energyHandler.canReceive() && (energyHandler.getEnergyStored() < conduitEnergyBuffer.getEnergyStored() || conduitEnergyBuffer.getEnergyStored() >= conduitEnergyBuffer.getMaxEnergyStored())) {
                            int receivedEnergy = energyHandler.receiveEnergy(MAXOUTPUT, true);
                            int takenEnergy = conduitEnergyBuffer.extractEnergy(receivedEnergy, false);
                            energyHandler.receiveEnergy(takenEnergy, false);

                        } else if (energyHandler.canExtract()) {
                            int takenEnergy = energyHandler.extractEnergy(MAXINPUT, true);
                            int receivedEnergy = conduitEnergyBuffer.receiveEnergy(takenEnergy, false);
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
        conduitEnergyBuffer.deserializeNBT(compound.getCompound("energyBuffer"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energyBuffer", conduitEnergyBuffer.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityEnergy.ENERGY)) {
            return LazyOptional.of(() -> conduitEnergyBuffer).cast();
        }
        return super.getCapability(cap, side);
    }
}
