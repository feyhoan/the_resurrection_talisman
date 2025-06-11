package net.feyhoan.bedtraveler.entity.client;

import net.feyhoan.bedtraveler.BedTraveler;
import net.feyhoan.bedtraveler.entity.custom.JellyBearEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class JellyBearRenderer extends MobEntityRenderer<JellyBearEntity, JellyBearModel<JellyBearEntity>> {
    private static final Identifier TEXTURE = new Identifier(BedTraveler.MOD_ID, "textures/entity/jelly_bear.png");

    public JellyBearRenderer(EntityRendererFactory.Context context, JellyBearModel<JellyBearEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public Identifier getTexture(JellyBearEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(JellyBearEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        matrixStack.scale(1f, 1f, 1f);

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}