package net.feyhoan.the_resurrection_talisman.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup THERESURRECTIONTALISMAN_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TheResurrectionTalisman.MOD_ID, "the_resurrection_talisman"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.the_resurrection_talisman"))
                    .icon(() -> new ItemStack(Items.BLACK_BED)).entries((displayContext, entries) -> {
                    }).build());


    public static void registerItemGroups() {
        TheResurrectionTalisman.LOGGER.info("Registering Item Groups for " + TheResurrectionTalisman.MOD_ID);
    }
}