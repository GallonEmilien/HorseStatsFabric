package dev.nero.horsestatsfabric.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.nero.horsestatsfabric.Colors;
import dev.nero.horsestatsfabric.Horse;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HorseScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.HorseScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(HorseScreen.class)
public abstract class HorseScreenMixin extends HandledScreen<HorseScreenHandler>
{
    @Shadow @Final private HorseBaseEntity entity;

    public HorseScreenMixin(HorseScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        super.drawForeground(matrices,mouseX,mouseY);
        int x = this.backgroundWidth + 5;
        Horse horse;

        String owner;
        GameProfile profile = new GameProfile(this.entity.getOwnerUuid(),null);
        profile = MinecraftClient.getInstance().getSessionService().fillProfileProperties(profile,false);
        owner = profile.getName();

        if(this.entity instanceof DonkeyEntity || this.entity instanceof LlamaEntity) {
            horse = new Horse(this.entity.getHealth(),
                    this.entity.getJumpStrength(),
                    this.entity.getAttributes().getValue(EntityAttributes.GENERIC_MOVEMENT_SPEED),
                    ((AbstractDonkeyEntity) this.entity).getInventoryColumns(),
                    owner,
                    false);
        } else {
            horse = new Horse(this.entity.getHealth(),
                    this.entity.getJumpStrength(),
                    this.entity.getAttributes().getValue(EntityAttributes.GENERIC_MOVEMENT_SPEED),
                    0,
                    owner,
                    true);
        }

        Identifier TEXTURE2 = new Identifier("textures/gui/toasts.png");
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE2);

        this.drawTexture(matrices, this.backgroundWidth, 0, 0, 16,
                (int)(159/1.6)+2, 16,(int)(256/1.6),128);
        this.drawTexture(matrices, this.backgroundWidth, 18, 0, 16,
                (int)(159/1.6)+2, 16,(int)(256/1.6),128);
        this.drawTexture(matrices, this.backgroundWidth, 36, 0, 16,
                (int)(159/1.6)+2, 16,(int)(256/1.6),128);

        int y = 4;

        if(horse.getOwner() != null)
        {
            this.drawTexture(matrices, this.backgroundWidth, 54, 0, 16,
                    (int)(159/1.6)+2, 16,(int)(256/1.6),128);
            this.textRenderer.draw(matrices, "Owner: "+horse.getOwner(), x, 54+y, Colors.FULL.getColor());
        }

        this.textRenderer.draw(matrices, "♥" , x+1, 0+y,0xcc0000);
        this.textRenderer.draw(matrices, "⇪" , x+2, 18+y,0x6fa8dc);
        this.textRenderer.draw(matrices, "➟", x, 36+y,0x6fa8dc);

        x+=10;

        this.textRenderer.draw(matrices, ""+Horse.MIN_HEALTH      , x, 0+y, Colors.ZERO.getColor());
        this.textRenderer.draw(matrices, ""+Horse.MIN_JUMP_HEIGHT , x, 18+y, Colors.ZERO.getColor());
        this.textRenderer.draw(matrices, ""+Horse.MIN_SPEED       , x, 36+y, Colors.ZERO.getColor());

        x+=22;
        this.textRenderer.draw(matrices, "<"    , x, 0+y,0x6f9adb);
        this.textRenderer.draw(matrices, "<"    , x, 18+y,0x6f9adb);
        this.textRenderer.draw(matrices, "<"    , x, 36+y,0x6f9adb);
        x+=6;

        this.textRenderer.draw(matrices, ""+horse.getHealth()     , x, 0+y,
                horse.getAttributeColor(Horse.MIN_HEALTH,horse.getHealth(),Horse.MAX_HEALTH));
        this.textRenderer.draw(matrices, ""+horse.getJumpHeight() , x, 18+y,
                horse.getAttributeColor(Horse.MIN_JUMP_HEIGHT,horse.getJumpHeight(),Horse.MAX_JUMP_HEIGHT));
        this.textRenderer.draw(matrices, ""+horse.getSpeed()      , x, 36+y,
                horse.getAttributeColor(Horse.MIN_SPEED,horse.getSpeed(),Horse.MAX_SPEED));

        x+=22;
        this.textRenderer.draw(matrices, "<"    , x, 0+y,0x6f9adb);
        this.textRenderer.draw(matrices, "<"    , x, 18+y,0x6f9adb);
        this.textRenderer.draw(matrices, "<"    , x, 36+y,0x6f9adb);
        x+=6;

        this.textRenderer.draw(matrices, ""+Horse.MAX_HEALTH      , x, 0+y, Colors.FULL.getColor());
        this.textRenderer.draw(matrices, ""+Horse.MAX_JUMP_HEIGHT , x, 18+y, Colors.FULL.getColor());
        this.textRenderer.draw(matrices, ""+Horse.MAX_SPEED       , x, 36+y, Colors.FULL.getColor());
    }
}


