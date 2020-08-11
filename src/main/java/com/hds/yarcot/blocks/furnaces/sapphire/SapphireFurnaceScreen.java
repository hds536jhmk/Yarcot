package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.blocks.furnaces.ModFurnaceContainer;
import com.hds.yarcot.blocks.furnaces.ModFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SapphireFurnaceScreen extends ModFurnaceScreen {
    private final ResourceLocation GUI_TEXTURE = new ResourceLocation(Yarcot.MOD_ID, "textures/gui/container/sapphire_furnace.png");

    public SapphireFurnaceScreen(ModFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected ResourceLocation getGuiTexture() {
        return GUI_TEXTURE;
    }
}
