package dxii.dxiimod.mixin;


import net.minecraft.client.Minecraft;
import net.minecraft.client.option.enums.RenderDistance;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WorldRenderer.class, remap = false)
public class FogMixin {
	@Redirect(
		method ="setupCameraTransform(F)V",
		at = @At(value = "FIELD", target = "net/minecraft/client/option/enums/RenderDistance.chunks : I")
	)
	private int mixinVar(RenderDistance instance){
		if(Minecraft.getMinecraft(Minecraft.class).theWorld != null) {
			if(Minecraft.getMinecraft(Minecraft.class).theWorld.getLevelData().getWorldTime() / 24000f > 3.85){
				return 1;
			}
			else if(Minecraft.getMinecraft(Minecraft.class).theWorld.getLevelData().getWorldTime() / 24000f > 3.7){
				return 4;
			}
			else if(Minecraft.getMinecraft(Minecraft.class).theWorld.getLevelData().getWorldTime() / 24000f > 3.5){
				return 8;
			}
			else if(Minecraft.getMinecraft(Minecraft.class).theWorld.getLevelData().getWorldTime() / 24000f > 3.2){
				return 16;
			}
			else {
				return 24;
			}
		}
		else{
			return 24;
		}
	}

}
