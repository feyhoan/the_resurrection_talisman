package net.feyhoan.the_resurrection_talisman.mixin;

import net.fabricmc.fabric.mixin.object.builder.TradeOffersTypeAwareBuyForOneEmeraldFactoryMixin;
import net.feyhoan.the_resurrection_talisman.item.ModItems;
import net.feyhoan.the_resurrection_talisman.util.DeathData;
import net.feyhoan.the_resurrection_talisman.util.UtilParticles;
import net.feyhoan.the_resurrection_talisman.world.dimensions.ModDimensions;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinPlayerReturn {
    @Inject(method = "tick", at = @At("HEAD"))
    private void checkReturnCondition(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;

        if (player.getWorld().getRegistryKey() == ModDimensions.OVERWORLD_LIMBO_LEVEL_KEY) {
            DeathData.getDeathInfo(player.getUuid()).ifPresent(info -> {
                if (isStandingOnDeathPos(player, info.deathPos())) {
                    returnToOverworld(player, info);
                    if (!player.getInventory().contains(ModItems.RESURRECTION_TALISMAN.getDefaultStack())) {
                        player.getInventory().offerOrDrop(info.talisman().copy());
                    }
                    DeathData.removeDeathInfo(player.getUuid(), player.server);
                }
            });
        }
    }


    private boolean isStandingOnDeathPos(PlayerEntity player, BlockPos deathPos) {
        BlockPos currentPos = BlockPos.ofFloored(player.getPos());
        return currentPos.isWithinDistance(deathPos, 1.5); // 1.5 блока радиуса
    }

    private void returnToOverworld(ServerPlayerEntity player, DeathData.DeathInfo info) {
        ServerWorld overworld = player.server.getWorld(World.OVERWORLD);

        // Телепортация
        player.teleport(
                overworld,
                info.deathPos().getX() + 0.5,
                info.deathPos().getY(),
                info.deathPos().getZ() + 0.5,
                player.getYaw(),
                player.getPitch()
        );

        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 3, false, false));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 40, 3, false, false));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 3, false, false));

        UtilParticles.spawnReturnOverworldParticles(player.getServerWorld(), player.getBlockPos());

        // Восстановление инвентаря
        DeathData.restoreInventory(player);

        // Эффекты
        player.getWorld().playSound(null, player.getBlockPos(),
                SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
    }
}