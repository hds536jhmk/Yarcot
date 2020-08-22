package com.hds.yarcot.blocks.furnaces;

import com.hds.yarcot.apis.ModEnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ModFurnaceTile extends TileEntity implements ITickableTileEntity {
    private final ModFurnaceInventory INVENTORY;
    private final ModEnergyStorage ENERGY_STORAGE;

    private final LazyOptional<IItemHandlerModifiable>[] LAZY_INVENTORY;
    private final LazyOptional<ModEnergyStorage> LAZY_ENERGY_STORAGE;

    private final int ENERGY_CONSUMPTION;
    private final float SPEED_FACTOR;

    private int smeltTime;
    private int smeltProgress;

    public ModFurnaceTile(TileEntityType<?> tileEntityTypeIn, int input, int energyConsumption, int capacity, float speedFactor) {
        super(tileEntityTypeIn);
        this.ENERGY_CONSUMPTION = energyConsumption;
        this.SPEED_FACTOR = speedFactor;

        this.INVENTORY = new ModFurnaceInventory() {
            @Override
            public void markDirty() {
                ModFurnaceTile.this.markDirty();
            }
        };
        this.ENERGY_STORAGE = new ModEnergyStorage(capacity, input, 0) {
            @Override
            public void onEnergyChanged(int energyAdded) {
                markDirty();
            }
        };

        this.LAZY_INVENTORY = SidedInvWrapper.create(INVENTORY, Direction.UP, Direction.DOWN);
        this.LAZY_ENERGY_STORAGE = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    public ModFurnaceInventory getFurnaceInventory() {
        return this.INVENTORY;
    }

    @Nullable
    public AbstractCookingRecipe getCurrentRecipe() {
        return world.getRecipeManager().getRecipe(IRecipeType.SMELTING, this.INVENTORY, world).orElse(null);
    }

    public float getSmeltProgressPercentage() {
        if (smeltTime <= 0)
            return 0;
        return (float) smeltProgress / (float) smeltTime;
    }

    public float getChargePercentage() {
        return (float) ENERGY_STORAGE.getEnergyStored() / (float) ENERGY_STORAGE.getMaxEnergyStored();
    }

    private void setPowered(boolean state) {
        world.setBlockState(
                this.getPos(),
                world.getBlockState(this.getPos())
                        .with(BlockStateProperties.POWERED, state)
        );
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;

        AbstractCookingRecipe recipe = this.getCurrentRecipe();
        if (recipe == null) {
            this.setPowered(false);
            smeltProgress = 0;
            return;
        }

        smeltTime = (int) (recipe.getCookTime() * SPEED_FACTOR);

        ItemStack craftingResult = recipe.getCraftingResult(this.INVENTORY);
        ItemStack outputStack = INVENTORY.getStackInSlot(1);
        if ((craftingResult.getItem() != outputStack.getItem() && !outputStack.isEmpty()) || craftingResult.getCount() + outputStack.getCount() >= outputStack.getMaxStackSize()) {
            this.setPowered(false);
            smeltProgress = 0;
            return;
        }

        if (this.ENERGY_STORAGE.getEnergyStored() >= ENERGY_CONSUMPTION) {
            this.setPowered(true);
            this.ENERGY_STORAGE.setEnergy(this.ENERGY_STORAGE.getEnergyStored() - ENERGY_CONSUMPTION);
            smeltProgress++;

            if (smeltProgress >= smeltTime) {
                smeltProgress = 0;

                this.INVENTORY.decrStackSize(0, 1);

                if (outputStack.isEmpty())
                    this.INVENTORY.setInventorySlotContents(1, craftingResult.copy());
                else
                    outputStack.setCount(outputStack.getCount() + craftingResult.getCount());

            }
        } else {
            this.setPowered(false);
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        INVENTORY.deserializeNBT(compound.getCompound("inventory"));
        ENERGY_STORAGE.deserializeNBT(compound.getCompound("energyStorage"));
        smeltProgress = compound.getInt("smeltProgress");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", INVENTORY.serializeNBT());
        compound.put("energyStorage", ENERGY_STORAGE.serializeNBT());
        compound.putInt("smeltProgress", smeltProgress);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
            if (side == Direction.DOWN)
                return LAZY_INVENTORY[1].cast();
            else
                return LAZY_INVENTORY[0].cast();
        else if (cap.equals(CapabilityEnergy.ENERGY))
            return LAZY_ENERGY_STORAGE.cast();
        return super.getCapability(cap, side);
    }
}
