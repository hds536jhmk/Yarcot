package com.hds.yarcot.materials;

import com.hds.yarcot.registries.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTiers implements IItemTier {
    SAPPHIRE(
            750,
            7.0F,
            0.0F,
            3,
            15,
            () -> Ingredient.fromItems(ModItems.SAPPHIRE.get())
    ),
    RUBY(
            750,
            7.0F,
            0.0F,
            3,
            15,
            () -> Ingredient.fromItems(ModItems.RUBY.get())
    );

    private final int DURABILITY;
    private final float EFFICIENCY;
    private final float ATTACK_DAMAGE;
    private final int HARVEST_LEVEL;
    private final int ENCHANTABILITY;
    private final Supplier<Ingredient> REPAIR_MATERIAL;

    ModItemTiers(int durability, float efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.DURABILITY = durability;
        this.EFFICIENCY = efficiency;
        this.ATTACK_DAMAGE = attackDamage;
        this.HARVEST_LEVEL = harvestLevel;
        this.ENCHANTABILITY = enchantability;
        this.REPAIR_MATERIAL = repairMaterial;
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
        return this.ATTACK_DAMAGE;
    }

    @Override
    public int getHarvestLevel() {
        return this.HARVEST_LEVEL;
    }

    @Override
    public int getEnchantability() {
        return this.ENCHANTABILITY;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.REPAIR_MATERIAL.get();
    }
}
