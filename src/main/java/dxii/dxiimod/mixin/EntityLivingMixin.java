package dxii.dxiimod.mixin;



import dxii.dxiimod.interfaces.ILivingEntityFunctions;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.core.util.helper.MathHelper.clamp;


@Mixin(value = EntityLiving.class, remap = false)
public class EntityLivingMixin implements ILivingEntityFunctions {

	@Shadow
	protected float moveSpeed;

	@Unique
	protected float oldMoveSpeed;

	@Unique
	protected EntityLiving thisObject = (EntityLiving)(Object)this ;


	@Unique
	private int parryTicks;

	@Unique
	private int parriedTicks = 0;


	@Override
	public void dxiimod$Parry(int parryTicks){
		this.parryTicks = parryTicks;
	}

	@Override
	public void dxiimod$parryStun(Entity attacker, int damage){
		if(thisObject instanceof EntityPlayer) {
			this.parriedTicks = 7;
		}else{
			this.parriedTicks = 15;
		}

		double d = clamp(attacker.x - thisObject.x, -1, 1);
		double d1 = clamp(attacker.z - thisObject.z, -1, 1);
		attacker.push(d*4 , .3, d1*4);

		if(damage  >= 5){
			attacker.world.playSoundEffect(
				attacker,
				SoundCategory.ENTITY_SOUNDS,
				attacker.x,
				attacker.y,
				attacker.z,
				"dxiimod.ds2_parry",
				1f,
				1f);
		}else{
			attacker.world.playSoundEffect(
				attacker,
				SoundCategory.ENTITY_SOUNDS,
				attacker.x,
				attacker.y,
				attacker.z,
				"dxiimod.parry",
				1f,
				1f);
		}

	}
	@Override
	public int dxiimod$getParryTicks(){
		return parryTicks;
	}
	@Override
	public int dxiimod$getParriedTicks(){
		return parriedTicks;
	}

	@Inject(
		method = "hurt(Lnet/minecraft/core/entity/Entity;ILnet/minecraft/core/util/helper/DamageType;)Z",
		at = @At(value = "HEAD"),
		cancellable = true)
	public void parryStunCallback(Entity attacker, int damage, DamageType type, CallbackInfoReturnable<Boolean> cir){
		System.out.println(damage);

		if(type == DamageType.COMBAT && this.parryTicks != 0 && thisObject.distanceTo(attacker) <= 3){
			((ILivingEntityFunctions)(attacker)).dxiimod$parryStun(attacker, damage);
			cir.setReturnValue(false);
		}

		if(this.parriedTicks > 0){
			attacker.world.playSoundEffect(attacker, SoundCategory.ENTITY_SOUNDS, attacker.x, attacker.y, attacker.z, "dxiimod.riposte", .1f, 1f);
		}
	}

	@ModifyArg(
		method ="hurt(Lnet/minecraft/core/entity/Entity;ILnet/minecraft/core/util/helper/DamageType;)Z",
		at = @At(value = "INVOKE", target = "net/minecraft/core/entity/EntityLiving.damageEntity (ILnet/minecraft/core/util/helper/DamageType;)V", ordinal = 1),
		index = 0
	)
	private int parriedDamageMixin(int i){
		if(this.parriedTicks > 0){
			return i * 2;
		}
		return i;
	}


	@Inject(
		method = "tick()V",
		at = @At(value = "INVOKE", target = "net/minecraft/core/entity/EntityLiving.onLivingUpdate ()V")
	)
	public void parrySub(CallbackInfo ci){
		if(this.oldMoveSpeed == 0){
			this.oldMoveSpeed = this.moveSpeed;
		}
		if(this.parryTicks != 0) {
			this.parryTicks--;
		}
		if(this.parriedTicks != 0){
			this.parriedTicks--;
		}
		if(this.parriedTicks < 0){
			this.parriedTicks = 0;
		}


		if(parriedTicks != 0){
			this.moveSpeed = oldMoveSpeed * .33f;
		}else{
			this.moveSpeed = oldMoveSpeed;
		}
	}

}
