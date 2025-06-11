package net.feyhoan.bedtraveler.mixin;

import net.feyhoan.bedtraveler.world.dimensions.ModDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    private final Random random = new Random();

    @Inject(method = "trySleep", at = @At("HEAD"), cancellable = true)
    private void onTrySleep(BlockPos pos, CallbackInfoReturnable<PlayerEntity.SleepFailureReason> cir) {
        if (random.nextFloat() < 0.3f) {
            PlayerEntity player = (PlayerEntity) (Object) this;
            if (player instanceof ServerPlayerEntity serverPlayer) {
                ServerWorld customWorld = serverPlayer.getServer().getWorld(RegistryKey.of(RegistryKeys.WORLD, new Identifier("bedtraveler", "candydim")));
                if (customWorld != null) {
                    // Устанавливаем точку возрождения
                    serverPlayer.setSpawnPoint(customWorld.getRegistryKey(), new BlockPos(1, 75, 1), 0, false, false);

                    // Телепортируем игрока
                    serverPlayer.teleport(customWorld, 1.5, 75, 1.5, PositionFlag.ROT, 0, 0);

                    serverPlayer.sendMessage(Text.literal("Сьешь 10 лалипопав"), false);

                }
            }
            cir.setReturnValue(null);
            cir.cancel();
        }
    }
}