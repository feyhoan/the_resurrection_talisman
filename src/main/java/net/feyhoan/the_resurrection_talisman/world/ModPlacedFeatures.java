package net.feyhoan.the_resurrection_talisman.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> REPLACE_SURFACE_PLACED =
            registerKey("replace_surface_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        RegistryEntry<ConfiguredFeature<?, ?>> configuredFeature =
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
                        .getOrThrow(ModConfiguredFeatures.REPLACE_SURFACE);

        context.register(REPLACE_SURFACE_PLACED,
                new PlacedFeature(configuredFeature, List.of()));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                new Identifier(TheResurrectionTalisman.MOD_ID, name));
    }
}