package net.feyhoan.the_resurrection_talisman.mixin;

import net.feyhoan.the_resurrection_talisman.item.ModItems;
import net.feyhoan.the_resurrection_talisman.util.DeathData;
import net.feyhoan.the_resurrection_talisman.world.dimensions.ModDimensions;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinServerPlayerEntity {
    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    private void handleLimboTransition(DamageSource source, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;

        if (player.getWorld().getRegistryKey() == World.OVERWORLD && hasTalisman(player)) {
            DeathData.storeInventoryAndPosition(player);
            clearInventoryExceptTalisman(player);
            ci.cancel();
            teleportToLimbo(player);
        }
    }

    private void teleportToLimbo(ServerPlayerEntity player) {

        Random random = player.getRandom();
        double offsetX = (random.nextBoolean() ? 1 : -1) * (80 + random.nextDouble() * 70);
        double offsetZ = (random.nextBoolean() ? 1 : -1) * (80 + random.nextDouble() * 70);

        ServerWorld limbo = player.server.getWorld(ModDimensions.OVERWORLD_LIMBO_LEVEL_KEY);
        player.teleport(limbo,
                player.getX() + offsetX,
                150, // Высота
                player.getZ() + offsetZ,
                player.getYaw(),
                player.getPitch()
        );


        player.setHealth(player.getMaxHealth());
        player.getHungerManager().setFoodLevel(0);
    }

    private boolean hasTalisman(PlayerEntity player) {
        return player.getInventory().contains(ModItems.RESURRECTION_TALISMAN.getDefaultStack());
    }

    private void clearInventoryExceptTalisman(ServerPlayerEntity player) {
        PlayerInventory inv = player.getInventory();

        // Очищаем основной инвентарь
        for(int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if(!stack.isOf(ModItems.RESURRECTION_TALISMAN)) {
                inv.setStack(i, ItemStack.EMPTY);
            }
        }

        // Очищаем броню
        for(int i = 0; i < inv.armor.size(); i++) {
            inv.armor.set(i, ItemStack.EMPTY);
        }

        // Очищаем оффхенд
        inv.offHand.set(0, ItemStack.EMPTY);
    }
}