package net.feyhoan.bedtraveler;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.feyhoan.bedtraveler.block.ModBlocks;
import net.feyhoan.bedtraveler.entity.ModEntities;
import net.feyhoan.bedtraveler.entity.custom.JellyBearEntity;
import net.feyhoan.bedtraveler.item.ModItemGroups;
import net.feyhoan.bedtraveler.item.ModItems;
import net.feyhoan.bedtraveler.world.gen.ModWorldGeneration;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
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
		ModEntities.registerModEntities();
		ModWorldGeneration.generateModWorldGen();

		FabricDefaultAttributeRegistry.register(ModEntities.JELLY_BEAR, JellyBearEntity.createJellyBearAttributes());


		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.LAPIS_BLOCK)
				.lightWithItem(Items.COOKIE)
				.destDimID(new Identifier(BedTraveler.MOD_ID, "candydim"))
				.tintColor(0xc2ff00)
				.registerPortal();
	}
}