package net.feyhoan.the_resurrection_talisman.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.item.custom.ResurrectionTalisman;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TheResurrectionTalisman.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TheResurrectionTalisman.LOGGER.info("Registering Mod Items for " + TheResurrectionTalisman.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToIngredientItemGroup);
    }


    public static final Item RESURRECTION_TALISMAN = registerItem("resurrection_talisman", new ResurrectionTalisman(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));


    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(RESURRECTION_TALISMAN);
    }
}
