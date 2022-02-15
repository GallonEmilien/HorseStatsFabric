package dev.nero.horsestatsfabric.mixin;


import com.mojang.authlib.GameProfile;
import dev.nero.horsestatsfabric.Horse;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractDonkeyEntity.class)
public abstract class DonkeyEntityMixin extends HorseBaseEntity {

    @Shadow public abstract int getInventoryColumns();

    @Shadow protected abstract int getInventorySize();

    protected DonkeyEntityMixin(EntityType<? extends HorseBaseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "interactMob")
    public ActionResult interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {

        //player.shouldCancelInteraction() renvoie si le joueur s'accroupit ou non
        if (player.getWorld().isClient && player.shouldCancelInteraction() && !isTame())
        {
            double jumpStrength = this.getJumpStrength();
            double maxHealth = this.getMaxHealth();
            double speed = this.getAttributes().getValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);

            int slots = this.getInventorySize();
            String owner = null;

            if(this.getOwnerUuid() != null) {
                GameProfile profile = new GameProfile(this.getOwnerUuid(), null);
                profile = MinecraftClient.getInstance().getSessionService().fillProfileProperties(profile, false);
                owner = profile.getName();
            }

            Horse horse = new Horse(maxHealth,jumpStrength,speed,slots,owner,false);

            player.sendMessage(new LiteralText(horse.toString()),true);
        }

        return cir.getReturnValue();
    }
}