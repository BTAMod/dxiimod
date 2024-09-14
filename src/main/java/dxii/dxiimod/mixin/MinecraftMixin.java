package dxii.dxiimod.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;


@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin {

	@ModifyArgs(
		method = "clickMouse(IZZ)V",
		at = @At(value = "INVOKE",
		target = "net/minecraft/client/player/controller/PlayerController.swingItem (Z)Z",
		ordinal = 1)
	)
	private void swingMixin(Args args){
		args.set(0, false);
	}

	@ModifyArgs(
		method = "clickMouse(IZZ)V",
		at = @At(value = "INVOKE",
			target = "net/minecraft/client/player/controller/PlayerController.swingItem (Z)Z",
			ordinal = 2)
	)
	private void swingMixin2(Args args){
		args.set(0, false);
	}

}
