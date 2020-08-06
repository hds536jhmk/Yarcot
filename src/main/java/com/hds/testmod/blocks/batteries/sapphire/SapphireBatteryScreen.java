package com.hds.testmod.blocks.batteries.sapphire;

import com.hds.testmod.TestMod;
import com.hds.testmod.blocks.barrels.ModBarrelContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.DecimalFormat;

@OnlyIn(Dist.CLIENT)
public class SapphireBatteryScreen extends ContainerScreen<SapphireBatteryContainer> {
    private final ResourceLocation GUI_TEXTURE = new ResourceLocation(TestMod.MOD_ID, "textures/gui/container/sapphire_battery.png");

    public SapphireBatteryScreen(SapphireBatteryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        this.xSize = 14 + 9 * ModBarrelContainer.CELL_SIZE;
        this.ySize = 40 + 7 * ModBarrelContainer.CELL_SIZE;
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
                19 + 3 * ModBarrelContainer.CELL_SIZE,
                0x404040
        );
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        this.blit(this.getGuiLeft(), this.getGuiTop(), 0, 0, this.xSize, this.ySize);
        this.blit(this.getGuiLeft() + 136, this.getGuiTop() + 57, 186, 53, 16, (int)(this.container.BATTERY_TILE.getChargePercentage() * -32.0F));
    }
}
