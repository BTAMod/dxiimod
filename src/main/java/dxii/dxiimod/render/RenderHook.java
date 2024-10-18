package dxii.dxiimod.render;

import dxii.dxiimod.dxiimodUtils;
import dxii.dxiimod.entity.EntityHook;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import org.lwjgl.opengl.GL11;

public class RenderHook extends EntityRenderer<EntityHook> {

	public RenderHook(){ this.shadowSize = 0.1f; }

	@Override
	public void doRender(Tessellator tessellator, EntityHook hook, double x, double y, double z, float f, float partialTick) {
		float scale = 1;
		this.loadTexture("/assets/dxiimod/textures/entity/hook.png");

		GL11.glPushMatrix();

		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glBlendFunc(GL11.GL_ADD, GL11.GL_ADD);
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glRotatef(hook.yRotO + (hook.yRot - hook.yRotO) * partialTick - 90.0f, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(hook.xRotO + (hook.xRot - hook.xRotO) * partialTick, 0.0f, 0.0f, 1.0f);
		GL11.glScalef(scale, scale, scale);


		float aaah = .33f;
		GL11.glRotatef(90f, 0, 1, 0);
		GL11.glNormal3f(scale, 0, 0);
		GL11.glTranslatef(0, 0, aaah);
		for (int i = 0; i < 4; ++i) {
			GL11.glRotatef(90f, 0, 0, 1);
			GL11.glNormal3f(0, 0, scale);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(0, .4, .4, 1, 0);//1
			tessellator.addVertexWithUV(0, -.4, .4, 0, 0);//2
			tessellator.addVertexWithUV(0, -.4, -.4, 0, 1);//3
			tessellator.addVertexWithUV(0, .4, -.4, 1, 1);//4
			tessellator.draw();
		}
		GL11.glTranslatef(0, 0, -aaah);
		GL11.glRotatef(90f, 0, -1, 0);
		GL11.glRotatef(hook.xRotO + (hook.xRot - hook.xRotO) * partialTick, 0.0f, 0.0f, -1.0f);
		GL11.glRotatef(hook.yRotO + (hook.yRot - hook.yRotO) * partialTick - 90.0f, 0.0f, -1.0f, 0.0f);

		dxiimodUtils.draw3dLine(.1, 0, 0, 0, (hook.owner.x - hook.x) - .1, (hook.owner.y - hook.y) - .5, (hook.owner.z - hook.z) - .1, .39f,.3f, .14f);

		GL11.glPopMatrix();
	}

}
