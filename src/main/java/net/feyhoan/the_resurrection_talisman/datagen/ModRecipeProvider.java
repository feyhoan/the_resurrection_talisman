package net.feyhoan.the_resurrection_talisman.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.feyhoan.the_resurrection_talisman.item.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

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
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.RESURRECTION_TALISMAN, 1)
                .pattern(" T ")
                .pattern("SNS")
                .pattern(" N ")
                .input('T', Items.TOTEM_OF_UNDYING)
                .input('S', ModItems.SHARD_OF_THE_SOULS_PRISM)
                .input('N', Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.TOTEM_OF_UNDYING), conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .criterion(hasItem(ModItems.SHARD_OF_THE_SOULS_PRISM), conditionsFromItem(ModItems.SHARD_OF_THE_SOULS_PRISM))
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RESURRECTION_TALISMAN)));

    }
}