package com.hds.yarcot.blocks.batteries;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.text.DecimalFormat;

public abstract class ModBatteryScreen extends ContainerScreen<ModBatteryContainer> {
    private final int TIER;

    public ModBatteryScreen(ModBatteryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn, int tier) {
        super(screenContainer, inv, titleIn);
        this.TIER = Math.max(Math.min(tier, 3), 1);
    }

    protected abstract ResourceLocation getGuiTexture();

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

        String batteryCharge = DecimalFormat.getPercentInstance().format(this.container.BATTERY_TILE.getChargePercentage());
        int stringWidth = this.font.getStringWidth(batteryCharge);
        this.font.drawString(batteryCharge, 144 - stringWidth / 2, 72, 0x404040);

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
        this.minecraft.getTextureManager().bindTexture(this.getGuiTexture());
        this.blit(this.getGuiLeft(), this.getGuiTop(), 0, 0, this.xSize, this.ySize);
        this.blit(
                this.getGuiLeft() + 136, this.getGuiTop() + 57,
                186, 53,
                16, (int)(this.container.BATTERY_TILE.getChargePercentage() * -(16 * this.TIER))
        );
    }
}
