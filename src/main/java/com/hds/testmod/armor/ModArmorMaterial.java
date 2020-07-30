package com.hds.testmod.armor;

import com.hds.testmod.TestMod;
import com.hds.testmod.items.ModItems;
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
            () -> {
                return Ingredient.fromItems(ModItems.SAPPHIRE.get());
            }
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
    private final int MAXDAMAGEFACTOR;
    private final int[] DAMAGEREDUCTION;
    private final int ENCHANTABILITY;
    private final SoundEvent SOUNDEVENT;
    private final float TOUGHNESS;
    private final Supplier<Ingredient> REPAIRMATERIAL;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReduction, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        NAME = TestMod.MODID + ":" + name;
        MAXDAMAGEFACTOR = maxDamageFactor;
        DAMAGEREDUCTION = damageReduction;
        ENCHANTABILITY = enchantability;
        SOUNDEVENT = soundEvent;
        TOUGHNESS = toughness;
        REPAIRMATERIAL = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE.getByIndex(slotIn.getIndex()) * MAXDAMAGEFACTOR;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return DAMAGEREDUCTION[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return ENCHANTABILITY;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SOUNDEVENT;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return REPAIRMATERIAL.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public float getToughness() {
        return TOUGHNESS;
    }
}
