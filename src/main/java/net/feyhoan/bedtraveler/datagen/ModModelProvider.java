package net.feyhoan.bedtraveler.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.feyhoan.bedtraveler.block.ModBlocks;
import net.feyhoan.bedtraveler.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerLog(ModBlocks.CANDY_TREE_LOG).log(ModBlocks.CANDY_TREE_LOG).wood(ModBlocks.CANDY_TREE_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CANDY_TREE_LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.CANDY_TREE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        BlockStateModelGenerator.BlockTexturePool candytree_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CANDY_TREE_PLANKS);
        candytree_pool.family(ModBlocks.CANDY_TREE_FAMILY);


        blockStateModelGenerator.registerLog(ModBlocks.JELLY_TREE_LOG).log(ModBlocks.JELLY_TREE_LOG).wood(ModBlocks.JELLY_TREE_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.JELLY_TREE_LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.JELLY_TREE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        BlockStateModelGenerator.BlockTexturePool jellytree_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.JELLY_TREE_PLANKS);
        jellytree_pool.family(ModBlocks.JELLY_TREE_FAMILY);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LOLLIPOP_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.LOLLIPOP_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOLLIPOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOLLIPOP_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOLLIPOP_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOLLIPOP_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LOLLIPOP_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LOLLIPOP_SWORD, Models.HANDHELD);
    }
}