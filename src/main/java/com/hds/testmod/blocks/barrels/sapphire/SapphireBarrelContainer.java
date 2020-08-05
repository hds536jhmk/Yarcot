package com.hds.testmod.blocks.barrels.sapphire;

import com.hds.testmod.blocks.barrels.ModBarrelContainer;
import com.hds.testmod.registries.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;

public class SapphireBarrelContainer extends ModBarrelContainer {
    public SapphireBarrelContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModContainers.SAPPHIRE_BARREL_CONTAINER.get(), windowId, playerInventory, pos);

        this.generateLayout(playerInventory, this.BARREL_TILE.COLUMNS, this.BARREL_TILE.ROWS, this.BARREL_TILE.getBarrelInventory());
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
