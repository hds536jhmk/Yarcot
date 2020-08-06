package com.hds.yarcot.materials;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.registries.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    SAPPHIRE(
            "sapphire",
            20,
            createDamageReductionArray(2, 6, 5, 2),
            16,
            SoundEvents.ENTITY_HORSE_ARMOR,
            0.5F,
            () -> Ingredient.fromItems(ModItems.SAPPHIRE.get())
    );

    public static class MAX_DAMAGE {
        public static final int HELMET = 11;
        public static final int CHESTPLATE = 16;
        public static final int LEGGINGS = 15;
        public static final int BOOTS = 13;

        public static int getByIndex(int index) {
            switch (index) {
                case 0:
                    return HELMET;
                case 1:
                    return CHESTPLATE;
                case 2:
                    return LEGGINGS;
                case 3:
                    return BOOTS;
            }
            return 0;
        }
    }

    public static int[] createDamageReductionArray(int helmet, int chestplate, int leggings, int boots) {
        return new int[] {
            helmet, leggings, chestplate, boots
        };
    }

    private final String NAME;
    private final int MAX_DAMAGE_FACTOR;
    private final int[] DAMAGE_REDUCTION;
    private final int ENCHANTABILITY;
    private final SoundEvent SOUND_EVENT;
    private final float TOUGHNESS;
    private final Supplier<Ingredient> REPAIR_MATERIAL;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReduction, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        this.NAME = Yarcot.MOD_ID + ":" + name;
        this.MAX_DAMAGE_FACTOR = maxDamageFactor;
        this.DAMAGE_REDUCTION = damageReduction;
        this.ENCHANTABILITY = enchantability;
        this.SOUND_EVENT = soundEvent;
        this.TOUGHNESS = toughness;
        this.REPAIR_MATERIAL = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE.getByIndex(slotIn.getIndex()) * this.MAX_DAMAGE_FACTOR;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.DAMAGE_REDUCTION[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.ENCHANTABILITY;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.SOUND_EVENT;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.REPAIR_MATERIAL.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public float getToughness() {
        return this.TOUGHNESS;
    }
}
