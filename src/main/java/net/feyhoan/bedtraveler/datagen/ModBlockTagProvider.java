package net.feyhoan.bedtraveler.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.feyhoan.bedtraveler.block.ModBlocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        /*getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.RUBY_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RUBY_BLOCK);*/

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.CANDY_TREE_LOG)
                .add(ModBlocks.CANDY_TREE_WOOD)
                .add(ModBlocks.JELLY_TREE_LOG)
                .add(ModBlocks.JELLY_TREE_WOOD);


        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.CANDY_TREE_LOG)
                .add(ModBlocks.CANDY_TREE_WOOD)
                .add(ModBlocks.CANDY_TREE_PLANKS)
                .add(ModBlocks.JELLY_TREE_LOG)
                .add(ModBlocks.JELLY_TREE_WOOD)
                .add(ModBlocks.CANDY_TREE_PLANKS);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.LOLLIPOP_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.LOLLIPOP_ORE);

    }
}