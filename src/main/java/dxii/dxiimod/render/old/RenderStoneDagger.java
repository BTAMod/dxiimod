package dxii.dxiimod.render.old;

import dxii.dxiimod.entity.EntityDagger;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import org.lwjgl.opengl.GL11;

public class RenderStoneDagger extends EntityRenderer<EntityDagger> {

	public RenderStoneDagger(){ this.shadowSize = 0.1f; }

	@Override
	public void doRender(Tessellator tessellator, EntityDagger entity, double x, double y, double z, float f, float f1) {
		float scale = .2f;
		int rot = entity.customRot;
		this.loadTexture("/assets/dxiimod/textures/entity/stonedagger.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(entity.yRot, 0f, 1f, 0f);
		GL11.glRotatef(90, 1f, 0f, 0f);

		GL11.glRotatef((float)entity.xd, 1f, 0f, 0f);
		GL11.glRotatef((float)entity.yd, 0f, 1f, 0f);
		GL11.glRotatef((float)entity.zd, 0f, 0f, 1f);

		GL11.glRotatef((float)rot, 1f, 0f, 0f);


		GL11.glNormal3f(scale, 0, 0);
		for (int i = 0; i < 4; ++i) {
			GL11.glRotatef(90f, 0f, 1, 0);
			GL11.glNormal3f(0, 0, scale);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(0, 1, 1, 1, 0);//1
			tessellator.addVertexWithUV(0, -1, 1, 0, 0);//2
			tessellator.addVertexWithUV(0, -1, -1, 0, 1);//3
			tessellator.addVertexWithUV(0, 1, -1, 1, 1);//4
			tessellator.draw();
		}

		GL11.glPopMatrix();
	}

}
