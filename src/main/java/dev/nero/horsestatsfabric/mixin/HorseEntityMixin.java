package dev.nero.horsestatsfabric.mixin;


import com.mojang.authlib.GameProfile;
import dev.nero.horsestatsfabric.Horse;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin ?
 * Permet de modifier le code qui existe dans le jeu de base.
 * On peut modifier des mécanismes, des valeurs, injecter une "logique" etc...
 * https://fabricmc.net/wiki/tutorial:mixin_introduction
 */

/**
 *  On ne doit pas oublier de modifier le fabric.mod.json pour y ajouter le mixin
 *
 *  Ensuite, on ajoute notre classe Mixin dans le .mixins.json
 */
@Mixin(HorseEntity.class)
public abstract class HorseEntityMixin extends HorseBaseEntity {

    protected HorseEntityMixin(EntityType<? extends HorseBaseEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Permet d'injecter du code dans la méthode que l'on a choisi grâce aux sélecteurs at et method.
     *
     * Pour le @At
     * HEAD = sommet de la méthode
     * RETURN = Avant chaque return
     * INVOKE = A l'appel de la méthode
     * TAIL = Avant le dernier return
     *
     * Pour le method
     * La méthode que l'on va modifier ;)
     *
     * Ici, on va donc remplacer le code par celui qui va suivre.
     * Pour voir le code on va dans external libraries > net.minecraft:minecraft-project & have fun
     *
     */
    @Inject(at = @At("HEAD"), method = "interactMob")
    public ActionResult interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {

        //player.shouldCancelInteraction() renvoie si le joueur s'accroupit ou non
        if (player.getWorld().isClient && player.shouldCancelInteraction() && !isTame())
        {
            double jumpStrength = this.getJumpStrength();
            double maxHealth = this.getMaxHealth();
            double speed = this.getAttributes().getValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);

            int slots = 0;
            String owner = "";

            GameProfile profile = new GameProfile(this.getOwnerUuid(),null);
            profile = MinecraftClient.getInstance().getSessionService().fillProfileProperties(profile,false);
            owner = profile.getName();

            Horse horse = new Horse(maxHealth,jumpStrength,speed,slots,owner,true);

            player.sendMessage(new LiteralText(horse.toString()),true);
        }

        return cir.getReturnValue();
    }
}