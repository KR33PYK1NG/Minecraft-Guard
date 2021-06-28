package rmc.mixins.minecraft_guard.inject;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import rmc.libs.event_factory.EventFactory;
import rmc.mixins.minecraft_guard.MinecraftGuard;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = Explosion.class)
public abstract class ExplosionMixin {

    @Shadow @Final
    private World level;

    @Shadow @Final
    private List<BlockPos> toBlow;

    @Inject(method = "Lnet/minecraft/world/Explosion;finalizeExplosion(Z)V",
            at = @At(value = "INVOKE",
                     target = "Ljava/util/Collections;shuffle(Ljava/util/List;Ljava/util/Random;)V"))
    private void guardBlockExplosion(CallbackInfo mixin) {
        Iterator<BlockPos> it = this.toBlow.iterator();
        while (it.hasNext()) {
            if (!EventFactory.testBlockBreak((ServerPlayerEntity) null, this.level, it.next(), MinecraftGuard.EXPLOSION_FAKE)) {
                this.toBlow.clear();
                break;
            }
        }
    }

}