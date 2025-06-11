package net.feyhoan.bedtraveler.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.feyhoan.bedtraveler.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.CANDY_TREE_PLANKS.asItem())
                .add(ModBlocks.JELLY_TREE_PLANKS.asItem());


        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(ModBlocks.CANDY_TREE_LOG.asItem())
                .add(ModBlocks.CANDY_TREE_WOOD.asItem())
                .add(ModBlocks.CANDY_TREE_LOG.asItem())
                .add(ModBlocks.CANDY_TREE_WOOD.asItem());
    }
}