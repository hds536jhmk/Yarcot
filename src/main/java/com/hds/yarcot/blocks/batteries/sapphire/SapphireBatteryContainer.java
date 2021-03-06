package com.hds.yarcot.blocks.batteries.sapphire;

import com.hds.yarcot.blocks.batteries.ModBatteryContainer;
import com.hds.yarcot.registries.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;

public class SapphireBatteryContainer extends ModBatteryContainer {
    public SapphireBatteryContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModContainers.SAPPHIRE_BATTERY_CONTAINER.get(), windowId, playerInventory, pos);
        this.generateSlots(this.BATTERY_TILE.getItemStorage(), 29, 21);
        this.generatePlayerInventory(0, 84);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
