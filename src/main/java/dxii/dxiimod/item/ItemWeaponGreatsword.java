package dxii.dxiimod.item;

import dxii.dxiimod.dxiimodUtils;
import dxii.dxiimod.interfaces.INewItemFunctions;
import dxii.dxiimod.interfaces.INewItemVars;
import dxii.dxiimod.interfaces.IPlayerStuff;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;

public class ItemWeaponGreatsword extends Item implements INewItemFunctions{

	public float range;
	public int damage = 4;
	public double knockback;
	public int cooldown;


	public ItemWeaponGreatsword(String name, int id, int dmg, float range, int cooldown, int durability, double knockback) {
		super(name, id);
		this.range = range;
		((INewItemVars)this).dxiimod$setItemRange(range);
		((INewItemVars)this).dxiimod$setItemCooldown(cooldown);
		((INewItemVars)this).dxiimod$setItemSpeedPenalty(.03);
		((INewItemVars)this).dxiimod$setDoesBreakBlocks(false);
		this.setMaxDamage(durability);
		this.cooldown = cooldown;
		this.knockback = knockback;
		this.damage = dmg;
	}


	@Override
	public boolean dxiimod$onItemAttack(EntityPlayer player, ItemStack itemstack, boolean flag){
		((IPlayerStuff)player).dxiimod$switchAnimVariant();

		if(flag) {
			dxiimodUtils.meleeAABBAttack(itemstack, player, this.damage, this.knockback, 1, 2, 2,"dxiimod.greatsword_impact", .66f, 1, 1, true);
		}else{
			player.swingItem();
			player.world.playSoundAtEntity(player, player, "dxiimod.greatsword_swing", 0.33F, 1F);
		}

		return false;
	}

	@Override
	public boolean dxiimod$onItemAltAttackTimed(EntityPlayer player, ItemStack itemstack, boolean timed){
		if(timed) {
			dxiimodUtils.meleeAABBAttack(itemstack, player, (int)(this.damage*1.25), 0, 3, 1, 1,"dxiimod.damage", .45f, .9f, .5, true);
			dxiimodUtils.meleeAABBAttack(itemstack, player, (int)(this.damage*1.25), 0, .5, 1, 1,"dxiimod.greatsword_impact", .45f, 1, .5, true);
		}else{
			((INewItemVars)this).dxiimod$setItemCooldown((int)(cooldown*1.25));
			player.swingItem();
			player.world.playSoundAtEntity(player, player, "dxiimod.greatsword_swing", 0.33F, 1F);
			((IPlayerStuff)player).dxiimod$setSpecialAnimVariant();
		}

		return true;
	}
}
