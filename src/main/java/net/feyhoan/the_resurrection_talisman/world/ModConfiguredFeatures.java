package net.feyhoan.the_resurrection_talisman.world;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> REPLACE_SURFACE =
            registerKey("replace_surface");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        Feature<DefaultFeatureConfig> feature = ModFeatures.REPLACE_SURFACE_FEATURE;
        ConfiguredFeature<DefaultFeatureConfig, Feature<DefaultFeatureConfig>> configuredFeature =
                new ConfiguredFeature<>(feature, new DefaultFeatureConfig());

        context.register(REPLACE_SURFACE, configuredFeature);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE,
                new Identifier(TheResurrectionTalisman.MOD_ID, name));
    }
}