package net.feyhoan.bedtraveler.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.feyhoan.bedtraveler.BedTraveler;
import net.feyhoan.bedtraveler.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup BEDTRAVELER_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BedTraveler.MOD_ID, "bedtraveler"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bedtraveler"))
                    .icon(() -> new ItemStack(Items.BLACK_BED)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.CANDY_TREE_LOG);
                        entries.add(ModBlocks.CANDY_TREE_WOOD);
                        entries.add(ModBlocks.CANDY_TREE_PLANKS);
                        entries.add(ModBlocks.CANDY_TREE_LEAVES);

                        entries.add(ModBlocks.JELLY_TREE_LOG);
                        entries.add(ModBlocks.JELLY_TREE_WOOD);
                        entries.add(ModBlocks.JELLY_TREE_PLANKS);
                        entries.add(ModBlocks.JELLY_TREE_LEAVES);

                        entries.add(ModBlocks.CANDY_TREE_SAPLING);
                        entries.add(ModBlocks.JELLY_TREE_SAPLING);

                        entries.add(ModBlocks.LOLLIPOP_ORE);
                        entries.add(ModItems.LOLLIPOP_SHARD);
                        entries.add(ModItems.LOLLIPOP);
                        entries.add(ModItems.LOLLIPOP_NUGGET);
                        entries.add(ModItems.LOLLIPOP_INGOT);
                        entries.add(ModItems.LOLLIPOP_SWORD);
                        entries.add(ModItems.LOLLIPOP_PICKAXE);
                        entries.add(ModItems.LOLLIPOP_AXE);
                    }).build());


    public static void registerItemGroups() {
        BedTraveler.LOGGER.info("Registering Item Groups for " + BedTraveler.MOD_ID);
    }
}