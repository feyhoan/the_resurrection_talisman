package net.feyhoan.the_resurrection_talisman.entity.client;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.entity.custom.LostSpiritEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


public class LostSpiritRenderer extends MobEntityRenderer<LostSpiritEntity, LostSpiritModel<LostSpiritEntity>> {
    private static final Identifier TEXTURE = new Identifier(TheResurrectionTalisman.MOD_ID, "textures/entity/lost_spirit.png");

    public LostSpiritRenderer(EntityRendererFactory.Context context, LostSpiritModel<LostSpiritEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public Identifier getTexture(LostSpiritEntity entity) {
        return TEXTURE;
    }


    @Override
    public void render(LostSpiritEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}