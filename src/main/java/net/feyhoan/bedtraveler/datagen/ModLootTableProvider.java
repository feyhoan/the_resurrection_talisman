package net.feyhoan.bedtraveler.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.feyhoan.bedtraveler.block.ModBlocks;
import net.feyhoan.bedtraveler.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    public void generate() {
        addDrop(ModBlocks.CANDY_TREE_LOG);
        addDrop(ModBlocks.CANDY_TREE_WOOD);
        addDrop(ModBlocks.CANDY_TREE_PLANKS);
        addDrop(ModBlocks.CANDY_TREE_SAPLING);
        // Листья с шансом выпадения леденца
        addDrop(ModBlocks.CANDY_TREE_LEAVES, createCandyTreeLeavesLoot(ModBlocks.CANDY_TREE_LEAVES, ModBlocks.CANDY_TREE_SAPLING, ModItems.LOLLIPOP_SHARD));

        addDrop(ModBlocks.JELLY_TREE_LOG);
        addDrop(ModBlocks.JELLY_TREE_WOOD);
        addDrop(ModBlocks.JELLY_TREE_PLANKS);
        addDrop(ModBlocks.JELLY_TREE_SAPLING);
        //
        addDrop(ModBlocks.JELLY_TREE_LEAVES, createCandyTreeLeavesLoot(ModBlocks.JELLY_TREE_LEAVES, ModBlocks.JELLY_TREE_SAPLING, ModItems.LOLLIPOP_SHARD));


        addDrop(ModBlocks.LOLLIPOP_ORE, copperLikeOreDrops(ModBlocks.LOLLIPOP_ORE, ModItems.LOLLIPOP_NUGGET));
    }

    public LootTable.Builder createCandyTreeLeavesLoot(Block leaves, Block drop, Item dopDrop) {
        return dropsWithSilkTouchOrShears(
                leaves,
                addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop))
                        .conditionally(
                                TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.05F, 0.0625F, 0.083333336F)
                        )
        )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                                .with(
                                        applyExplosionDecay(leaves, ItemEntry.builder(Items.STICK))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                .conditionally(
                                                        TableBonusLootCondition.builder(Enchantments.FORTUNE,
                                                                0.02F,
                                                                0.022222223F,
                                                                0.025F,
                                                                0.033333335F
                                                        )
                                                )
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                                .with(
                                        applyExplosionDecay(leaves, ItemEntry.builder(dopDrop))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                .conditionally(
                                                        TableBonusLootCondition.builder(Enchantments.FORTUNE,
                                                                0.02F,
                                                                0.022222223F,
                                                                0.025F,
                                                                0.033333335F
                                                        )
                                                )
                                )
                );
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(1.0f, 3.0f))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}