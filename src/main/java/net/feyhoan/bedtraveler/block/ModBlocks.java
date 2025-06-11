package net.feyhoan.bedtraveler.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.feyhoan.bedtraveler.BedTraveler;
import net.feyhoan.bedtraveler.world.tree.CandyTreeSaplingGenerator;
import net.feyhoan.bedtraveler.world.tree.JellyTreeSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block CANDY_TREE_LOG = registerBlock("candy_tree_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(2f)));
    public static final Block CANDY_TREE_WOOD = registerBlock("candy_tree_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(2f)));
    public static final Block CANDY_TREE_PLANKS = registerBlock("candy_tree_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(2f)));
    public static final Block CANDY_TREE_LEAVES = registerBlock("candy_tree_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(1f).nonOpaque()));
    public static final Block CANDY_TREE_SAPLING = registerBlock("candy_tree_sapling",
            new SaplingBlock(new CandyTreeSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

    public static final BlockFamily CANDY_TREE_FAMILY = BlockFamilies.register(ModBlocks.CANDY_TREE_PLANKS)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block JELLY_TREE_LOG = registerBlock("jelly_tree_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(2f)));
    public static final Block JELLY_TREE_WOOD = registerBlock("jelly_tree_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(2f)));
    public static final Block JELLY_TREE_PLANKS = registerBlock("jelly_tree_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(2f)));
    public static final Block JELLY_TREE_LEAVES = registerBlock("jelly_tree_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(1f).nonOpaque()));
    public static final Block JELLY_TREE_SAPLING = registerBlock("jelly_tree_sapling",
            new SaplingBlock(new JellyTreeSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

    public static final BlockFamily JELLY_TREE_FAMILY = BlockFamilies.register(ModBlocks.JELLY_TREE_PLANKS)
            .group("wooden").unlockCriterionName("has_planks").build();


    public static final Block LOLLIPOP_ORE = registerBlock("lollipop_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f), UniformIntProvider.create(2, 5)));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(BedTraveler.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(BedTraveler.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        BedTraveler.LOGGER.info("Registering ModBlocks for " + BedTraveler.MOD_ID);
    }
}