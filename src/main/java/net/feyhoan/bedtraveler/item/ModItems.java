package net.feyhoan.bedtraveler.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.feyhoan.bedtraveler.BedTraveler;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BedTraveler.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BedTraveler.LOGGER.info("Registering Mod Items for " + BedTraveler.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }


    public static final Item LOLLIPOP_SHARD = registerItem("lollipop_shard", new Item(new FabricItemSettings()));
    public static final Item LOLLIPOP = registerItem("lollipop", new Item(new FabricItemSettings().food(ModFoodComponents.LOLLIPOP)));

    public static final Item LOLLIPOP_NUGGET = registerItem("lollipop_nugget", new Item(new FabricItemSettings()));
    public static final Item LOLLIPOP_INGOT = registerItem("lollipop_ingot", new Item(new FabricItemSettings()));

    public static final Item LOLLIPOP_PICKAXE = registerItem("lollipop_pickaxe",
            new PickaxeItem(ModToolMaterial.LOLLIPOP, 1, 2f, new FabricItemSettings()));
    public static final Item LOLLIPOP_AXE = registerItem("lollipop_axe",
            new AxeItem(ModToolMaterial.LOLLIPOP, 2, 2f, new FabricItemSettings()));
    public static final Item LOLLIPOP_SWORD = registerItem("lollipop_sword",
            new SwordItem(ModToolMaterial.LOLLIPOP, 5, 2f, new FabricItemSettings()));


    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {

    }
}
