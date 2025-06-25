package net.feyhoan.the_resurrection_talisman;

import net.fabricmc.api.ModInitializer;

import net.feyhoan.the_resurrection_talisman.block.ModBlocks;
import net.feyhoan.the_resurrection_talisman.item.ModItemGroups;
import net.feyhoan.the_resurrection_talisman.item.ModItems;
import net.feyhoan.the_resurrection_talisman.world.gen.ModWorldGeneration;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheResurrectionTalisman implements ModInitializer {
	public static final String MOD_ID = "the_resurrection_talisman";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		//ModEntities.registerModEntities();
		ModWorldGeneration.generateModWorldGen();

		//FabricDefaultAttributeRegistry.register(ModEntities.JELLY_BEAR, JellyBearEntity.createJellyBearAttributes());

		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.NETHERITE_BLOCK)
				.lightWithItem(Items.GUNPOWDER)
				.destDimID(new Identifier(TheResurrectionTalisman.MOD_ID, "overworld_limbo"))
				.tintColor(0x999999)
				.registerPortal();
	}
}