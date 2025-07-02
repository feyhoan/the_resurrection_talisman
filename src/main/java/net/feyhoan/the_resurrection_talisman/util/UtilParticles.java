package net.feyhoan.the_resurrection_talisman.util;

import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.joml.Vector3f;

import java.util.List;

public class UtilParticles{
    public static void spawnOverworldParticles(ServerWorld world, BlockPos deathPos) {
        // Чёрно-серый туман (DustParticle)
        ParticleEffect dust = new DustParticleEffect(
                new Vector3f(0.2f, 0.2f, 0.2f), // RGB (тёмно-серый)
                1.0f // Размер
        );

        // Спавн частиц в случайных точках вокруг места смерти
        for (int i = 0; i < 10; i++) {
            double x = deathPos.getX() + 0.5 + (world.random.nextDouble() - 0.5) * 2;
            double z = deathPos.getZ() + 0.5 + (world.random.nextDouble() - 0.5) * 2;

            world.spawnParticles(
                    dust,
                    x, deathPos.getY() + 0.1, z,
                    3, // Количество
                    0.1, 0.1, 0.1, // Разброс
                    0.01 // Скорость
            );
        }

        // Soul Fire Flame для атмосферы
        world.spawnParticles(
                ParticleTypes.SOUL_FIRE_FLAME,
                deathPos.getX() + 0.5, deathPos.getY() + 0.2, deathPos.getZ() + 0.5,
                5, 0.2, 0.2, 0.2, 0.02
        );
    }

    public static ParticleEffect red_glow = new DustParticleEffect(
            new Vector3f(1.0f, 0.0f, 0.0f), // Белый цвет
            1.3f // Яркость
    );

    public static void spawnLimboMarker(ServerWorld world, BlockPos deathPos) {
        // Спавн вертикального столба частиц (3 блока вверх)
        float step = 0.5f;
        for (float y = 0; y < 3;) {
            world.spawnParticles(
                    red_glow,
                    deathPos.getX() + 0.5, deathPos.getY() + y, deathPos.getZ() + 0.5,
                    5, 0.1, 0.1, 0.1, 0
            );
            y = y+step;
        }

        // Мигание, если игрок рядом
        List<ServerPlayerEntity> players = world.getPlayers();
        for (ServerPlayerEntity player : players) {
            if (player.squaredDistanceTo(deathPos.getX(), deathPos.getY(), deathPos.getZ()) < 16) {
                world.spawnParticles(
                        ParticleTypes.CRIMSON_SPORE,
                        deathPos.getX() + 0.5, deathPos.getY() + 1.5, deathPos.getZ() + 0.5,
                        3, 0.3, 0.3, 0.3, 0.1
                );
            }
        }
    }
}

