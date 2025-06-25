package net.feyhoan.the_resurrection_talisman;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.feyhoan.the_resurrection_talisman.block.ModBlocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.server.network.ServerPlayerEntity;

public class TheResurrectionTalismanClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        /*EntityRendererRegistry.register(ModEntities.JELLY_BEAR, context ->
                new JellyBearRenderer(
                        context,
                        new JellyBearModel<>(context.getPart(ModModelLayers.JELLY_BEAR)),
                        0.5f  // Shadow size - adjust as needed
                )
        );
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.JELLY_BEAR, JellyBearModel::getTexturedModelData);*/

        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;

            // Проверяем, что игрок в нужном измерении
            if (client.player.getWorld().getRegistryKey().getValue().toString()
                    .equals("the_resurrection_talisman:overworld_limbo")) {
                // Устанавливаем очень близкий туман (4-8 блоков)
                RenderSystem.setShaderFogStart(4.0F);
                RenderSystem.setShaderFogEnd(8.0F);
            }
        });
    }
}
