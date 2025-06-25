package net.feyhoan.the_resurrection_talisman.world.dimensions;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> OVERWORLD_LIMBO_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(TheResurrectionTalisman.MOD_ID, "overworld_limbo"));
    public static final RegistryKey<World> OVERWORLD_LIMBO_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(TheResurrectionTalisman.MOD_ID, "overworld_limbo"));
    public static final RegistryKey<DimensionType> OVERWORLD_LIMBO_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(TheResurrectionTalisman.MOD_ID, "overworld_limbo_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(OVERWORLD_LIMBO_DIM_TYPE, new DimensionType(
                OptionalLong.of(18000),
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.THE_NETHER_ID, // effectsLocation
                0.2f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}