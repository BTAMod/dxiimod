package dxii.dxiimod.render.enemy;

import dxii.dxiimod.entity.enemy.EnemyFogLurker;
import net.minecraft.client.render.entity.LivingRenderer;
import org.lwjgl.opengl.GL11;
public class RenderFogLurker1 extends LivingRenderer<EnemyFogLurker> {

	public RenderFogLurker1() {
		super(new ModelFogLurker1(), 1);
	}

	@Override
	protected void preRenderCallback(EnemyFogLurker entity, float f){
		float scale = 1;
		GL11.glScalef(scale, scale, scale);
	}

}
