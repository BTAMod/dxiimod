package dxii.dxiimod.mixin;

import net.minecraft.client.render.model.Cube;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = Cube.class, remap = false)
public class RenderModelsMixin {

	@Shadow
	private boolean compiled;


	@Inject(method = "addBox(FFFIIIF)V", at = @At(value = "HEAD") )
	public void hotswapMixin(float minX, float minY, float minZ, int sizeX, int sizeY, int sizeZ, float expandAmount, CallbackInfo ci){
		this.compiled = false;
	}
}
