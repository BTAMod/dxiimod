package dxii.dxiimod.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dxii.dxiimod.dxiimodMain;

import java.io.File;

@Mixin(value = GameSettings.class, remap = false)
public class GameOptionsMixin {

	@Unique
	public RangeOption FogDist;


	@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/b100/utils/ReflectUtils;getAllObjects(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Object;)[Ljava/lang/Object;"))
	public void addOptions(Minecraft minecraft, File file, CallbackInfo ci){
		dxiimodMain.optionsInit((GameSettings) (Object)this);

		this.FogDist = dxiimodMain.FogDist;
	}


}
