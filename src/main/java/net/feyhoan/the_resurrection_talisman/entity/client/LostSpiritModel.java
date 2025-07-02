package net.feyhoan.the_resurrection_talisman.entity.client;

import net.feyhoan.the_resurrection_talisman.entity.animation.ModAnimations;
import net.feyhoan.the_resurrection_talisman.entity.custom.LostSpiritEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import javax.swing.text.html.parser.Entity;


public class LostSpiritModel<T extends LostSpiritEntity> extends SinglePartEntityModel<T> {
	private final ModelPart lost_spirit;

	public LostSpiritModel(ModelPart root) {
		this.lost_spirit = root.getChild("lost_spirit");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData lost_spirit = modelPartData.addChild("lost_spirit", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.0F, 0.0F));

		ModelPartData body = lost_spirit.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 22).cuboid(-4.8473F, -12.5997F, -2.8688F, 10.0F, 20.0F, 6.0F, new Dilation(0.0F))
				.uv(50, 56).cuboid(4.1527F, 7.4003F, -2.5688F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(56, 55).cuboid(-0.8473F, 7.4003F, -2.5688F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.1527F, -17.3943F, -0.557F));

		ModelPartData cube_r1 = torso.addChild("cube_r1", ModelPartBuilder.create().uv(48, 32).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.8473F, 2.4003F, -2.9688F, -0.0524F, 0.0F, 0.0F));

		ModelPartData cube_r2 = torso.addChild("cube_r2", ModelPartBuilder.create().uv(28, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.1527F, -4.5997F, -2.9688F, -0.0524F, 0.0F, 0.0F));

		ModelPartData cube_r3 = torso.addChild("cube_r3", ModelPartBuilder.create().uv(32, 54).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.1527F, 2.8003F, 3.2312F, 0.0349F, 0.0F, 0.0F));

		ModelPartData cube_r4 = torso.addChild("cube_r4", ModelPartBuilder.create().uv(36, 54).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.8473F, 3.8003F, 3.2312F, 0.0349F, 0.0F, 0.0F));

		ModelPartData cube_r5 = torso.addChild("cube_r5", ModelPartBuilder.create().uv(40, 54).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.8473F, -2.1997F, 3.2312F, 0.0349F, 0.0F, 0.0F));

		ModelPartData cube_r6 = torso.addChild("cube_r6", ModelPartBuilder.create().uv(44, 54).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.1527F, -3.1997F, 3.2312F, 0.0349F, 0.0F, 0.0F));

		ModelPartData cube_r7 = torso.addChild("cube_r7", ModelPartBuilder.create().uv(56, 3).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.8473F, -6.1997F, 3.2312F, 0.0349F, 0.0F, 0.0F));

		ModelPartData cube_r8 = torso.addChild("cube_r8", ModelPartBuilder.create().uv(4, 56).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.8473F, -8.1997F, 3.2312F, 0.0349F, 0.0F, 0.0F));

		ModelPartData cube_r9 = torso.addChild("cube_r9", ModelPartBuilder.create().uv(8, 56).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.1527F, -9.1997F, 3.2312F, 0.0349F, 0.0F, 0.0F));

		ModelPartData cube_r10 = torso.addChild("cube_r10", ModelPartBuilder.create().uv(48, 24).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.1527F, 1.4003F, -2.9688F, -0.0524F, 0.0F, 0.0F));

		ModelPartData cube_r11 = torso.addChild("cube_r11", ModelPartBuilder.create().uv(24, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.8473F, -3.5997F, -2.9688F, -0.0524F, 0.0F, 0.0F));

		ModelPartData cube_r12 = torso.addChild("cube_r12", ModelPartBuilder.create().uv(48, 3).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.2527F, -3.5997F, 2.0312F, 0.0F, 1.5708F, -0.0425F));

		ModelPartData cube_r13 = torso.addChild("cube_r13", ModelPartBuilder.create().uv(4, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.2527F, 3.4003F, 1.0312F, 0.0F, 1.5708F, -0.0425F));

		ModelPartData cube_r14 = torso.addChild("cube_r14", ModelPartBuilder.create().uv(8, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.2527F, 1.4003F, -1.9688F, 0.0F, 1.5708F, -0.0425F));

		ModelPartData cube_r15 = torso.addChild("cube_r15", ModelPartBuilder.create().uv(12, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.2527F, -4.5997F, -0.9688F, 0.0F, 1.5708F, -0.0425F));

		ModelPartData cube_r16 = torso.addChild("cube_r16", ModelPartBuilder.create().uv(16, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.2527F, -10.5997F, 1.0312F, 0.0F, 1.5708F, -0.0425F));

		ModelPartData cube_r17 = torso.addChild("cube_r17", ModelPartBuilder.create().uv(48, 16).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.2527F, -9.5997F, -1.9688F, 0.0F, 1.5708F, -0.0425F));

		ModelPartData cube_r18 = torso.addChild("cube_r18", ModelPartBuilder.create().uv(52, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.9473F, -3.5997F, 2.0312F, 0.0F, 1.5708F, 0.0622F));

		ModelPartData cube_r19 = torso.addChild("cube_r19", ModelPartBuilder.create().uv(52, 40).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.9473F, 3.4003F, 1.0312F, 0.0F, 1.5708F, 0.0622F));

		ModelPartData cube_r20 = torso.addChild("cube_r20", ModelPartBuilder.create().uv(52, 32).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.9473F, 1.4003F, -1.9688F, 0.0F, 1.5708F, 0.0622F));

		ModelPartData cube_r21 = torso.addChild("cube_r21", ModelPartBuilder.create().uv(52, 24).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.9473F, -4.5997F, -0.9688F, 0.0F, 1.5708F, 0.0622F));

		ModelPartData cube_r22 = torso.addChild("cube_r22", ModelPartBuilder.create().uv(52, 16).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.9473F, -10.5997F, 1.0312F, 0.0F, 1.5708F, 0.0622F));

		ModelPartData cube_r23 = torso.addChild("cube_r23", ModelPartBuilder.create().uv(52, 3).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.9473F, -9.5997F, -1.9688F, 0.0F, 1.5708F, 0.0622F));

		ModelPartData cube_r24 = torso.addChild("cube_r24", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.8473F, -9.5997F, -2.9688F, -0.0524F, 0.0F, 0.0F));

		ModelPartData cube_r25 = torso.addChild("cube_r25", ModelPartBuilder.create().uv(48, 40).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.1527F, -10.5997F, -2.9688F, -0.0524F, 0.0F, 0.0F));

		ModelPartData cube_r26 = torso.addChild("cube_r26", ModelPartBuilder.create().uv(20, 48).cuboid(-1.0F, -4.0F, 0.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.8473F, -7.5997F, -2.9688F, -0.0524F, 0.0F, 0.0F));

		ModelPartData cube_r27 = torso.addChild("cube_r27", ModelPartBuilder.create().uv(48, 11).cuboid(-4.0F, -1.5F, 0.0F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(58, 36).cuboid(0.0F, -1.5F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1527F, 8.6003F, 2.4312F, 3.1416F, 0.0F, -3.098F));

		ModelPartData cube_r28 = torso.addChild("cube_r28", ModelPartBuilder.create().uv(58, 38).cuboid(-5.0F, -1.5F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1527F, 8.6003F, 2.4312F, -3.0544F, 0.0038F, -3.1414F));

		ModelPartData cube_r29 = torso.addChild("cube_r29", ModelPartBuilder.create().uv(52, 56).cuboid(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1527F, 8.6003F, 2.4312F, 3.1416F, 0.0F, 3.098F));

		ModelPartData cube_r30 = torso.addChild("cube_r30", ModelPartBuilder.create().uv(56, 24).cuboid(2.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1527F, 8.6003F, 2.8312F, 3.1416F, 0.0F, -3.098F));

		ModelPartData cube_r31 = torso.addChild("cube_r31", ModelPartBuilder.create().uv(56, 36).cuboid(4.0043F, -1.8001F, -2.5F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.144F, 8.9003F, -0.0688F, 3.1416F, 0.0F, -3.098F));

		ModelPartData cube_r32 = torso.addChild("cube_r32", ModelPartBuilder.create().uv(16, 56).cuboid(-3.9912F, -1.5F, -2.5F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.144F, 8.9003F, -0.0688F, 0.0F, 0.0F, 0.0436F));

		ModelPartData cube_r33 = torso.addChild("cube_r33", ModelPartBuilder.create().uv(58, 40).cuboid(-4.9912F, -1.5F, -2.5F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.244F, 8.7003F, -0.0688F, 0.0F, 0.0F, -0.0436F));

		ModelPartData cube_r34 = torso.addChild("cube_r34", ModelPartBuilder.create().uv(58, 42).cuboid(0.0088F, -1.5F, -2.5F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.144F, 8.9003F, -0.0688F, -0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r35 = torso.addChild("cube_r35", ModelPartBuilder.create().uv(56, 27).cuboid(2.0088F, -1.5F, -2.5F, 2.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.144F, 8.9003F, -0.0688F, 0.0F, 0.0F, -0.0436F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -4.9761F, -0.478F, 12.0F, 12.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.1527F, -19.7018F, -4.196F));

		ModelPartData cube_r36 = head.addChild("cube_r36", ModelPartBuilder.create().uv(44, 15).cuboid(-4.0F, -2.0F, -5.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F))
				.uv(30, 56).cuboid(2.0F, -2.0F, -5.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F))
				.uv(28, 56).cuboid(-5.0F, -2.0F, -5.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F))
				.uv(44, 0).cuboid(-5.0F, -5.0F, -5.0F, 8.0F, 3.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 48).cuboid(3.0F, -5.0F, -5.0F, 2.0F, 12.0F, 0.0F, new Dilation(0.0F))
				.uv(44, 3).cuboid(-7.0F, -5.0F, -5.0F, 2.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.4239F, 4.222F, -0.0524F, 0.0F, 0.0F));

		ModelPartData left_arm = body.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(11.0924F, -19.4284F, -4.1808F));

		ModelPartData cube_r37 = left_arm.addChild("cube_r37", ModelPartBuilder.create().uv(44, 16).cuboid(2.9614F, -1.4298F, -0.1449F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.0151F, -0.1085F, -1.4357F, -1.5482F, -1.135F, 1.0082F));

		ModelPartData cube_r38 = left_arm.addChild("cube_r38", ModelPartBuilder.create().uv(56, 30).cuboid(3.0387F, -1.0702F, -1.8551F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0849F, 0.3915F, -1.5357F, -1.5482F, -1.135F, 1.0082F));

		ModelPartData cube_r39 = left_arm.addChild("cube_r39", ModelPartBuilder.create().uv(56, 40).cuboid(-0.0386F, -1.4298F, 0.8551F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.3151F, -1.0085F, -1.6357F, -1.5482F, -1.135F, 1.0082F));

		ModelPartData cube_r40 = left_arm.addChild("cube_r40", ModelPartBuilder.create().uv(52, 11).cuboid(0.0387F, -1.0702F, -1.8551F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0849F, -0.0085F, -2.2357F, -1.5482F, -1.135F, 1.0082F));

		ModelPartData cube_r41 = left_arm.addChild("cube_r41", ModelPartBuilder.create().uv(48, 56).cuboid(2.0F, -1.0F, 1.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0041F, 2.4284F, -0.6307F, -0.4358F, -0.0221F, -0.5274F));

		ModelPartData cube_r42 = left_arm.addChild("cube_r42", ModelPartBuilder.create().uv(20, 56).cuboid(2.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.1041F, 2.0284F, -2.3307F, -0.4358F, -0.0221F, -0.5274F));

		ModelPartData cube_r43 = left_arm.addChild("cube_r43", ModelPartBuilder.create().uv(56, 16).cuboid(2.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.0041F, 0.1284F, -2.2307F, -0.4358F, -0.0221F, -0.5274F));

		ModelPartData cube_r44 = left_arm.addChild("cube_r44", ModelPartBuilder.create().uv(56, 52).cuboid(2.0F, -1.0F, 1.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.1959F, 0.5284F, -0.5307F, -0.4358F, -0.0221F, -0.5274F));

		ModelPartData cube_r45 = left_arm.addChild("cube_r45", ModelPartBuilder.create().uv(32, 22).cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.8041F, -3.0716F, 1.5693F, -0.4358F, -0.0221F, -0.5274F));

		ModelPartData right_arm = body.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-10.6041F, -19.4284F, -4.0693F));

		ModelPartData cube_r46 = right_arm.addChild("cube_r46", ModelPartBuilder.create().uv(56, 11).cuboid(-2.9614F, -1.4298F, -0.1449F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0151F, -0.1085F, -1.4357F, -1.5482F, 1.135F, -1.0082F));

		ModelPartData cube_r47 = right_arm.addChild("cube_r47", ModelPartBuilder.create().uv(56, 33).cuboid(-3.0387F, -1.0702F, -1.8551F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.0849F, 0.3915F, -1.5357F, -1.5482F, 1.135F, -1.0082F));

		ModelPartData cube_r48 = right_arm.addChild("cube_r48", ModelPartBuilder.create().uv(56, 44).cuboid(0.0386F, -1.4298F, 0.8551F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.3151F, -1.0085F, -1.6357F, -1.5482F, 1.135F, -1.0082F));

		ModelPartData cube_r49 = right_arm.addChild("cube_r49", ModelPartBuilder.create().uv(12, 56).cuboid(-0.0387F, -1.0702F, -1.8551F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.0849F, -0.0085F, -2.2357F, -1.5482F, 1.135F, -1.0082F));

		ModelPartData cube_r50 = right_arm.addChild("cube_r50", ModelPartBuilder.create().uv(56, 48).cuboid(-2.0F, -1.0F, 1.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0041F, 2.4284F, -0.6307F, -0.4358F, 0.0221F, 0.5274F));

		ModelPartData cube_r51 = right_arm.addChild("cube_r51", ModelPartBuilder.create().uv(24, 56).cuboid(-2.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.1041F, 2.0284F, -2.3307F, -0.4358F, 0.0221F, 0.5274F));

		ModelPartData cube_r52 = right_arm.addChild("cube_r52", ModelPartBuilder.create().uv(56, 20).cuboid(-2.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0041F, 0.1284F, -2.2307F, -0.4358F, 0.0221F, 0.5274F));

		ModelPartData cube_r53 = right_arm.addChild("cube_r53", ModelPartBuilder.create().uv(54, 56).cuboid(-2.0F, -1.0F, 1.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.1959F, 0.5284F, -0.5307F, -0.4358F, 0.0221F, 0.5274F));

		ModelPartData cube_r54 = right_arm.addChild("cube_r54", ModelPartBuilder.create().uv(32, 38).cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.8041F, -3.0716F, 1.5693F, -0.4358F, 0.0221F, 0.5274F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(LostSpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(ModAnimations.LOST_SPIRIT_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.LOST_SPIRIT_IDLE, ageInTicks, 1f);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		lost_spirit.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return lost_spirit;
	}
}