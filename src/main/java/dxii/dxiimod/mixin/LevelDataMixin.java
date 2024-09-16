package dxii.dxiimod.mixin;

import com.mojang.nbt.CompoundTag;
import dxii.dxiimod.interfaces.IWorldVariables;
import net.minecraft.core.world.save.LevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = LevelData.class, remap = false)
public class LevelDataMixin implements IWorldVariables {


	@Unique
	public boolean isFog = false;

	@Unique
	public int fogDay = 0;

	@Override
	public boolean dxiimod$getFog(){
		return this.isFog;
	}

	@Override
	public void dxiimod$setFog(boolean Fog){
		this.isFog = Fog;
	}

	@Override
	public int dxiimod$getFogDay(){
		return this.fogDay;
	}

	@Override
	public void dxiimod$setFogDay(int day){
		this.fogDay = day;
	}


	@Inject(
		method = "updateTagCompound(Lcom/mojang/nbt/CompoundTag;Lcom/mojang/nbt/CompoundTag;)V",
		at = @At(value = "INVOKE", target = "com/mojang/nbt/CompoundTag.putLong (Ljava/lang/String;J)V")
	)
	public void updateTagMixin(CompoundTag levelTag, CompoundTag playerTag, CallbackInfo ci){
		levelTag.putBoolean("FogEnabled", this.isFog);
		levelTag.putInt("FogDay", this.fogDay);
	}

	@Inject(
		method = "readFromCompoundTag(Lcom/mojang/nbt/CompoundTag;)V",
		at = @At(value = "INVOKE", target = "com/mojang/nbt/CompoundTag.getLong (Ljava/lang/String;)J")
	)
	public void readTagMixin(CompoundTag tag, CallbackInfo ci){
		this.isFog = tag.getBoolean("FogEnabled");
		this.fogDay = tag.getInteger("FogDay");
	}


}
