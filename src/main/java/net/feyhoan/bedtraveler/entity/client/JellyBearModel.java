package net.feyhoan.bedtraveler.entity.client;

import net.feyhoan.bedtraveler.entity.animation.ModAnimations;
import net.feyhoan.bedtraveler.entity.custom.JellyBearEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import javax.swing.text.html.parser.Entity;

public class JellyBearModel<T extends JellyBearEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;

	public JellyBearModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("jelly_bear").getChild("body");
		this.head = body.getChild("head");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData jelly_bear = modelPartData.addChild("jelly_bear",
				ModelPartBuilder.create(),
				ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = jelly_bear.addChild("body",
				ModelPartBuilder.create(),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		body.addChild("torso", ModelPartBuilder.create()
						.uv(0, 15).cuboid(-4.0F, -13.0F, -2.0F, 8.0F, 13.0F, 5.0F)
						.uv(34, 0).cuboid(2.0F, -12.0F, -4.0F, 3.0F, 4.0F, 3.0F)
						.uv(34, 7).cuboid(-5.0F, -12.0F, -4.0F, 3.0F, 4.0F, 3.0F),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		body.addChild("right_leg", ModelPartBuilder.create()
						.uv(26, 21).cuboid(-5.0F, -4.0F, -4.0F, 3.0F, 4.0F, 3.0F),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		body.addChild("left_leg", ModelPartBuilder.create()
						.uv(26, 28).cuboid(-5.0F, -4.0F, -4.0F, 3.0F, 4.0F, 3.0F),
				ModelTransform.pivot(7.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -21.0F, -3.0F, 10.0F, 8.0F, 7.0F, new Dilation(0.0F))
				.uv(0, 33).cuboid(3.0F, -23.0F, -1.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.0F))
				.uv(12, 33).cuboid(-7.0F, -23.0F, -1.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.0F))
				.uv(26, 15).cuboid(-3.0F, -22.0F, -2.0F, 6.0F, 1.0F, 5.0F, new Dilation(0.0F))
				.uv(24, 35).cuboid(-2.0F, -17.0F, -4.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(JellyBearEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		//this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.JELLY_BEAR_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.JELLY_BEAR_IDLE, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}