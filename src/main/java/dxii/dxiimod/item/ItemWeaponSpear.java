package dxii.dxiimod.item;

import dxii.dxiimod.dxiimodUtils;
import dxii.dxiimod.interfaces.INewItemFunctions;
import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.core.HitResult;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;


public class ItemWeaponSpear extends Item implements INewItemFunctions {

	public float range;
	public int damage = 4;


	public ItemWeaponSpear(String name, int id, float range, int dmg, int cooldown, int durability) {
		super(name, id);
		this.range = range-1;
		this.damage = dmg;
		((INewItemVars)this).dxiimod$setItemRange(range);
		((INewItemVars)this).dxiimod$setItemCooldown(cooldown);
		((INewItemVars)this).dxiimod$setItemSpeedPenalty(.012);
		((INewItemVars)this).dxiimod$setDoesBreakBlocks(false);
		this.setMaxDamage(durability);
	}

	@Override
	public boolean onBlockDestroyed(World world, ItemStack itemstack, int i, int j, int k, int l, Side side, EntityLiving entityliving) {
		if( ((EntityPlayer)entityliving).gamemode != Gamemode.creative ) {
			itemstack.damageItem(4, entityliving);
		}
		return true;
	}

	@Override
	public boolean dxiimod$onItemAttack(EntityPlayer player, ItemStack itemstack, boolean flag){
		if(flag) {
			dxiimodUtils.meleeAABBAttack(itemstack, player, this.damage, .2, 0, .5, .5,"", .1f, 1, 1, false);
			dxiimodUtils.meleeAABBAttack(itemstack, player, this.damage, .2, 2*(this.range/4), .5, .5,"", .1f, 1, 1, true);
			dxiimodUtils.meleeAABBAttack(itemstack, player, this.damage, .2, 3*(this.range/4), .5, .5,"", .1f, 1, 1, true);
			dxiimodUtils.meleeAABBAttack(itemstack, player, this.damage, .2, this.range, .5, .5,"", .1f, 1, 1, true);

			HitResult eyeTrace = dxiimodUtils.quickEyeRayCast(player);
			if(eyeTrace != null && eyeTrace.hitType == HitResult.HitType.TILE && dxiimodUtils.isHitBlockSolid(player, eyeTrace)){
				player.world.spawnParticle("smoke", eyeTrace.location.xCoord, eyeTrace.location.yCoord, eyeTrace.location.zCoord, 0.0, 0.0, 0.0, 0);
				player.world.playSoundAtEntity(player, player, "dxiimod.stab", 0.03F, 1F);
			}
		}else{
			player.swingItem();
			player.world.playSoundAtEntity(player, player, "dxiimod.spear", 0.1F, 1F);
		}

		return false;
	}

	@Override
	public boolean dxiimod$onItemAltAttackTimed(EntityPlayer player, ItemStack itemstack, boolean timed){
		return false;
	}

	@Override
	public boolean beforeDestroyBlock(World world, ItemStack itemStack, int blockId, int x, int y, int z, Side side, EntityPlayer player) {
		return player.getGamemode() != Gamemode.creative;
	}

}
