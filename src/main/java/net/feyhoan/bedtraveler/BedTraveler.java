package net.feyhoan.bedtraveler;

import net.fabricmc.api.ModInitializer;

import net.feyhoan.bedtraveler.block.ModBlocks;
import net.feyhoan.bedtraveler.item.ModItemGroups;
import net.feyhoan.bedtraveler.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BedTraveler implements ModInitializer {
	public static final String MOD_ID = "bedtraveler";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}