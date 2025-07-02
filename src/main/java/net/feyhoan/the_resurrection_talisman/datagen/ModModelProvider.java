package net.feyhoan.the_resurrection_talisman.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.feyhoan.the_resurrection_talisman.block.ModBlocks;
import net.feyhoan.the_resurrection_talisman.item.ModItems;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Регистрация кастомного блока травы
        TexturedModel.Factory factory = TexturedModel.makeFactory(
                block -> new TextureMap()
                        .put(TextureKey.SIDE, new Identifier("the_resurrection_talisman", "block/overworld_limbo_grass_block_side"))
                        .put(TextureKey.TOP, new Identifier("the_resurrection_talisman", "block/overworld_limbo_grass_block_top"))
                        .put(TextureKey.BOTTOM, new Identifier("the_resurrection_talisman", "block/overworld_limbo_grass_block_bottom")),
                Models.CUBE_BOTTOM_TOP);

        blockStateModelGenerator.registerSingleton(ModBlocks.OVERWORLD_LIMBO_GRASS_BLOCK, factory);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RESURRECTION_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHARD_OF_THE_SOULS_PRISM, Models.GENERATED);
    }
}