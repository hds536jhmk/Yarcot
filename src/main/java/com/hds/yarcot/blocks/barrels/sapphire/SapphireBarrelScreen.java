package com.hds.yarcot.blocks.barrels.sapphire;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.blocks.barrels.ModBarrelContainer;
import com.hds.yarcot.blocks.barrels.ModBarrelScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SapphireBarrelScreen extends ModBarrelScreen {
    private final ResourceLocation GUI_TEXTURE = new ResourceLocation(Yarcot.MOD_ID, "textures/gui/container/sapphire_barrel.png");

    public SapphireBarrelScreen(ModBarrelContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected ResourceLocation getGuiTexture() {
        return this.GUI_TEXTURE;
    }
}
