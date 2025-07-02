package net.feyhoan.the_resurrection_talisman.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.entity.custom.LostSpiritEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<LostSpiritEntity> LOST_SPIRIT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheResurrectionTalisman.MOD_ID, "lost_spirit"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LostSpiritEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static void registerModEntities() {
        TheResurrectionTalisman.LOGGER.info("Registering Entities for " + TheResurrectionTalisman.MOD_ID);
    }
}