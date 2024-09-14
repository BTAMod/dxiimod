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

import static net.minecraft.core.util.helper.MathHelper.clamp;

public class itemGreatsword extends Item {

	public float range = 4;
	public int coolDown = 15;
	public int damage = 4;
	public int durability = 64;
	public double knockback = .1;


	public itemGreatsword(String name, int id, float range, int cooldown, int durability, double knockback) {
		super(name, id);
		this.range = range;
		((INewItemVars)this).dxiimod$setItemRange(range);
		((INewItemVars)this).dxiimod$setItemCooldown(cooldown);
		this.setMaxDamage(durability);
		this.knockback = knockback;
	}

	@Override
	public boolean onBlockDestroyed(World world, ItemStack itemstack, int i, int j, int k, int l, Side side, EntityLiving entityliving) {
		if( ((EntityPlayer)entityliving).gamemode != Gamemode.creative ) {
			itemstack.damageItem(4, entityliving);
		}
		return true;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving player) {
		double bound = 2.5;

		double d = clamp(entityliving.x - player.x, -1, 1);
		double d1 = clamp(entityliving.z - player.z, -1, 1);


		Vec3d plyLook1 = player.getViewVector(1);

		plyLook1.xCoord *= range/2;
		plyLook1.yCoord *= range/2;

		double AABBpos1x = player.x + plyLook1.xCoord;
		double AABBpos1y = player.y + player.getHeadHeight() + plyLook1.yCoord;
		double AABBpos1z = player.z + plyLook1.zCoord;


		AABB aabb1 = new AABB(
			AABBpos1x - bound,
			AABBpos1y - bound/2,
			AABBpos1z - bound,
			AABBpos1x + bound,
			AABBpos1y + bound/2,
			AABBpos1z + bound
		);

		List<Entity> entityList = player.world.getEntitiesWithinAABB(EntityLiving.class, aabb1 );
		for (Entity entity : entityList) {
			if(!(entity instanceof EntityPlayer)) {
				entity.hurt(player, this.damage, DamageType.COMBAT);
				entity.push(d * this.knockback, .1, d1 * this.knockback);
			}
		}

		entityliving.world.playSoundAtEntity(entityliving, entityliving, "dxiimod.greatsword_impact", 0.33F, 1F);

		if( ((EntityPlayer)player).gamemode != Gamemode.creative ){
			itemstack.damageItem(1, entityliving);
		}
		return false;
	}

	@Override
	public boolean beforeDestroyBlock(World world, ItemStack itemStack, int blockId, int x, int y, int z, Side side, EntityPlayer player) {
		return player.getGamemode() != Gamemode.creative;
	}

}
