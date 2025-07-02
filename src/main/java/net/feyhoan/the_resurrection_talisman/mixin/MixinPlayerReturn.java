package net.feyhoan.the_resurrection_talisman.mixin;

import net.feyhoan.the_resurrection_talisman.util.DeathData;
import net.feyhoan.the_resurrection_talisman.world.dimensions.ModDimensions;
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
            DeathData.DeathInfo info = DeathData.DEATHS.get(player.getUuid());

            if (info != null && isStandingOnDeathPos(player, info.deathPos())) {
                returnToOverworld(player, info);
            }
        }
    }

    private boolean isStandingOnDeathPos(PlayerEntity player, BlockPos deathPos) {
        BlockPos currentPos = BlockPos.ofFloored(player.getPos());
        return currentPos.equals(deathPos);
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

        // Восстановление инвентаря
        DeathData.restoreInventory(player);

        // Эффекты
        player.getWorld().playSound(null, player.getBlockPos(),
                SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
    }
}