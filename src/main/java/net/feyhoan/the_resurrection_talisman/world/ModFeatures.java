package net.feyhoan.the_resurrection_talisman.world;

import com.mojang.serialization.Codec;
import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ModFeatures {
    public static final Feature<DefaultFeatureConfig> REPLACE_SURFACE_FEATURE =
            new ReplaceSurfaceFeature(DefaultFeatureConfig.CODEC);

    public static void register() {
        Registry.register(Registries.FEATURE,
                new Identifier(TheResurrectionTalisman.MOD_ID, "replace_surface"),
                REPLACE_SURFACE_FEATURE);
    }

    public static class ReplaceSurfaceFeature extends Feature<DefaultFeatureConfig> {
        public ReplaceSurfaceFeature(Codec<DefaultFeatureConfig> configCodec) {
            super(configCodec);
        }

        @Override
        public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
            StructureWorldAccess world = context.getWorld();
            BlockPos origin = context.getOrigin();

            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = world.getTopY(); y >= world.getBottomY(); y--) {
                        BlockPos pos = origin.add(x, y, z);
                        BlockState state = world.getBlockState(pos);

                        // Заменяем траву
                        if (state.isOf(Blocks.GRASS_BLOCK)) {
                            world.setBlockState(pos, ModBlocks.OVERWORLD_LIMBO_GRASS_BLOCK.getDefaultState(), 3);
                        }
                        // Заменяем землю
                        else if (state.isOf(Blocks.DIRT)) {
                            world.setBlockState(pos, ModBlocks.OVERWORLD_LIMBO_DIRT.getDefaultState(), 3);
                        }
                    }
                }
            }
            return true;
        }
    }
}