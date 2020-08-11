package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.blocks.furnaces.ModFurnaceInventory;
import com.hds.yarcot.registries.ModTileEntities;
import com.hds.yarcot.util.customclasses.ModEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SapphireFurnaceTile extends TileEntity implements ITickableTileEntity{
    private ModFurnaceInventory inventory = new ModFurnaceInventory() {
        @Override
        public void markDirty() {
            SapphireFurnaceTile.this.markDirty();
        }
    };

    private ModEnergyStorage energyStorage = new ModEnergyStorage(1000, 200, 0) {
        @Override
        public void onEnergyChanged(int energyAdded) {
            SapphireFurnaceTile.this.markDirty();
        }
    };

    private final int ENERGY_PER_TICK = 2;
    private int totalSmeltTicks = 100;
    private int thisSmeltProgress = 0;

    public SapphireFurnaceTile() {
        super(ModTileEntities.SAPPHIRE_FURNACE_TILE.get());
    }

    public ModFurnaceInventory getFurnaceInventory() {
        return this.inventory;
    }

    @Nullable
    public AbstractCookingRecipe getCurrentRecipe() {
        return world.getRecipeManager().getRecipe(IRecipeType.SMELTING, this.inventory, world).orElse(null);
    }

    public int getSmeltProgress() {
        return (int) ((float) thisSmeltProgress / (float) totalSmeltTicks * 100.0F);
    }

    public float getChargePercentage() {
        return (float) energyStorage.getEnergyStored() / (float) energyStorage.getMaxEnergyStored();
    }

    private void setPowered(boolean state) {
        BlockState blockState = world.getBlockState(this.getPos());
        world.setBlockState(this.getPos(), blockState.with(BlockStateProperties.POWERED, state));
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;

        AbstractCookingRecipe recipe = this.getCurrentRecipe();
        if (recipe == null) {
            this.setPowered(false);
            thisSmeltProgress = 0;
            return;
        }

        ItemStack craftingResult = recipe.getCraftingResult(this.inventory);
        ItemStack outputStack = inventory.getStackInSlot(1);
        if ((craftingResult.getItem() != outputStack.getItem() && !outputStack.isEmpty()) || craftingResult.getCount() + outputStack.getCount() >= outputStack.getMaxStackSize()) {
            this.setPowered(false);
            thisSmeltProgress = 0;
            return;
        }

        if (this.energyStorage.getEnergyStored() >= ENERGY_PER_TICK) {
            this.setPowered(true);
            this.energyStorage.setEnergy(this.energyStorage.getEnergyStored() - ENERGY_PER_TICK);
            thisSmeltProgress++;

            totalSmeltTicks = recipe.getCookTime() / 2;

            if (thisSmeltProgress >= totalSmeltTicks) {
                thisSmeltProgress = 0;

                this.inventory.decrStackSize(0, 1);

                if (outputStack.isEmpty())
                    this.inventory.setInventorySlotContents(1, craftingResult.copy());
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
        inventory.deserializeNBT(compound.getCompound("inventory"));
        energyStorage.deserializeNBT(compound.getCompound("energyStorage"));
        thisSmeltProgress = compound.getInt("smeltProgress");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", inventory.serializeNBT());
        compound.put("energyStorage", energyStorage.serializeNBT());
        compound.putInt("smeltProgress", thisSmeltProgress);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) {
            return LazyOptional.of(() -> new SidedInvWrapper(this.inventory, side)).cast();
        } else if (cap.equals(CapabilityEnergy.ENERGY))
            return LazyOptional.of(() -> energyStorage).cast();
        return super.getCapability(cap, side);
    }
}
