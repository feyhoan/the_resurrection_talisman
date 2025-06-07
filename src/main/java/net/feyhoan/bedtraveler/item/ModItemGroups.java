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
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BedTraveler.MOD_ID, "bedtraveler"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bedtraveler"))
                    .icon(() -> new ItemStack(Items.BLACK_BED)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RUBY);
                        entries.add(ModBlocks.RUBY_BLOCK);
                        entries.add(Items.BLACK_BED);

                    }).build());


    public static void registerItemGroups() {
        BedTraveler.LOGGER.info("Registering Item Groups for " + BedTraveler.MOD_ID);
    }
}