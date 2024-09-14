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

	@Inject(method = "render(F)V", at = @At(value = "INVOKE", target = "net/minecraft/client/render/model/Cube.compileDisplayList (F)V"))
	public void hotswapMixin(float scale, CallbackInfo ci){
		this.compiled = false;
	}
}
