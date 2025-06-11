package net.feyhoan.bedtraveler.world.biome.surface;

import net.feyhoan.bedtraveler.block.ModBlocks;
import net.feyhoan.bedtraveler.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRANITE = makeStateRule(Blocks.GRANITE);
    private static final MaterialRules.MaterialRule POLISHED_GRANITE = makeStateRule(Blocks.POLISHED_GRANITE);

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}