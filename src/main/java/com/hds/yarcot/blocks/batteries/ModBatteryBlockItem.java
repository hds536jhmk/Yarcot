package com.hds.yarcot.blocks.batteries;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.creativetabs.ModItemGroups;
import com.hds.yarcot.items.batteries.IBatteryItem;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ModBatteryBlockItem extends BlockItem implements IBatteryItem {
    public ModBatteryBlockItem(Block block) {
        super(block, new Item.Properties().group(ModItemGroups.BLOCKS));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(
                TextComponentUtils.toTextComponent(
                        () -> TextFormatting.BLUE + I18n.format("item." + Yarcot.MOD_ID + ".generic_battery.tooltip.energy", getEnergyStored(stack), getMaxEnergyStored(stack))
                )
        );
    }

    @Nullable
    @Override
    public abstract ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt);
}
