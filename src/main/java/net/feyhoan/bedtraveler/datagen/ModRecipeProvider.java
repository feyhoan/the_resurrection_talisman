package net.feyhoan.bedtraveler.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.feyhoan.bedtraveler.block.ModBlocks;
import net.feyhoan.bedtraveler.item.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    //private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(ModItems.RAW_RUBY,
    //        ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE, ModBlocks.NETHER_RUBY_ORE, ModBlocks.END_STONE_RUBY_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        /*offerSmelting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                0.7f, 200, "ruby");
        offerBlasting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                0.7f, 100, "ruby");*/

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.LOLLIPOP_NUGGET, RecipeCategory.MISC,
                ModItems.LOLLIPOP_INGOT);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.LOLLIPOP, 1)
                .pattern("LL ")
                .pattern("LS ")
                .pattern("  S")
                .input('S', Items.STICK)
                .input('L', ModItems.LOLLIPOP_SHARD)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.LOLLIPOP_SHARD), conditionsFromItem(ModItems.LOLLIPOP_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.LOLLIPOP)));


        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LOLLIPOP_AXE, 1)
                .pattern("LL ")
                .pattern("LS ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('L', ModItems.LOLLIPOP_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.LOLLIPOP_INGOT), conditionsFromItem(ModItems.LOLLIPOP_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.LOLLIPOP_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LOLLIPOP_SWORD, 1)
                .pattern(" L ")
                .pattern(" L ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('L', ModItems.LOLLIPOP_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.LOLLIPOP_INGOT), conditionsFromItem(ModItems.LOLLIPOP_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.LOLLIPOP_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LOLLIPOP_PICKAXE, 1)
                .pattern("LLL")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('L', ModItems.LOLLIPOP_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.LOLLIPOP_INGOT), conditionsFromItem(ModItems.LOLLIPOP_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.LOLLIPOP_PICKAXE)));


        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CANDY_TREE_PLANKS, 4)
                .input(ModBlocks.CANDY_TREE_LOG)
                .criterion("has_candy_tree_log", conditionsFromItem(ModBlocks.CANDY_TREE_LOG))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.CANDY_TREE_PLANKS)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.JELLY_TREE_PLANKS, 4)
                .input(ModBlocks.JELLY_TREE_LOG)
                .criterion("has_jelly_tree_log", conditionsFromItem(ModBlocks.JELLY_TREE_LOG))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.JELLY_TREE_PLANKS)));
    }
}