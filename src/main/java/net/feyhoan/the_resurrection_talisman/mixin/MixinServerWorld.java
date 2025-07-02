package net.feyhoan.the_resurrection_talisman.mixin;

import net.feyhoan.the_resurrection_talisman.util.DeathData;
import net.feyhoan.the_resurrection_talisman.util.ParticlePathfinder;
import net.feyhoan.the_resurrection_talisman.util.UtilParticles;
import net.feyhoan.the_resurrection_talisman.world.dimensions.ModDimensions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld {
    private static final int PARTICLE_INTERVAL = 140; // ticks between path updates
    private int particleTickCounter = 0;

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickParticles(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        particleTickCounter++;

        if (particleTickCounter >= PARTICLE_INTERVAL) {
            particleTickCounter = 0;

            DeathData.DEATHS.forEach((uuid, pos) -> {
                if (((World)(Object)this).getRegistryKey() == ModDimensions.OVERWORLD_LIMBO_LEVEL_KEY) {
                    // Get all players in limbo
                    for (ServerPlayerEntity player : ((ServerWorld)(Object)this).getPlayers()) {
                        // Create path from player to death location
                        ParticlePathfinder.createPath(
                                (ServerWorld)(Object)this,
                                player.getPos(),
                                Vec3d.ofCenter(pos.deathPos())
                        );
                    }
                    UtilParticles.spawnLimboMarker((ServerWorld)(Object)this, pos.deathPos());
                }
                else if (((World)(Object)this).getRegistryKey() == World.OVERWORLD) {
                    UtilParticles.spawnOverworldParticles((ServerWorld)(Object)this, pos.deathPos());
                }
            });
        }
    }
}