package dxii.dxiimod.item;

import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import static net.minecraft.core.util.helper.MathHelper.clamp;

public class hammerStoneItem extends Item {


	public hammerStoneItem(String name, int id) {
		super(name, id);
		((INewItemVars)this).dxiimod$setItemRange(3f);
		((INewItemVars)this).dxiimod$setItemCooldown(25);
		this.setMaxDamage(128);
	}

	@Override
	public boolean onBlockDestroyed(World world, ItemStack itemstack, int i, int j, int k, int l, Side side, EntityLiving entityliving) {
		if( ((EntityPlayer)entityliving).gamemode != Gamemode.creative ) {
			itemstack.damageItem(2, entityliving);
		}
		return true;
	}

	public int getDamageVsEntity(Entity entity) {
		return 10;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
		double d = clamp(entityliving.x - entityliving1.x, -1, 1);
		double d1 = clamp(entityliving.z - entityliving1.z, -1, 1);
		entityliving.push(d * .6, .1,d1 * .6);

		if( ((EntityPlayer)entityliving1).gamemode != Gamemode.creative ){
			itemstack.damageItem(1, entityliving);
		}
		entityliving.world.playSoundAtEntity(entityliving1, entityliving1, "step.stone", 1.5F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
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
