package net.feyhoan.bedtraveler.entity.client;

import net.feyhoan.bedtraveler.BedTraveler;
import net.feyhoan.bedtraveler.entity.custom.JellyBearEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Random;

public class JellyBearRenderer extends MobEntityRenderer<JellyBearEntity, JellyBearModel<JellyBearEntity>> {
    private static final Map<Integer, Identifier> TEXTURES = Map.of(
            0, new Identifier(BedTraveler.MOD_ID, "textures/entity/jelly_bear_green.png"),
            1, new Identifier(BedTraveler.MOD_ID, "textures/entity/jelly_bear_red.png"),
            2, new Identifier(BedTraveler.MOD_ID, "textures/entity/jelly_bear_yellow.png")
    );

    private final Random random = new Random();

    public JellyBearRenderer(EntityRendererFactory.Context context, JellyBearModel<JellyBearEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public Identifier getTexture(JellyBearEntity entity) {
        int colorVariant = entity.getColorVariant();
        BedTraveler.LOGGER.info("JellyBearRenderer/getTexture: Rendering with color {}", colorVariant);
        return TEXTURES.getOrDefault(colorVariant, TEXTURES.get(0));
    }



    @Override
    public void render(JellyBearEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        matrixStack.scale(1f, 1f, 1f);

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}