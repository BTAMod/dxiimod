package dxii.dxiimod.entity;

import dxii.dxiimod.dxiimodItems;
import dxii.dxiimod.dxiimodUtils;
import net.minecraft.core.HitResult;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;


//first projectile i made btw
public class EntityDagger extends EntityProjectile{
	public int customRot;
	public int penetrations;
	public int soundDelay;

	//i didnt know you could just use this.owner.isCreative or smth lol
	public boolean playerCreative;

	public EntityDagger(World world, EntityLiving entityliving, boolean playerCreative) {
		super(world, entityliving);
		this.playerCreative = playerCreative;
	}

	public void init() {
		this.defaultGravity = 0.05F;
		this.defaultProjectileSpeed = 1.0F;
	}

	public void tick(){
		super.tick();
		customRot = customRot + 45;
		if(soundDelay != 0){
			soundDelay--;
		}
	}

	public void onHit(HitResult hitResult) {
		if (hitResult.hitType == HitResult.HitType.TILE) {
			Block hitBlock = this.world.getBlock( hitResult.x, hitResult.y, hitResult.z );

			if(dxiimodUtils.isHitBlockSolid(this, hitResult)) {
				if (!playerCreative) {
					EntityItem item = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(dxiimodItems.trowingDagger, 1));
					this.world.entityJoinedWorld(item);
				}
				this.remove();
				this.world.playBlockSoundEffect(this, this.x, this.y, this.z, hitBlock, EnumBlockSoundEffectType.ENTITY_LAND);
				if(hitBlock.blockMaterial == Material.metal || hitBlock.blockMaterial == Material.stone){
					world.playSoundAtEntity(this.owner, this, "dxiimod.iron_stone", 0.25F, (float)(Math.random()/4 + .4) );
				}
			}
		}

		if (hitResult.hitType == HitResult.HitType.ENTITY) {
			if(soundDelay == 0) {
				soundDelay = 5;
				world.playSoundAtEntity(world.getClosestPlayer(this.x, this.y, this.z, 20.0), this, "dxiimod.stab", 0.5F, 1f);
			}
			hitResult.entity.hurt(this.owner, 4, DamageType.COMBAT);
			if(penetrations > 3){
				this.remove();
			}
			penetrations++;
		}

	}
}
