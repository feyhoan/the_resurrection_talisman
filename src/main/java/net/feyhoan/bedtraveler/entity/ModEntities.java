package net.feyhoan.bedtraveler.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.feyhoan.bedtraveler.BedTraveler;
import net.feyhoan.bedtraveler.entity.custom.JellyBearEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<JellyBearEntity> JELLY_BEAR = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(BedTraveler.MOD_ID, "jelly_bear"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, JellyBearEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static void registerModEntities() {
        BedTraveler.LOGGER.info("Registering Entities for " + BedTraveler.MOD_ID);
    }
}