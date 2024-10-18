package dxii.dxiimod.mixin;

import dxii.dxiimod.interfaces.INewItemVars;
import dxii.dxiimod.interfaces.IPlayerControllerStuff;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.controller.PlayerController;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = PlayerController.class, remap = false)
public class CombatMixinOld implements IPlayerControllerStuff {



	@Final
	@Shadow
	protected Minecraft mc;


	@Shadow
	protected int swingCooldown = 0;

	@Unique
	protected int attackCooldown = 0;

	@Unique
	protected int usageCooldown = 0;


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
				return (float)(((INewItemVars) currentItem).dxiimod$getItemRange());
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
			return (float)((INewItemVars) currentItem).dxiimod$getItemRange();
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

			if (this.mc.thePlayer.getGamemode().canInteract() & this.attackCooldown == 0) {
				entityplayer.attackTargetEntityWithCurrentItem(entity);
				if (this.mc.thePlayer.getHeldItem() != null) {
					this.swingCooldown = ((INewItemVars) currentItem).dxiimod$getItemCooldown();
					this.attackCooldown = ((INewItemVars) currentItem).dxiimod$getItemCooldown();
				}
			}
		}else if (this.mc.thePlayer.getGamemode().canInteract()) {
			entityplayer.attackTargetEntityWithCurrentItem(entity);
			this.mc.thePlayer.swingItem();
		}
	}

	/**
	 * @author can you
	 * @reason stop please
	 */
	@Overwrite
	public boolean useItem(EntityPlayer player, World world, ItemStack stack) {
		if (!player.getGamemode().canInteract() | (this.usageCooldown != 0 & ((INewItemVars) stack.getItem()).dxiimod$getItemUsageCooldown() > 0) ) {
			return false;
		}
		int prevStackSize = stack.stackSize;
		ItemStack newItemStack = stack.useItemRightClick(world, player);
		if(((INewItemVars) stack.getItem()).dxiimod$getItemUsageCooldown() > 0){
			this.swingCooldown = ((INewItemVars) stack.getItem()).dxiimod$getItemUsageCooldown();
			this.usageCooldown = ((INewItemVars) stack.getItem()).dxiimod$getItemUsageCooldown();
		}
		if (newItemStack != stack || newItemStack.stackSize != prevStackSize) {
			player.inventory.mainInventory[player.inventory.currentItem] = newItemStack;
			if (newItemStack == null || newItemStack.stackSize <= 0) {
				player.inventory.mainInventory[player.inventory.currentItem] = null;
			}
			return true;
		}
		return false;
	}

	/**
	 * @author can you
	 * @reason stop please
	 */
	@Overwrite
	public boolean swingItem(boolean force) {
		if (this.swingCooldown == 0 || force) {
			if(this.attackCooldown == 0) {
				this.mc.thePlayer.swingItem();
				this.swingCooldown = 5;
				return true;
			}
		}
		return false;
	}



	@Inject(
		method = "startDestroyBlock(IIILnet/minecraft/core/util/helper/Side;DDZ)V",
		at = @At(value = "INVOKE", target = "net/minecraft/core/player/gamemode/Gamemode.canInteract ()Z")
	)
	private void blockBreakMixin(CallbackInfo ci){
		if (this.attackCooldown != 0) {
			return;
		}
	}

	@Redirect(
		method ="continueDestroyBlock(IIILnet/minecraft/core/util/helper/Side;DD)V",
		at = @At(value = "INVOKE", target = "net/minecraft/core/player/gamemode/Gamemode.canInteract ()Z")
	)
	private boolean mineBLock(Gamemode instance){
		return this.mc.thePlayer.getGamemode().canInteract() && this.attackCooldown == 0;
	}

	@Inject(
		method = "tick()V",
		at = @At(value = "INVOKE", target = "net/minecraft/client/player/controller/PlayerController.syncCurrentPlayItem ()V")
	)
	private void attackDelayMixin(CallbackInfo ci){
		if (this.attackCooldown > 0) {
			--this.attackCooldown;
		}

		if (this.usageCooldown > 0) {
			--this.usageCooldown;
		}


	}

	@Override
	public int dxiimod$getAttackDelay(){
		return this.swingCooldown;
	}

	@Override
	public void dxiimod$setAttackDelay(int ticks){
		this.attackCooldown += ticks;
	}




}
