package net.feyhoan.the_resurrection_talisman.util;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;

public class ParticlePathfinder {
    private static final int SNAKE_LENGTH = 10; // длина змейки
    private static final double STEP_SIZE = 0.8; // шаг движения
    private static final double RANDOMNESS = 4;
    private static final int MAX_STEPS = 200;

    public static void createPath(ServerWorld world, Vec3d startPos, Vec3d endPos) {
        List<Vec3d> pathPoints = calculatePath(startPos, endPos, world);
        // Инициализация змейки: все части в начале на стартовой точке
        List<Vec3d> snakeParts = new ArrayList<>();
        for (int i = 0; i < SNAKE_LENGTH; i++) {
            snakeParts.add(startPos);
        }

        // Текущая цель - последняя точка пути
        Vec3d targetPoint = pathPoints.get(pathPoints.size() - 1);
        int targetIndex = pathPoints.size() - 2;

        Random random = world.getRandom();

        for (int stepCount = 0; stepCount < MAX_STEPS; stepCount++) {
            // Определяем текущую цель для змейки
            Vec3d currentHead = snakeParts.get(0);
            if (currentHead.distanceTo(targetPoint) < STEP_SIZE) {
                if (targetIndex >= 0) {
                    targetPoint = pathPoints.get(targetIndex);
                    targetIndex--;
                } else {
                    // достигли конца пути
                    break;
                }
            }

            // Вычисляем направление к цели с рандомизацией
            Vec3d directionToTarget = targetPoint.subtract(currentHead).normalize();

            Vec3d randomOffset = new Vec3d(
                    (random.nextDouble() - 0.5) * RANDOMNESS,
                    (random.nextDouble() - 0.5) * RANDOMNESS,
                    (random.nextDouble() - 0.5) * RANDOMNESS
            );

            Vec3d moveDirection = directionToTarget.add(randomOffset).normalize();

            // Обновляем позицию головы
            Vec3d newHeadPos = currentHead.add(moveDirection.multiply(STEP_SIZE));

            // Проверка препятствий и корректировка направления
            BlockPos blockPosCheck = new BlockPos((int) newHeadPos.x, (int) newHeadPos.y, (int) newHeadPos.z);
            if (world.getBlockState(blockPosCheck).isSolid()) {
                // Попытка подняться вверх
                Vec3d upPos = currentHead.add(0, STEP_SIZE, 0);
                if (!world.getBlockState(new BlockPos((int) upPos.x, (int) upPos.y, (int) upPos.z)).isSolid()) {
                    newHeadPos = upPos;
                } else {
                    // Попытка сбоку
                    Vec3d sideDir = new Vec3d(
                            random.nextBoolean() ? 1 : -1,
                            0,
                            random.nextBoolean() ? 1 : -1
                    ).normalize();
                    newHeadPos = currentHead.add(sideDir.multiply(STEP_SIZE));
                }
            }

            // Перемещаем змейку: добавляем новую голову и удаляем хвост
            snakeParts.add(0, newHeadPos);
            if (snakeParts.size() > SNAKE_LENGTH) {
                snakeParts.remove(snakeParts.size() - 1);
            }

            // Визуализация: спавним партиклы для всех частей змейки
            for (Vec3d part : snakeParts) {
                world.spawnParticles(
                        ParticleTypes.SOUL_FIRE_FLAME,
                        part.x, part.y, part.z,
                        1, 0, 0, 0, 0
                );
            }
        }
    }

    private static List<Vec3d> calculatePath(Vec3d start, Vec3d end, ServerWorld world) {
        List<Vec3d> path = new ArrayList<>();
        path.add(start);

        Vec3d current = start;
        Random random = world.getRandom();

        for (int i = 0; i < MAX_STEPS && current.distanceTo(end) > 2.0; i++) {
            Vec3d mainDirection = end.subtract(current).normalize();

            Vec3d randomOffset = new Vec3d(
                    (random.nextDouble() - 0.5) * RANDOMNESS,
                    (random.nextDouble() - 0.5) * RANDOMNESS,
                    (random.nextDouble() - 0.5) * RANDOMNESS
            );

            Vec3d newDirection = mainDirection.add(randomOffset).normalize();

            Vec3d nextPos = current.add(newDirection.multiply(STEP_SIZE));

            if (world.getBlockState(new BlockPos((int)nextPos.x, (int)nextPos.y, (int)nextPos.z)).isSolid()) {
                Vec3d upPos = current.add(0, STEP_SIZE, 0);
                if (!world.getBlockState(new BlockPos((int)upPos.x, (int)upPos.y, (int)upPos.z)).isSolid()) {
                    newDirection = new Vec3d(0,1,0);
                } else {
                    Vec3d sideDirection = new Vec3d(
                            random.nextBoolean() ? 1 : -1,
                            0,
                            random.nextBoolean() ? 1 : -1
                    ).normalize();
                    newDirection = sideDirection;
                }
                nextPos = current.add(newDirection.multiply(STEP_SIZE));
            }

            path.add(nextPos);
            current = nextPos;
        }

        path.add(end);
        return path;
    }
}