package dxii.dxiimod.item;


import dxii.dxiimod.entity.EntityFlameParticle;
import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;

public class ItemWeaponFlamespewer extends Item {

	public int reloadTimer;

	public ItemWeaponFlamespewer(String name, int id){
		super(name, id);
		this.setMaxStackSize(1);
		this.setMaxDamage(128);
		((INewItemVars)this).dxiimod$setItemUsageCooldown(1);
	}


	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if(itemstack.getMetadata() < itemstack.getMaxDamage() - 2) {
			for (int i = 4; i != 0; i--) {
				Vec3d plylook = entityplayer.getLookAngle();
				EntityFlameParticle flame = new EntityFlameParticle(world, entityplayer);
				world.entityJoinedWorld(flame);
				flame.setHeading(flame.xd, flame.yd, flame.zd, .55f, 10);
				double newX = flame.x + plylook.xCoord * .5;
				double newY = flame.y + plylook.yCoord * .5;
				double newZ = flame.z + plylook.zCoord * .5;
				world.spawnParticle("lava", newX, newY, newZ, 0.0, 0.0, 0.0, 0);
			}
			world.playSoundAtEntity(entityplayer, entityplayer, "mob.ghast.fireball", 0.6F, 1.5F);

			this.reloadTimer = 10;
			itemstack.setMetadata(itemstack.getMetadata() + 4);

		}

		return itemstack;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
		if(this.reloadTimer > 0) {
			this.reloadTimer--;
		}

		if(itemstack.getMetadata() != 0 && this.reloadTimer == 0) {
			itemstack.setMetadata(itemstack.getMetadata() - 1);
			this.reloadTimer = 2;
		}
	}
}
