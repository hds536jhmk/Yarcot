package com.hds.yarcot.blocks.furnaces.sapphire;

import com.hds.yarcot.Yarcot;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SapphireFurnaceScreen extends ContainerScreen<SapphireFurnaceContainer> {
    private final ResourceLocation GUI_TEXTURE = new ResourceLocation(Yarcot.MOD_ID, "textures/gui/container/sapphire_furnace.png");

    public SapphireFurnaceScreen(SapphireFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        this.xSize = 176;
        this.ySize = 166;
        super.init();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        super.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 0x404040);

        this.font.drawString(
                this.playerInventory.getDisplayName().getFormattedText(),
                8.0F,
                73,
                0x404040
        );
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(this.GUI_TEXTURE);
        this.blit(this.getGuiLeft(), this.getGuiTop(), 0, 0, this.xSize, this.ySize);
        this.blit(
                this.getGuiLeft() + 21, this.getGuiTop() + 49,
                186, 21,
                16, (int) (this.container.FURNACE_TILE.getChargePercentage() * -16)
        );
        this.blit(
                this.getGuiLeft() + 80, this.getGuiTop() + 35,
                179, 31,
                (int) (this.container.furnaceProgress * 22.0F), 16
        );

    }
}
