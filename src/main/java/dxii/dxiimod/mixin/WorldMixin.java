package dxii.dxiimod.mixin;

import dxii.dxiimod.interfaces.IPlayerInventory;
import dxii.dxiimod.item.enums.EAccBonus;
import dxii.dxiimod.item.accessory.baseAccessory;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;


//i kinda forgot how this works, but it actually reduces distance player can be seen at if he wears fogring
@Mixin(value = World.class, remap = false)
public class WorldMixin {

	@Unique
	World thisObject = (World)(Object)this;
	/**
	 * @author
	 * @reason
	 */
	@Overwrite
	public EntityPlayer getClosestPlayer(double x, double y, double z, double radius) {
		double closestDistance = Double.POSITIVE_INFINITY;
		EntityPlayer entityplayer = null;
		if (radius < 0.0) {
			for (EntityPlayer entityPlayer1 : thisObject.players) {
				double currentDistance = entityPlayer1.distanceToSqr(x, y, z);
				if (!(currentDistance < closestDistance)) continue;
				closestDistance = currentDistance;
				entityplayer = entityPlayer1;
			}
		} else {
			double rSquared = radius * radius;
			double rThirdSquared = (radius * 0.2) * (radius * 0.2);
			for (EntityPlayer entityPlayer1 : thisObject.players) {
				double currentDistance = entityPlayer1.distanceToSqr(x, y, z);
				if (!(currentDistance < rSquared) || !(currentDistance < closestDistance)) continue;
				closestDistance = currentDistance;

				boolean plyInvis = false;

				ItemStack[] accInv = ((IPlayerInventory) (entityPlayer1.inventory)).dxiimod$getAccInv();

				for (ItemStack itemstack : accInv) {
					if (itemstack != null) {
						if( ((baseAccessory) (itemstack.getItem())).bonus == EAccBonus.INVIS) {
							plyInvis = true;
						}
					}
				}

				if(plyInvis & currentDistance < rThirdSquared || !plyInvis){
					entityplayer = entityPlayer1;
				}
			}
		}

		return entityplayer;
	}
}
