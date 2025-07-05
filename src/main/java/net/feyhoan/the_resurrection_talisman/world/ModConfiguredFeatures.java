package net.feyhoan.the_resurrection_talisman.world;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> REPLACE_GRASS =
            registerKey("replace_grass");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // Получаем конкретную реализацию Feature
        Feature<DefaultFeatureConfig> feature = ModFeatures.REPLACE_GRASS_FEATURE;

        // Создаем ConfiguredFeature с явным указанием типов
        ConfiguredFeature<DefaultFeatureConfig, Feature<DefaultFeatureConfig>> configuredFeature =
                new ConfiguredFeature<>(feature, new DefaultFeatureConfig());

        context.register(REPLACE_GRASS, configuredFeature);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE,
                new Identifier(TheResurrectionTalisman.MOD_ID, name));
    }
}