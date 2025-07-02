package net.feyhoan.the_resurrection_talisman.mixin;

import net.feyhoan.the_resurrection_talisman.entity.custom.LostSpiritEntity;
import net.feyhoan.the_resurrection_talisman.world.dimensions.ModDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void handleLimboDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;

        // Проверяем, находится ли сущность в измерении Limbo
        if (entity.getWorld().getRegistryKey() != ModDimensions.OVERWORLD_LIMBO_LEVEL_KEY) {
            return; // Если нет, выходим
        }

        // 1️⃣ Если игрок получает урон — отменяем и проигрываем звук
        if (entity instanceof PlayerEntity) {
            if (entity instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, 0.3f, 1.2f);
            }
            cir.setReturnValue(false); // Отменяем урон
            return;
        }

        // 2️⃣ Если игрок атакует LostSpirit — восстанавливаем голод
        if (entity instanceof LostSpiritEntity && source.getAttacker() instanceof ServerPlayerEntity player) {
            player.getHungerManager().add(4, 2); // Восстанавливаем голод
        }
    }
}