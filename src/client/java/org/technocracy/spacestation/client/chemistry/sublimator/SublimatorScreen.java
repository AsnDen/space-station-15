package org.technocracy.spacestation.client.chemistry.sublimator;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import org.technocracy.spacestation.chemistry.sublimator.SublimatorScreenHandler;

public class SublimatorScreen extends HandledScreen<SublimatorScreenHandler> {
    private static final int PLAYER_INVENTORY_TOP = 108;

    public SublimatorScreen(SublimatorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 176;
        backgroundHeight = 190;
    }

    @Override
    protected void init() {
        super.init();
        titleX = 8;
        titleY = 6;
        playerInventoryTitleX = 8;
        playerInventoryTitleY = PLAYER_INVENTORY_TOP - 12;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.fill(x, y, x + backgroundWidth, y + backgroundHeight, 0xFF2B2B2B);
        context.fill(x + 1, y + 1, x + backgroundWidth - 1, y + backgroundHeight - 1, 0xFF3C3C3C);
        context.fill(x + 8, y + 18, x + 168, y + 70, 0xFF242424);
        context.drawBorder(x + 8, y + 18, 160, 52, 0xFF555555);
        context.fill(x + 30, y + 34, x + 50, y + 54, 0xFF171717);
        context.drawBorder(x + 30, y + 34, 20, 20, 0xFF888888);
        context.fill(x + 125, y + 34, x + 145, y + 54, 0xFF171717);
        context.drawBorder(x + 125, y + 34, 20, 20, 0xFF888888);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("gui.spacestation.sublimator.input"),
            x + 40, y + 22, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("gui.spacestation.sublimator.output"),
            x + 135, y + 22, 0xFFFFFFFF);
        context.drawText(textRenderer, Text.translatable("gui.spacestation.sublimator.unit"), x + 40, y + 57, 0xFFAAAAAA, false);
        context.drawHorizontalLine(x, x + backgroundWidth, y + PLAYER_INVENTORY_TOP - 20, 0xFF555555);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("container.inventory"), x + 35,
            y + PLAYER_INVENTORY_TOP - 12, 0xFFFFFFFF);
        renderProgressBar(context, x + 62, y + 40);
        renderPlayerInventorySlotBackplates(context, x, y);
    }

    private void renderPlayerInventorySlotBackplates(DrawContext context, int x, int y) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                drawSlotBackplate(context, x + 8 + col * 18, y + PLAYER_INVENTORY_TOP + row * 18);
            }
        }
        for (int col = 0; col < 9; col++) {
            drawSlotBackplate(context, x + 8 + col * 18, y + PLAYER_INVENTORY_TOP + 58);
        }
    }

    private void drawSlotBackplate(DrawContext context, int x, int y) {
        context.fill(x - 1, y - 1, x + 17, y + 17, 0xFF1A1A1A);
        context.drawBorder(x - 1, y - 1, 18, 18, 0xFF555555);
        }

        private void renderProgressBar(DrawContext context, int x, int y) {
        int width = 52;
        int height = 8;
        context.fill(x, y, x + width, y + height, 0xFF171717);
        context.drawBorder(x, y, width, height, 0xFF777777);
        if (handler.entity == null) return;

        int progress = handler.entity.getProcessProgress();
        int filled = Math.round(width * progress / (float) handler.entity.PROCESS_TIME);
        context.fill(x + 1, y + 1, x + 1 + Math.max(0, filled - 2), y + height - 1, 0xFF55B7D9);
    }
}
