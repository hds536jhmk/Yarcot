package com.hds.yarcot.creativetabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class BaseItemGroup extends ItemGroup {

    private final Supplier<ItemStack> ICON;

    public BaseItemGroup(String label, ItemStack icon) {
        super(label);
        this.ICON = () -> icon;
    }

    public BaseItemGroup(String label, Item icon) {
        super(label);
        this.ICON = () -> new ItemStack(icon);
    }

    public BaseItemGroup(String label) {
        super(label);
        this.ICON = () -> null;
    }

    @Override
    public ItemStack createIcon() {
        return ICON.get();
    }
}
