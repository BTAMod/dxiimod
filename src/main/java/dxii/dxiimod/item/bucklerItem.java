package dxii.dxiimod.item;

import dxii.dxiimod.entity.EntityGoldTomahawk;
import dxii.dxiimod.entity.EntityIronTomahawk;
import dxii.dxiimod.entity.EntityStoneTomahawk;
import dxii.dxiimod.interfaces.ILivingEntityFunctions;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.entity.projectile.*;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

//this is an absolute bloat of a class

import java.util.List;

import static net.minecraft.core.util.helper.MathHelper.clamp;

public class bucklerItem extends Item {

	public int parryDelay = 0;
	public EntityPlayer player;

	public bucklerItem(String name, int id){
		super(name, id);

		this.maxStackSize = 1;
		this.setMaxDamage(128);
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer player) {
		if(this.parryDelay == 0) {
			this.parryDelay = 20;
		}

		this.player = player;

		return itemstack;
	}

	/*I hate this func,
	I cant just make it reflect all projectiles
	just because of arrow having onGround flag,
	maybe there is a way but im too inexperienced to achieve it any other way
	 */
	public void parryHitbox(World world, EntityPlayer player){
		double bound = 1.75;
		AABB aabb1 = new AABB(
			player.x - bound,
			player.y + player.getHeadHeight() - bound,
			player.z - bound,
			player.x  + bound,
			player.y + player.getHeadHeight() + bound,
			player.z + bound
		);


		List<Entity> projectileList = player.world.getEntitiesWithinAABB(EntityProjectile.class, aabb1 );
		for (Entity entity : projectileList) {
			world.spawnParticle("largesmoke", entity.x, entity.y, entity.z, 0.0, 0.0, 0.0, 0);

			if(entity instanceof EntityArrow) {
				double oldArrowX = entity.x;
				double oldArrowY = entity.y;
				double oldArrowZ = entity.z;

				if (!((EntityArrow) entity).isGrounded()) {
					entity.remove();
					EntityArrow newArrow = new EntityArrow(world, player, true, ((EntityArrow) entity).getArrowType());
					world.entityJoinedWorld(newArrow);
					newArrow.setPos(oldArrowX, oldArrowY, oldArrowZ);
					world.playSoundAtEntity(player, player, "dxiimod.shield_bash", 0.66f, 1.0f);
					//					newArrow.damage = 10;
				}
			}

			if(entity instanceof EntityFireball) {
				entity.hurt(player, 1, DamageType.COMBAT);
				world.playSoundAtEntity(player, player, "dxiimod.shield_bash", 0.66f, 1.0f);
			}

			if(entity instanceof EntityCannonball) {

				double oldCBX = entity.x;
				double oldCBY = entity.y;
				double oldCBZ = entity.z;

				entity.remove();
				EntityCannonball newCB = new EntityCannonball(world, player);
				world.entityJoinedWorld(newCB);
				newCB.setPos(oldCBX, oldCBY, oldCBZ);
				double pushX = player.getLookAngle().xCoord;
				double pushY = player.getLookAngle().yCoord;
				double pushZ = player.getLookAngle().zCoord;
				newCB.push(pushX * 1.2, pushY * 1.2, pushZ * 1.2);
				world.playSoundAtEntity(player, player, "dxiimod.shield_bash", 0.66f, 1.0f);
			}

			if(entity instanceof EntityStoneTomahawk) {

				double oldX = entity.x;
				double oldY = entity.y;
				double oldZ = entity.z;

				entity.remove();
				EntityStoneTomahawk newCB = new EntityStoneTomahawk(world, player, player.gamemode == Gamemode.creative);
				world.entityJoinedWorld(newCB);
				newCB.setPos(oldX, oldY, oldZ);

				world.playSoundAtEntity(player, player, "dxiimod.shield_bash", 0.66f, 1.0f);
			}

			if(entity instanceof EntityIronTomahawk) {

				double oldX = entity.x;
				double oldY = entity.y;
				double oldZ = entity.z;

				entity.remove();
				EntityIronTomahawk newCB = new EntityIronTomahawk(world, player, player.gamemode == Gamemode.creative);
				world.entityJoinedWorld(newCB);
				newCB.setPos(oldX, oldY, oldZ);

				world.playSoundAtEntity(player, player, "dxiimod.shield_bash", 0.66f, 1.0f);
			}

			if(entity instanceof EntityGoldTomahawk) {

				double oldX = entity.x;
				double oldY = entity.y;
				double oldZ = entity.z;

				entity.remove();
				EntityIronTomahawk newCB = new EntityIronTomahawk(world, player, player.gamemode == Gamemode.creative);
				world.entityJoinedWorld(newCB);
				newCB.setPos(oldX, oldY, oldZ);

				world.playSoundAtEntity(player, player, "dxiimod.shield_bash", 0.66f, 1.0f);
			}


		}

		player.swingItem();
		world.playSoundAtEntity(player, player, "dxiimod.shield_swing", 0.3f, 1.0f);
	}

	@Override
	public void inventoryTick(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
		if(this.parryDelay > 0) {
			this.parryDelay--;
		}

		if(this.parryDelay >= 10 && this.parryDelay <= 16) {
			((ILivingEntityFunctions)player).dxiimod$Parry(1);
		}

		if(parryDelay == 18){
			this.parryHitbox(world, this.player);
		}
	}

}
