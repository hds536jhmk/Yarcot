package com.hds.yarcot.blocks.batteries.sapphire;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.blocks.batteries.ModBatteryContainer;
import com.hds.yarcot.blocks.batteries.ModBatteryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SapphireBatteryScreen extends ModBatteryScreen {
    private final ResourceLocation GUI_TEXTURE = new ResourceLocation(Yarcot.MOD_ID, "textures/gui/container/sapphire_battery.png");

    public SapphireBatteryScreen(ModBatteryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, 2);
    }

    @Override
    protected ResourceLocation getGuiTexture() {
        return this.GUI_TEXTURE;
    }
}
