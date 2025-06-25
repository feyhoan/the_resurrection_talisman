package net.feyhoan.the_resurrection_talisman.world;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> CANDY_TREE_KEY = registerKey("candy_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> JELLY_TREE_KEY = registerKey("jelly_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_JELLY_TREE_KEY = registerKey("small_jelly_tree");

    public static final RegistryKey<ConfiguredFeature<?, ?>> LOLLIPOP_ORE_KEY = registerKey("lollipop_ore");
    /*

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> LollipopOre = List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.LOLLIPOP_ORE.getDefaultState()));
        register(context, LOLLIPOP_ORE_KEY, Feature.ORE, new OreFeatureConfig(LollipopOre, 10));

        register(context, CANDY_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.CANDY_TREE_LOG),
                new StraightTrunkPlacer(3, 1, 1),

                BlockStateProvider.of(ModBlocks.CANDY_TREE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2),

                new TwoLayersFeatureSize(1, 0, 2)).build());


        register(context, SMALL_JELLY_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.JELLY_TREE_LOG),
                new StraightTrunkPlacer(2, 1, 0), // Высота ствола около 8 блоков с небольшой вариацией

                BlockStateProvider.of(ModBlocks.JELLY_TREE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 2), // Пышная листва

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, JELLY_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.JELLY_TREE_LOG),
                new StraightTrunkPlacer(6, 2, 0), // Высота ствола около 8 блоков с небольшой вариацией

                BlockStateProvider.of(ModBlocks.JELLY_TREE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2), // Пышная листва

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }*/

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(TheResurrectionTalisman.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}