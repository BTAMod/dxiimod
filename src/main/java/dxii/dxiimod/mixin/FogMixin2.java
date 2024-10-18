package dxii.dxiimod.mixin;


import dxii.dxiimod.interfaces.IWorldVariables;
import net.minecraft.client.GLAllocation;
import net.minecraft.client.render.FogManager;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.nio.FloatBuffer;


//here im experimenting with fog, currently unused
@Mixin(value = FogManager.class, remap = false)
public class FogMixin2 {

	@Unique
	protected FogManager thisObject = (FogManager)(Object)this;

	@Shadow
	public float fogRed;

	@Shadow
	public float fogGreen;

	@Shadow
	public float fogBlue;

	@Shadow
	private final FloatBuffer fogColorBuffer = GLAllocation.createDirectFloatBuffer(16);

	@Unique
	private float getFogDist(World world){
		double fogDay = ((IWorldVariables)(world.getLevelData() )).dxiimod$getFogDay();

//		if (world.getLevelData().getWorldTime() / 24000f > (fogDay + 1.35)) {
//				return MathHelper.clamp(dxiimodMain.FogDist.value, 1, 6);
//			} else if (Minecraft.getMinecraft(Minecraft.class).theWorld.getLevelData().getWorldTime() / 24000f > (fogDay + 1.2)) {
//				return MathHelper.clamp(dxiimodMain.FogDist.value, 4, 6);
//			} else if (Minecraft.getMinecraft(Minecraft.class).theWorld.getLevelData().getWorldTime() / 24000f > (fogDay + 1)) {
//				return 8;
//			} else if (Minecraft.getMinecraft(Minecraft.class).theWorld.getLevelData().getWorldTime() / 24000f > (fogDay + .7)) {
//				return 16;
//			} else {
//				return 24;
//			}

		return 2;
	}


	/*
	here im experimenting with fog param constants, which are defined as numbers (ffs)

	2914 - fog density
	2915 - fog start
	2916 - fog end
	2917 - fog mode
	 */

	@ModifyArg(
		method ="setupFog(IFF)V",
		at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glFogf (IF)V", ordinal = 4), index = 1
	)
	private float theFogIsComing(float param){
		return 0.1f;
	}

	@ModifyArg(
		method ="setupFog(IFF)V",
		at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glFogf (IF)V", ordinal = 2), index = 1
	)

	//fog end and fog start work ONLY if FOG_MODE is GL_LINEAR, EXP/EXP2 modes utilize fog density
	private float theFogIsComing2(float param){
		//2915 - fog start, default - 32
		return .1f;
	}

	@ModifyArg(
		method ="setupFog(IFF)V",
		at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glFogf (IF)V", ordinal = 3), index = 1
	)
	private float theFogIsComing3(float param){
		//2916 - fog end, default - 128
		return 32;
	}


	@ModifyArg(
		method ="setupFog(IFF)V",
		at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glFogi (II)V", ordinal = 2), index = 1
	)
	private int theFogIsComing4(int pname){
		//2917 - fog mode, default - 9729 (GL_LINEAR)
//		return 0x801;
		return 0x801;
	}

	@ModifyArg(
		method ="setupFog(IFF)V",
		at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glFog (ILjava/nio/FloatBuffer;)V"), index = 1
	)
	private FloatBuffer theFogIsComing5(FloatBuffer params){
		return this.buffer(this.fogRed, this.fogGreen, this.fogBlue, 0.01f);
	}

	/**
	 */
	@Overwrite
	private FloatBuffer buffer(float r, float g, float b, float a) {
		this.fogColorBuffer.clear();
		this.fogColorBuffer.put(r).put(g).put(b).put(1.1f);
		this.fogColorBuffer.flip();
		return this.fogColorBuffer;
	}



}
