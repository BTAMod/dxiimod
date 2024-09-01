package dxii.dxiimod.render.enemy;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBiped;

public class ModelFogLurker1 extends ModelBiped {

	public Cube head;
	public Cube body;

	public ModelFogLurker1(){
		this(0.0f);
	}

	public ModelFogLurker1(float extAmt) {
		this.head = new Cube(0,0);
		this.head.addBox(-4,-12,-4, 8, 12, 8, extAmt);
		this.head.setRotationPoint(0, 0, 0);
		this.head.setRotationAngle(0, 0, 0);
		this.body = new Cube(32,0);
		this.body.addBox(-4,0,-4, 8, 12, 8, extAmt);

	}

	public void render(float limbSwing, float limbYaw, float ticksExisted, float headYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbYaw, ticksExisted, headYaw, headPitch, scale);
		this.head.render(scale);
		this.body.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		super.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
	}

}
