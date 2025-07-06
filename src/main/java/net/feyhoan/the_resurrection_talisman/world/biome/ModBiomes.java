package net.feyhoan.the_resurrection_talisman.world.biome;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.entity.ModEntities;
import net.feyhoan.the_resurrection_talisman.sounds.ModSounds;
import net.feyhoan.the_resurrection_talisman.world.ModPlacedFeatures;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> OVERWORLD_LIMBO = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TheResurrectionTalisman.MOD_ID, "overworld_limbo"));

    public static final RegistryKey<Biome> NETHER_LIMBO = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TheResurrectionTalisman.MOD_ID, "nether_limbo"));


    public static void boostrap(Registerable<Biome> context) {
        context.register(OVERWORLD_LIMBO, OverworldLimbo(context));
    }

    public static void OverworldLimboGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);

        // Добавляем замену травы
        builder.feature(
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                ModPlacedFeatures.REPLACE_SURFACE_PLACED
        );
    }

    public static Biome OverworldLimbo(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE,
                new SpawnSettings.SpawnEntry(ModEntities.LOST_SPIRIT, 7, 1, 3));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                );
        OverworldLimboGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.1f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x707070)
                        .waterFogColor(0x707070)
                        .skyColor(0x212121)
                        .grassColor(0x2a2a2a)
                        .foliageColor(0x3f3f3f)
                        .fogColor(0x0e0e0e)
                        .loopSound(ModSounds.LIMBO_AMBIENT)
                        .build())
                .build();
    }
}