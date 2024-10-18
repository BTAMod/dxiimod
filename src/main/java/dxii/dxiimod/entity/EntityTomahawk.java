package dxii.dxiimod.entity;

import dxii.dxiimod.dxiimodUtils;
import net.minecraft.core.HitResult;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class EntityTomahawk extends EntityProjectile{
	public int customRot;
	public String texture;
	public boolean doesDrop;
	public Item droppedItem;

	public EntityTomahawk(World world, EntityLiving entityliving, int damage, String texture, float speed, float gravity, boolean doesDrop, Item droppedItem) {
		super(world, entityliving);
		this.damage = damage;
		this.texture = texture;
		this.doesDrop = doesDrop;
		this.droppedItem = droppedItem;
		this.defaultGravity = gravity;
		this.defaultProjectileSpeed = speed;
	}



	public void tick(){
		super.tick();
		customRot = customRot + 45;
	}

	public void onHit(HitResult hitResult) {
		if (hitResult.hitType == HitResult.HitType.TILE) {
			Block hitBlock = this.world.getBlock( hitResult.x, hitResult.y, hitResult.z );

			if(dxiimodUtils.isHitBlockSolid(this, hitResult)) {
				if(((EntityPlayer)this.owner).gamemode != Gamemode.creative && this.doesDrop ) {
					EntityItem item = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(this.droppedItem, 1));
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
			hitResult.entity.hurt(this.owner, this.damage, DamageType.COMBAT);
			if(((EntityPlayer)this.owner).gamemode != Gamemode.creative && this.doesDrop) {
				EntityItem item = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(this.droppedItem, 1));
				this.world.entityJoinedWorld(item);
			}
//			world.playSoundAtEntity(world.getClosestPlayer(this.x, this.y, this.z, 20.0), this, "random.drr", 0.5F, 1f);
			this.remove();
		}

	}
}
