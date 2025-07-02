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
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> OVERWORLD_LIMBO = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TheResurrectionTalisman.MOD_ID, "overworld_limbo"));

    public static final RegistryKey<Biome> NETHER_LIMBO = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TheResurrectionTalisman.MOD_ID, "nether_limbo"));


    public static void boostrap(Registerable<Biome> context) {
        context.register(OVERWORLD_LIMBO, LollipopValley(context));
        //context.register(JELLY_BEAN_FOREST, JellyBeanForest(context));
    }

    public static void OverworldLimboGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
    }

    public static Biome LollipopValley(Registerable<Biome> context) {
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

    public static void JellyBeanForestOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        //builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.LOLLIPOP_ORE_PLACED_KEY);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_EMERALD);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_LAPIS);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GOLD);
        DefaultBiomeFeatures.addLandCarvers(builder);
    }

    public static Biome JellyBeanForest(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        //spawnBuilder.spawn(SpawnGroup.CREATURE,
        //        new SpawnSettings.SpawnEntry(
        //                ModEntities.JELLY_BEAR,
        //                7,  // Вес спавна
        //                2,  // Минимальная группа
        //                4   // Максимальная группа
        //        ));

        // Добавьте это для логов:
        TheResurrectionTalisman.LOGGER.info("JellyBeanForest biome initialized with JellyBear spawns");

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                );

        JellyBeanForestOverworldGeneration(biomeBuilder);


        // Добавляем разнообразие растительности
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);
        DefaultBiomeFeatures.addForestGrass(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        // Добавляем уникальные объекты (если есть)
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.JELLY_TREE_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SMALL_JELLY_TREE_PLACED_KEY);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.3f)
                .temperature(0.9f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x0087ff)
                        .waterFogColor(0x0062b9)
                        .skyColor(0xb0fcf9)
                        .grassColor(0x399908)
                        .foliageColor(0x399908)
                        .fogColor(0x1d5600)
                        .moodSound(BiomeMoodSound.CAVE)
                        .build())
                .build();
    }
}