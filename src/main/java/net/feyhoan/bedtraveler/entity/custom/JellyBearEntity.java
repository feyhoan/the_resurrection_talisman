package net.feyhoan.bedtraveler.entity.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.feyhoan.bedtraveler.BedTraveler;
import net.feyhoan.bedtraveler.entity.ModEntities;
import net.minecraft.entity.*;
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
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JellyBearEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    // Система цветов как у овец
    private static final TrackedData<Byte> COLOR = DataTracker.registerData(JellyBearEntity.class, TrackedDataHandlerRegistry.BYTE);
    public static final String COLOR_KEY = "Color";

    // Наши 3 цвета (0-зелёный, 1-красный, 2-жёлтый)
    public enum JellyBearColor {
        GREEN(0), RED(1), YELLOW(2);

        private final byte id;

        JellyBearColor(int id) {
            this.id = (byte)id;
        }

        public byte getId() {
            return id;
        }

        public static JellyBearColor byId(byte id) {
            return switch(id) {
                case 1 -> RED;
                case 2 -> YELLOW;
                default -> GREEN;
            };
        }
    }

    public JellyBearEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COLOR, (byte)0); // По умолчанию зелёный
    }

    // Генерация случайного цвета при спавне
    public static JellyBearColor generateDefaultColor(Random random) {
        int i = random.nextInt(100);
        if (i < 40) {
            return JellyBearColor.GREEN;
        } else if (i < 80) {
            return JellyBearColor.RED;
        } else {
            return JellyBearColor.YELLOW;
        }
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                 SpawnReason spawnReason, @Nullable EntityData entityData,
                                 @Nullable NbtCompound entityNbt) {
        // Устанавливаем случайный цвет при спавне в мире
        this.setColor(generateDefaultColor(world.getRandom()));
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public JellyBearColor getColor() {
        return JellyBearColor.byId(this.dataTracker.get(COLOR));
    }

    public void setColor(JellyBearColor color) {
        this.dataTracker.set(COLOR, color.getId());
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putByte(COLOR_KEY, this.getColor().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains(COLOR_KEY)) {
            this.setColor(JellyBearColor.byId(nbt.getByte(COLOR_KEY)));
        }
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);

        if (!this.getWorld().isClient()) {
            ItemStack drop = switch(this.getColor()) {
                case RED -> new ItemStack(Items.RED_DYE);
                case YELLOW -> new ItemStack(Items.YELLOW_DYE);
                default -> new ItemStack(Items.GREEN_DYE);
            };
            this.dropStack(drop);
        }
    }


    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
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
}