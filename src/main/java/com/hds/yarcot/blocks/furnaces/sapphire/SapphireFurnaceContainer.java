package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.blocks.furnaces.ModFurnaceContainer;
import com.hds.yarcot.registries.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;

public class SapphireFurnaceContainer extends ModFurnaceContainer {

    public SapphireFurnaceContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModContainers.SAPPHIRE_FURNACE_CONTAINER.get(), windowId, playerInventory, pos);

        this.generateSlots(56, 34, 116, 35);
        this.generatePlayerInventory(0, 84);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
