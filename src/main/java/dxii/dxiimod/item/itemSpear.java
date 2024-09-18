package dxii.dxiimod.item;

import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;

import java.util.List;

public class itemSpear extends Item {

	public float range;
	public int coolDown = 15;
	public int damage = 4;
	public int durability;


	public itemSpear(String name, int id, float range, int cooldown, int durability) {
		super(name, id);
		this.range = range;
		((INewItemVars)this).dxiimod$setItemRange(range);
		((INewItemVars)this).dxiimod$setItemCooldown(cooldown);
		this.setMaxDamage(durability);
	}

	@Override
	public boolean onBlockDestroyed(World world, ItemStack itemstack, int i, int j, int k, int l, Side side, EntityLiving entityliving) {
		if( ((EntityPlayer)entityliving).gamemode != Gamemode.creative ) {
			itemstack.damageItem(4, entityliving);
		}
		return true;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving player) {
		double bound = .4;

		Vec3d plyLook1 = player.getViewVector(1);
		Vec3d plyLook2 = player.getViewVector(1);
		Vec3d plyLook3 = player.getViewVector(1);

		plyLook1.xCoord *= range/3;
		plyLook1.yCoord *= range/3;
		plyLook1.zCoord *= range/3;

		plyLook2.xCoord *= (range/3) * 2;
		plyLook2.yCoord *= (range/3) * 2;
		plyLook2.zCoord *= (range/3) * 2;

		plyLook3.xCoord *= range;
		plyLook3.yCoord *= range;
		plyLook3.zCoord *= range;

		double AABBpos1x = player.x + plyLook1.xCoord;
		double AABBpos1y = player.y + player.getHeadHeight() + plyLook1.yCoord;
		double AABBpos1z = player.z + plyLook1.zCoord;

		double AABBpos2x = player.x + plyLook2.xCoord;
		double AABBpos2y = player.y + player.getHeadHeight() + plyLook2.yCoord;
		double AABBpos2z = player.z + plyLook2.zCoord;

		double AABBpos3x = player.x + plyLook3.xCoord;
		double AABBpos3y = player.y + player.getHeadHeight() + plyLook3.yCoord;
		double AABBpos3z = player.z + plyLook3.zCoord;

		AABB aabb1 = new AABB(
			AABBpos1x - bound,
			AABBpos1y - bound,
			AABBpos1z - bound,
			AABBpos1x + bound,
			AABBpos1y + bound,
			AABBpos1z + bound
		);

		AABB aabb2 = new AABB(
			AABBpos2x - bound,
			AABBpos2y - bound,
			AABBpos2z - bound,
			AABBpos2x + bound,
			AABBpos2y + bound,
			AABBpos2z + bound
		);

		AABB aabb3 = new AABB(
			AABBpos3x - bound,
			AABBpos3y - bound,
			AABBpos3z - bound,
			AABBpos3x + bound,
			AABBpos3y + bound,
			AABBpos3z + bound
		);


		List<Entity> entityList = player.world.getEntitiesWithinAABB(EntityLiving.class, aabb1 );
		for (Entity entity : entityList) {
			entity.hurt(player, this.damage, DamageType.COMBAT);
		}

		List<Entity> entityList2 = player.world.getEntitiesWithinAABB(EntityLiving.class, aabb2 );
		for (Entity entity : entityList2) {
			entity.hurt(player, this.damage, DamageType.COMBAT);
		}

		List<Entity> entityList3 = player.world.getEntitiesWithinAABB(EntityLiving.class, aabb3 );
		for (Entity entity : entityList3) {
			entity.hurt(player, this.damage, DamageType.COMBAT);
		}

		if( ((EntityPlayer)player).gamemode != Gamemode.creative ){
			itemstack.damageItem(1, entityliving);
		}

		entityliving.world.playSoundAtEntity(entityliving, entityliving, "dxiimod.stab", .33F, 1F);

		return false;


	}

	@Override
	public boolean beforeDestroyBlock(World world, ItemStack itemStack, int blockId, int x, int y, int z, Side side, EntityPlayer player) {
		return player.getGamemode() != Gamemode.creative;
	}

}
