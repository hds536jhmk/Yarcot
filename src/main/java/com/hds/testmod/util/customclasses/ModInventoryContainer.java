package com.hds.testmod.util.customclasses;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public abstract class ModInventoryContainer extends Container {
    public static final int CELL_SIZE = 18;

    private final IItemHandler PLAYER_INVENTORY;

    public ModInventoryContainer(@Nullable ContainerType<?> type,  int windowId, PlayerInventory playerInventory) {
        super(type, windowId);
        this.PLAYER_INVENTORY = new InvWrapper(playerInventory);
    }

    public void generatePlayerInventory(int x, int y) {
        int xCell = 0;
        int yCell = 0;
        for (int currentSlot = 0; currentSlot < 36; currentSlot++) {
            int xPosition = 8 + x + xCell * CELL_SIZE;
            if (currentSlot < 27) { // INVENTORY
                addSlot(new SlotItemHandler(PLAYER_INVENTORY, currentSlot + 9, xPosition, y + yCell * CELL_SIZE));
            } else { // HOTBAR
                addSlot(new SlotItemHandler(PLAYER_INVENTORY, currentSlot - 27, xPosition, 4 + y + yCell * CELL_SIZE));
            }
            xCell++;
            if (xCell >= 9) { // NEW ROW
                xCell = 0;
                yCell++;
            }
        }
    }

    @Override
    public abstract boolean canInteractWith(PlayerEntity playerIn);
}
