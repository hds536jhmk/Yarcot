package com.hds.yarcot.blocks.conduits;

import com.hds.yarcot.apis.IEnergeticTileEntity;
import com.hds.yarcot.apis.ModBlockStateProperties;
import com.hds.yarcot.apis.ModEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ModConduitTile extends TileEntity implements ITickableTileEntity, IEnergeticTileEntity {
    private final int MAX_INPUT;
    private final int MAX_OUTPUT;
    private final int BUFFER_CAPACITY;

    private final ModEnergyStorage CONDUIT_ENERGY_BUFFER;
    private final LazyOptional<ModEnergyStorage> LAZY_ENERGY_BUFFER;

    public ModConduitTile(TileEntityType<?> type, int input, int output, int bufferCapacity) {
        super(type);
        this.MAX_INPUT = input;
        this.MAX_OUTPUT = output;
        this.BUFFER_CAPACITY = bufferCapacity;

        this.CONDUIT_ENERGY_BUFFER = new ModEnergyStorage(this.BUFFER_CAPACITY, this.MAX_INPUT, this.MAX_OUTPUT, 0) {
            @Override
            public void onEnergyChanged(int energyAdded) {
                markDirty();
            }
        };

        this.LAZY_ENERGY_BUFFER = LazyOptional.of(() -> CONDUIT_ENERGY_BUFFER);
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;

        BlockPos thisPos = this.getPos();
        this.CONDUIT_ENERGY_BUFFER.transferToNeighbours(world, thisPos);

        BlockState thisBlockState = world.getBlockState(thisPos);
        for (Direction direction : Direction.values()) {
            TileEntity te = world.getTileEntity(thisPos.offset(direction));

            BooleanProperty property = ModBlockStateProperties.BOOLEAN_DIRECTION.getFromDirection(direction);
            thisBlockState = thisBlockState.with(property, false);
            if (te == null)
                continue;

            AtomicBoolean hasConnection = new AtomicBoolean(false);
            te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(
                    handler -> {
                        hasConnection.set(handler.canExtract() || handler.canReceive());
                    }
            );
            thisBlockState = thisBlockState.with(property, hasConnection.get());
        }

        world.setBlockState(thisPos, thisBlockState);
    }

    @Override
    public ModEnergyStorage getEnergyStorage() {
        return CONDUIT_ENERGY_BUFFER;
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
        if (cap.equals(CapabilityEnergy.ENERGY))
            return LAZY_ENERGY_BUFFER.cast();
        return super.getCapability(cap, side);
    }
}
