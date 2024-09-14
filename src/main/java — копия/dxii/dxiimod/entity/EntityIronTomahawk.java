package dxii.dxiimod.entity;

import dxii.dxiimod.item.modItems;
import net.minecraft.core.HitResult;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class EntityIronTomahawk extends EntityProjectile{
	public int customRot;
	public boolean playerCreative = false;

	public EntityIronTomahawk(World world, EntityLiving entityliving, boolean playerCreative) {
		super(world, entityliving);
		this.playerCreative = playerCreative;
	}

	public void init() {
		this.defaultGravity = 0.08F;
		this.defaultProjectileSpeed = 0.9F;
	}

	public void tick(){
		super.tick();
		customRot = customRot + 45;
	}

	public void onHit(HitResult hitResult) {
		if (hitResult.hitType == HitResult.HitType.TILE) {
			if(!playerCreative) {
				EntityItem item = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(modItems.ironTomahawk, 1));
				this.world.entityJoinedWorld(item);
            }
			this.remove();
//			world.playSoundAtEntity(world.getClosestPlayer(this.x, this.y, this.z, 20.0), this, "random.drr", 0.5F, 1f);

		}

		if (hitResult.hitType == HitResult.HitType.ENTITY) {
			hitResult.entity.hurt(this.owner, 6, DamageType.COMBAT);
			if(!playerCreative) {
				EntityItem item = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(modItems.ironTomahawk, 1));
				this.world.entityJoinedWorld(item);
			}
//			world.playSoundAtEntity(world.getClosestPlayer(this.x, this.y, this.z, 20.0), this, "random.drr", 0.5F, 1f);
			this.remove();
		}

	}
}
