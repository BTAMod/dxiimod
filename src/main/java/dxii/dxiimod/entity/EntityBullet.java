package dxii.dxiimod.entity;

import dxii.dxiimod.dxiimodUtils;
import net.minecraft.core.HitResult;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class EntityBullet extends EntityProjectile {

	public boolean steelPiercing;

	public EntityBullet(World world, EntityLiving entityliving, boolean piercing){
		super(world, entityliving);
		this.steelPiercing = piercing;
	}

	public void init() {
		this.damage = 30;
		this.defaultGravity = 0.01F;
		this.defaultProjectileSpeed = 1.0F;
	}

	public void onHit(HitResult hitResult) {
		if (hitResult.hitType == HitResult.HitType.TILE) {
			if(dxiimodUtils.isHitBlockSolid(this, hitResult)) {
				this.remove();
//				world.playSoundAtEntity(world.getClosestPlayer(this.x, this.y, this.z, 20.0), this, "dxiimod.iron_stone", 0.5F, 1f);
			}
		}

		if (hitResult.hitType == HitResult.HitType.ENTITY) {
			hitResult.entity.hurt(this.owner, this.damage, DamageType.COMBAT);
			if(!this.steelPiercing) {
				this.remove();
			}
		}

	}

	@Override
	public void tick() {
		super.tick();
		world.spawnParticle("smoke", this.x, this.y, this.z, 0.0, 0.0, 0.0, 0);
	}
}
