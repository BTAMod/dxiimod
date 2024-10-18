package dxii.dxiimod.render.enemy;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBiped;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class ModelFogLurker2 extends ModelBiped {

	public Cube head;
	public Cube body;
	public Cube armR;
	public Cube armL;

	protected static Random itemRand = new Random();

	public ModelFogLurker2(){
		this(0.0f);
	}

	public void partz(float extAmt){
		this.head = new Cube(36,35, 128, 64);
		this.head.addBox(-3, -10,-3, 6, 10, 5, extAmt);
		this.head.setRotationPoint(0, -10,0 );


		this.body = new Cube(0,0, 128, 64);
		this.body.addBox(-4,-10,-1, 8, 23, 4, extAmt);

		this.armR = new Cube(24,0, 128, 64);
		this.armR.addBox(-3,-1,-2, 3, 36, 3, extAmt);
		this.armR.setRotationPoint(-4, -6, 2);
		this.armR.mirror = true;

		this.armL = new Cube(36,0, 128, 64);
		this.armL.addBox(0,-1,-2, 2, 33, 2, extAmt);
		this.armL.setRotationPoint(4, -8, 2);


		this.bipedRightLeg = new Cube(65,1, 128, 64);
		this.bipedRightLeg.addBox(-1,0,-1, 3, 36, 3, extAmt);
		this.bipedRightLeg.setRotationPoint(2, -10, 1);

		this.bipedLeftLeg = new Cube(53,1, 128, 64);
		this.bipedLeftLeg.addBox(-1,0,-1, 3, 36, 3, extAmt);
		this.bipedLeftLeg.setRotationPoint(-3, -10, 1);
	}

	public ModelFogLurker2(float extAmt) {
		this.partz(extAmt);
	}

	public void render(float limbSwing, float limbYaw, float ticksExisted, float headYaw, float headPitch, float scale) {
		GL11.glTranslatef(0f, -1.5f, 0f);
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
		this.partz(0);
		super.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
		this.head.rotateAngleY = headYaw / 57.29578f;
		this.head.rotateAngleX = .5f + headPitch / 57.29578f + itemRand.nextFloat() * 0.1F;
		this.head.rotateAngleZ = .4f;
		this.armR.rotateAngleX = -.4f + itemRand.nextFloat() * 0.1F;
		this.armR.rotateAngleY = itemRand.nextFloat() * 0.1F;
		this.armR.rotateAngleZ = .1f + itemRand.nextFloat() * -0.1F + itemRand.nextFloat() * 0.1F;
		this.armL.rotateAngleX = -.3f + itemRand.nextFloat() * -0.1F + itemRand.nextFloat() * 0.1F;
		this.armL.rotateAngleY = itemRand.nextFloat() * 0.1F + itemRand.nextFloat() * -0.1F;
		this.armL.rotateAngleZ = -.1f + itemRand.nextFloat() * 0.1F;
	}

}
















