package dxii.dxiimod.render.enemy;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBiped;

import java.util.Random;

public class ModelFogLurker1 extends ModelBiped {

	public Cube head;
	public Cube body;
	public Cube armR;
	public Cube armL;

	protected static Random itemRand = new Random();

	public ModelFogLurker1(){
		this(0.0f);
	}

	public void partz(float extAmt){
		this.head = new Cube(0,16);
		this.head.addBox(-5, -10,-5, 10, 10, 5, extAmt);
		this.head.setRotationPoint(0, 0,0 );


		this.body = new Cube(0,0);
		this.body.addBox(-5,0,-3, 12, 10, 6, extAmt);

		this.armL = new Cube(50,0);
		this.armL.addBox(-3,-1,-2, 3, 15, 4, extAmt);
		this.armL.setRotationPoint(-5, 2, 0);
		this.armL.mirror = true;

		this.armR = new Cube(50,0);
		this.armR.addBox(0,-1,-2, 3, 15, 4, extAmt);
		this.armR.setRotationPoint(7, 2, 0);


		this.bipedRightLeg = new Cube(35,16);
		this.bipedRightLeg.addBox(-2,-2,-2, 4, 14, 5, extAmt);
		this.bipedRightLeg.setRotationPoint(5, 10, 0);

		this.bipedLeftLeg = new Cube(35,16);
		this.bipedLeftLeg.addBox(-2,-2,-2, 4, 14, 5, extAmt);
		this.bipedLeftLeg.setRotationPoint(-3, 10, 0);
	}

	public ModelFogLurker1(float extAmt) {
		this.partz(extAmt);
	}

	public void render(float limbSwing, float limbYaw, float ticksExisted, float headYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbYaw, ticksExisted, headYaw, headPitch, scale);
		this.head.render(scale);
		this.body.render(scale);
		this.armL.render(scale);
		this.armR.render(scale);
		this.bipedRightLeg.render(scale);
		this.bipedLeftLeg.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		super.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
		this.head.rotateAngleY = headYaw / 57.29578f;
		this.head.rotateAngleX = .5f + headPitch / 57.29578f + itemRand.nextFloat() * 0.1F;
		this.head.rotateAngleZ = .4f;

		this.armR.rotateAngleX = -.9f + itemRand.nextFloat() * 0.1F;
		this.armR.rotateAngleZ = itemRand.nextFloat() * 0.1F;
		this.armL.rotateAngleZ = itemRand.nextFloat() * 0.1F;
		this.armL.rotateAngleY = -.3f + itemRand.nextFloat() * 0.1F;
		this.armL.rotateAngleX = -.3f + itemRand.nextFloat() * 0.1F;

	}

}
