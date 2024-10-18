
package dxii.dxiimod.render;

import dxii.dxiimod.entity.EntityFlameParticle;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import org.lwjgl.opengl.GL11;

public class RenderFlameParticle extends EntityRenderer<EntityFlameParticle> {

	public RenderFlameParticle(){ this.shadowSize = 0.1f; }

	@Override
	public void doRender(Tessellator tessellator, EntityFlameParticle entity, double x, double y, double z, float f, float partialTick) {
		float scale = .2f;
		this.loadTexture("/assets/dxiimod/textures/entity/bullet.png");
		GL11.glColor4f(2, 2, 2, 255);
		GL11.glBlendFunc(GL11.GL_ADD, GL11.GL_ADD);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(scale, scale, scale);

		GL11.glNormal3f(scale, 0, 0);
		for (int i = 0; i < 4; ++i) {
			GL11.glRotatef(90f, 0f, 1, 0);
			GL11.glNormal3f(0, 0, scale);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(0, .4, .4, 1, 0);//1
			tessellator.addVertexWithUV(0, -.4, .4, 0, 0);//2
			tessellator.addVertexWithUV(0, -.4, -.4, 0, 1);//3
			tessellator.addVertexWithUV(0, .4, -.4, 1, 1);//4
			tessellator.draw();
		}

		GL11.glRotatef(90f, 0, 0, 1);

		for (int i = 0; i < 2; ++i) {
			GL11.glRotatef(180f, 0f, 1, 0);
			GL11.glNormal3f(0, 0, scale);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(0, .4, .4, 1, 0);//1
			tessellator.addVertexWithUV(0, -.4, .4, 0, 0);//2
			tessellator.addVertexWithUV(0, -.4, -.4, 0, 1);//3
			tessellator.addVertexWithUV(0, .4, -.4, 1, 1);//4
			tessellator.draw();
		}
		GL11.glPopMatrix();
	}

}
