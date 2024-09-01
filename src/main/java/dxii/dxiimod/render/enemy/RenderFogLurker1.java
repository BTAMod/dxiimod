package dxii.dxiimod.render.enemy;

import dxii.dxiimod.entity.enemy.EnemyFogLurker;
import net.minecraft.client.render.entity.LivingRenderer;
//import net.minecraft.client.render.model.ModelBase;
//import net.minecraft.client.render.tessellator.Tessellator;
//import org.lwjgl.opengl.GL11;

public class RenderFogLurker1 extends LivingRenderer<EnemyFogLurker> {

	public RenderFogLurker1() {
		super(new ModelFogLurker1(), .35f);
	}

}
