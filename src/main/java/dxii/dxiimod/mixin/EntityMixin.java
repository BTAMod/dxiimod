package dxii.dxiimod.mixin;


import dxii.dxiimod.interfaces.IPlayerInventory;
import dxii.dxiimod.item.enums.EAccBonus;
import dxii.dxiimod.item.accessory.baseAccessory;
import dxii.dxiimod.mixin.accessors.IAEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(value = Entity.class, remap = false)
public class EntityMixin {

	@Unique
	protected Entity thisObject = (Entity)(Object)this ;


	//removes steps sounds if dragoncrest rig is wore
	@Redirect(
		method ="move(DDD)V",
		at = @At(value = "FIELD", target = "net/minecraft/core/entity/Entity.muteStepSounds : Z")
	)
	public boolean stepSoundMixin(Entity instance){
		boolean dragonCrest = false;

		if(thisObject instanceof EntityPlayer){
			ItemStack[] accInv = ((IPlayerInventory)(((EntityPlayer)thisObject).inventory)).dxiimod$getAccInv();
			if (accInv[0] != null && ((baseAccessory) (accInv[0].getItem())).bonus == EAccBonus.NOSTEPS) {
				dragonCrest = true;
			}else if (accInv[1] != null && ((baseAccessory) (accInv[1].getItem())).bonus == EAccBonus.NOSTEPS) {
				dragonCrest = true;
			}else if (accInv[2] != null && ((baseAccessory) (accInv[2].getItem())).bonus == EAccBonus.NOSTEPS) {
				dragonCrest = true;
			}else if (accInv[3] != null && ((baseAccessory) (accInv[3].getItem())).bonus == EAccBonus.NOSTEPS) {
				dragonCrest = true;
			}
		}

		return ( ((IAEntity)thisObject).getMuteStepSounds() || dragonCrest );
	}





}
