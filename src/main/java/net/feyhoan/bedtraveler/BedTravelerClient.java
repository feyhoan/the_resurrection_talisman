package net.feyhoan.bedtraveler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.feyhoan.bedtraveler.block.ModBlocks;
import net.feyhoan.bedtraveler.entity.ModEntities;
import net.feyhoan.bedtraveler.entity.client.JellyBearModel;
import net.feyhoan.bedtraveler.entity.client.JellyBearRenderer;
import net.feyhoan.bedtraveler.entity.client.ModModelLayers;
import net.minecraft.client.render.RenderLayer;
import org.apache.logging.log4j.core.pattern.JAnsiTextRenderer;

public class BedTravelerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CANDY_TREE_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CANDY_TREE_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JELLY_TREE_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JELLY_TREE_SAPLING, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.JELLY_BEAR, context ->
                new JellyBearRenderer(
                        context,
                        new JellyBearModel<>(context.getPart(ModModelLayers.JELLY_BEAR)),
                        0.5f  // Shadow size - adjust as needed
                )
        );
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.JELLY_BEAR, JellyBearModel::getTexturedModelData);
    }
}
