package net.feyhoan.bedtraveler.entity.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.feyhoan.bedtraveler.BedTraveler;
import net.feyhoan.bedtraveler.entity.ModEntities;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JellyBearEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private static final TrackedData<Integer> COLOR_VARIANT = DataTracker.registerData(JellyBearEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private int colorVariant = -1; // Временное хранилище для сервера
    public static final String COLOR_KEY = "Color";
    private boolean colorInitialized = false;

    public JellyBearEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COLOR_VARIANT, 0); // Значение по умолчанию
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        super.onTrackedDataSet(data);
        if (data.equals(COLOR_VARIANT)) {
            BedTraveler.LOGGER.info("Color updated to {}", getColorVariant());
        }
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        BedTraveler.LOGGER.info("JellyBearEntity/onSpawnPacket: Client received spawn packet");
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }


    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
        if (!this.colorInitialized && !this.getWorld().isClient()) {
            this.setColorVariant(this.random.nextInt(3));
            this.colorInitialized = true;
            BedTraveler.LOGGER.info("Initialized random color: {}", getColorVariant());
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AvoidSunlightGoal(this));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createJellyBearAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0); // Урон остаётся 0
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SLIME_ATTACK;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_SLIME_BLOCK_FALL;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_DOLPHIN_DEATH;
    }

    public void setColorVariant(int variant) {
        this.dataTracker.set(COLOR_VARIANT, variant);
    }

    public int getColorVariant() {
        return this.dataTracker.get(COLOR_VARIANT);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(COLOR_KEY, this.getColorVariant());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains(COLOR_KEY)) {
            int color = nbt.getInt(COLOR_KEY);
            this.setColorVariant(color);
            this.colorInitialized = true; // Помечаем как инициализированное
            BedTraveler.LOGGER.info("Color loaded from NBT: {}", color);
        }
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        // Сохраняем цвет перед вызовом super.onDeath()
        int color = this.getColorVariant();
        BedTraveler.LOGGER.info("JellyBearEntity/onDeath/Stored color before death: {}", color);

        super.onDeath(damageSource); // Вызываем родительский метод

        if (!getWorld().isClient()) {
            ItemStack drop = switch (color) { // Используем сохраненное значение
                case 1 -> new ItemStack(Items.RED_DYE);
                case 2 -> new ItemStack(Items.YELLOW_DYE);
                default -> new ItemStack(Items.GREEN_DYE);
            };
            BedTraveler.LOGGER.info("JellyBearEntity/onDeath/Dropping for color: {}", color);
            this.dropStack(drop);
        }
    }
}