package net.feyhoan.the_resurrection_talisman.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.world.tree.CandyTreeSaplingGenerator;
import net.feyhoan.the_resurrection_talisman.world.tree.JellyTreeSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TheResurrectionTalisman.MOD_ID, name), block);
    }

    public static final Block OVERWORLD_LIMBO_GRASS_BLOCK = registerBlock("overworld_limbo_grass_block",
            new Block(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK)));
    public static final Block OVERWORLD_LIMBO_DIRT = registerBlock("overworld_limbo_dirt",
            new Block(FabricBlockSettings.copyOf(Blocks.DIRT)));


    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TheResurrectionTalisman.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        TheResurrectionTalisman.LOGGER.info("Registering ModBlocks for " + TheResurrectionTalisman.MOD_ID);
    }
}