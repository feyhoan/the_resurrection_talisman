package net.feyhoan.bedtraveler.world.dimensions;

import net.feyhoan.bedtraveler.BedTraveler;
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
    public static final RegistryKey<DimensionOptions> CANDYDIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(BedTraveler.MOD_ID, "candydim"));
    public static final RegistryKey<World> CANDYDIM_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(BedTraveler.MOD_ID, "candydim"));
    public static final RegistryKey<DimensionType> CANDYDIM_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(BedTraveler.MOD_ID, "candydim_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(CANDYDIM_DIM_TYPE, new DimensionType(
                OptionalLong.empty(),
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.5f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}