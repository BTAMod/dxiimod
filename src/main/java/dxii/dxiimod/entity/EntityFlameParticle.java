package dxii.dxiimod.entity;

import dxii.dxiimod.dxiimodUtils;
import net.minecraft.core.HitResult;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class EntityFlameParticle extends EntityProjectile {
	public EntityFlameParticle(World world, EntityLiving entityliving){
		super(world, entityliving);
	}

	public void init() {
		this.damage = 1;
		this.defaultGravity = 0.003F;
		this.defaultProjectileSpeed = 0.15F;
	}

	public void onHit(HitResult hitResult) {
		if (hitResult.hitType == HitResult.HitType.TILE) {
			if(dxiimodUtils.isHitBlockSolid(this, hitResult)) {
				this.remove();
//				world.playSoundAtEntity(world.getClosestPlayer(this.x, this.y, this.z, 20.0), this, "dxiimod.iron_stone", 0.5F, 1f);
			}
		}

		if (hitResult.hitType == HitResult.HitType.ENTITY && hitResult.entity != this.owner) {
			hitResult.entity.hurt(this.owner, this.damage, DamageType.FIRE);

			hitResult.entity.xd *= .33;
			hitResult.entity.yd = 0.0;
			hitResult.entity.zd *= .33;

			hitResult.entity.remainingFireTicks = 5;
			hitResult.entity.heartsFlashTime = 1;
			this.remove();
		}

	}

	@Override
	protected void checkOnWater(boolean addVelocity) {
		if (this.checkAndHandleWater(addVelocity)) {
			this.remove();
		}
	}

	@Override
	public void tick() {
		super.tick();

		this.xd *= 7.5;
		this.yd *= 7.5;
		this.zd *= 7.5;

		if(this.tickCount > 8){
			this.remove();
		}
		if(this.tickCount > 1) {
			world.spawnParticle("flame", this.x, this.y, this.z, this.random.nextFloat()*.1, this.random.nextFloat()*.1, this.random.nextFloat()*.1, 0);
		}
	}
}
