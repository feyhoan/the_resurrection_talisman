package net.feyhoan.bedtraveler.world.biome;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.feyhoan.bedtraveler.BedTraveler;
import net.feyhoan.bedtraveler.world.ModPlacedFeatures;
import net.minecraft.client.sound.MusicType;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.entity.EntityType;
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
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.trunk.MegaJungleTrunkPlacer;

public class ModBiomes {
    public static final RegistryKey<Biome> LOLLIPOP_VALLEY = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(BedTraveler.MOD_ID, "lollipop_valley"));

    public static final RegistryKey<Biome> JELLY_BEAN_FOREST = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(BedTraveler.MOD_ID, "jelly_bean_forest"));


    public static void boostrap(Registerable<Biome> context) {
        context.register(LOLLIPOP_VALLEY, LollipopValley(context));
        context.register(JELLY_BEAN_FOREST, JellyBeanForest(context));
    }

    public static void LollipopValleyOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.LOLLIPOP_ORE_PLACED_KEY);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIAMOND);
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
    }

    public static Biome LollipopValley(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 2, 5));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                );

        LollipopValleyOverworldGeneration(biomeBuilder);


        // Добавляем разнообразие растительности
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);
        DefaultBiomeFeatures.addForestGrass(biomeBuilder);

        // Добавляем уникальные объекты (если есть)
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.CANDY_TREE_PLACED_KEY);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xFFFFFF)
                        .waterFogColor(0x131313)
                        .skyColor(0x9fbbe8)
                        .grassColor(0xfda3e9)
                        .foliageColor(0xffd7f4)
                        .fogColor(0xf7f1f5)
                        .moodSound(BiomeMoodSound.CAVE)
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
        //spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 2, 5));

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
                .downfall(0.5f)
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