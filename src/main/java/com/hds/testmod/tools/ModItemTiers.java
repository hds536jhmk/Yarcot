package com.hds.testmod.tools;

import com.hds.testmod.items.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTiers implements IItemTier {
    SAPPHIRE(750, 7.0F, -1.0F, 3, 15, () -> {
        return Ingredient.fromItems(ModItems.SAPPHIRE.get());
    });

    private final int DURABILITY;
    private final float EFFICIENCY;
    private final float ATTACKDAMAGE;
    private final int HARVESTLEVEL;
    private final int ENCHANTABILITY;
    private final Supplier<Ingredient> REPAIRMATERIAL;

    ModItemTiers(int durability, float efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> repairMaterial) {
        DURABILITY = durability;
        EFFICIENCY = efficiency;
        ATTACKDAMAGE = attackDamage;
        HARVESTLEVEL = harvestLevel;
        ENCHANTABILITY = enchantability;
        REPAIRMATERIAL = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return DURABILITY;
    }

    @Override
    public float getEfficiency() {
        return EFFICIENCY;
    }

    @Override
    public float getAttackDamage() {
        return ATTACKDAMAGE;
    }

    @Override
    public int getHarvestLevel() {
        return HARVESTLEVEL;
    }

    @Override
    public int getEnchantability() {
        return ENCHANTABILITY;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return REPAIRMATERIAL.get();
    }
}
