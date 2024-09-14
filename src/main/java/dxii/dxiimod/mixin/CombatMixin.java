package dxii.dxiimod.mixin;

import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.controller.PlayerController;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.player.gamemode.Gamemode;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import javax.security.auth.callback.Callback;


@Mixin(value = PlayerController.class, remap = false)
public class CombatMixin {

	@Final
	@Shadow
	protected Minecraft mc;

	@Final
	@Shadow
	protected int swingCooldown = 0;

	@Unique
	protected int attackCooldown = 0;

	/**
	 * @author me
	 * @reason idk
	 */
	@Overwrite
	public float getBlockReachDistance(){
		if(this.mc.thePlayer.getHeldItem() != null){
			Item currentItem = this.mc.thePlayer.getHeldItem().getItem();
			if(this.mc.thePlayer.getGamemode() == Gamemode.creative){
				return 4.5f;
			}else {
				return (((INewItemVars) currentItem).dxiimod$getItemRange());
			}
		}else{
			return 3f;
		}

		//range * 0.875 = blocks range
	}

	/**
	 * @author me
	 * @reason idk (added these just to make it stop annoying me with piss yellow things, cyka)
	 */
	@Overwrite
	public float getEntityReachDistance(){
		if(this.mc.thePlayer.getHeldItem() != null){
			Item currentItem = this.mc.thePlayer.getHeldItem().getItem();
			return ((INewItemVars) currentItem).dxiimod$getItemRange();
		}else {
			return 4f;
		}
	}

	/**
	 * @author me
	 * @reason idk
	 */
	@Overwrite
	public void attack(EntityPlayer entityplayer, Entity entity) {
		if(this.mc.thePlayer.getHeldItem() != null) {
			Item currentItem = this.mc.thePlayer.getHeldItem().getItem();

			if (this.mc.thePlayer.getGamemode().canInteract() & this.attackCooldown <= 5) {
				entityplayer.attackTargetEntityWithCurrentItem(entity);
				if (this.mc.thePlayer.getHeldItem() != null) {
					this.swingCooldown = ((INewItemVars) currentItem).dxiimod$getItemCooldown();
					this.attackCooldown = ((INewItemVars) currentItem).dxiimod$getItemCooldown();
				}

				if( ((INewItemVars) currentItem).dxiimod$itemSwingable() ){
					this.mc.thePlayer.swingItem();
				}
			}
		}else if (this.mc.thePlayer.getGamemode().canInteract()) {
			entityplayer.attackTargetEntityWithCurrentItem(entity);
			this.mc.thePlayer.swingItem();
		}
	}


	@Inject(
		method = "tick()V",
		at = @At(value = "INVOKE", target = "net/minecraft/client/player/controller/PlayerController.syncCurrentPlayItem ()V")
	)
	private void attackDelayMixin(CallbackInfo ci){
		if (this.attackCooldown > 0) {
			--this.attackCooldown;
		}
	}

}
