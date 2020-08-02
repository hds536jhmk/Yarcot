package com.hds.testmod.container;

import com.hds.testmod.TestMod;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.text.ITextComponent;

public class SapphireChestScreen extends ContainerScreen<SapphireChestContainer> {

    private ResourceLocation GUI_TEXTURE = new ResourceLocation(TestMod.MODID, "textures/gui/container/sapphire_chest.png");
    private final Vec2f TEXTURE_OFFSET;

    public SapphireChestScreen(SapphireChestContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        TEXTURE_OFFSET = getContainer().getTextureOffset();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        super.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        int x = (this.width - this.xSize) / 2 + (int)TEXTURE_OFFSET.x;
        int y = (this.height - this.ySize) / 2 + (int)TEXTURE_OFFSET.y;
        this.blit(x, y, 0, 0, this.xSize, this.ySize);
    }
}
