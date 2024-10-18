package dxii.dxiimod.item;

import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import static net.minecraft.core.util.helper.MathHelper.clamp;

public class itemWeaponRapier extends Item {

	public int damage;

	public itemWeaponRapier(String name, int id, int dmg, int cooldown, int durability) {
		super(name, id);
		((INewItemVars)this).dxiimod$setItemRange(3f);
		((INewItemVars)this).dxiimod$setItemCooldown(cooldown);
		this.setMaxDamage(durability);
		this.damage = dmg;
	}

	@Override
	public boolean onBlockDestroyed(World world, ItemStack itemstack, int i, int j, int k, int l, Side side, EntityLiving entityliving) {
		if( ((EntityPlayer)entityliving).gamemode != Gamemode.creative ) {
			itemstack.damageItem(2, entityliving);
		}
		return true;
	}

	public int getDamageVsEntity(Entity entity) {
		return this.damage;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
		entityliving.xd = 0;
		entityliving.yd = 0;
		entityliving.zd = 0;
		if( ((EntityPlayer)entityliving1).gamemode != Gamemode.creative ){
			itemstack.damageItem(1, entityliving1);
		}
		entityliving.world.playSoundAtEntity(entityliving1, entityliving1, "dxiimod.stab", 0.65F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		return false;
	}

	@Override
	public boolean beforeDestroyBlock(World world, ItemStack itemStack, int blockId, int x, int y, int z, Side side, EntityPlayer player) {
		return player.getGamemode() != Gamemode.creative;
	}

	/*
	public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer ply, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		ply.hurt(ply, 1000, DamageType.COMBAT);
		return false;
	}*/
}
