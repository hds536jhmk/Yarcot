package com.hds.testmod.materials;

import com.hds.testmod.registries.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTiers implements IItemTier {
    SAPPHIRE(
            750,
            7.0F,
            -1.0F,
            3,
            15,
            () -> Ingredient.fromItems(ModItems.SAPPHIRE.get())
    );

    private final int DURABILITY;
    private final float EFFICIENCY;
    private final float ATTACKDAMAGE;
    private final int HARVESTLEVEL;
    private final int ENCHANTABILITY;
    private final Supplier<Ingredient> REPAIRMATERIAL;

    ModItemTiers(int durability, float efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.DURABILITY = durability;
        this.EFFICIENCY = efficiency;
        this.ATTACKDAMAGE = attackDamage;
        this.HARVESTLEVEL = harvestLevel;
        this.ENCHANTABILITY = enchantability;
        this.REPAIRMATERIAL = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return this.DURABILITY;
    }

    @Override
    public float getEfficiency() {
        return this.EFFICIENCY;
    }

    @Override
    public float getAttackDamage() {
        return this.ATTACKDAMAGE;
    }

    @Override
    public int getHarvestLevel() {
        return this.HARVESTLEVEL;
    }

    @Override
    public int getEnchantability() {
        return this.ENCHANTABILITY;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.REPAIRMATERIAL.get();
    }
}
