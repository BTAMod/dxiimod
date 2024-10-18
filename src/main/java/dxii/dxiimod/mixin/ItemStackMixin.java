package dxii.dxiimod.mixin;

import dxii.dxiimod.interfaces.IPlayerInventory;
import dxii.dxiimod.item.enums.EAccBonus;
import dxii.dxiimod.item.accessory.baseAccessory;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//currently used only for cherry wood ring
@Mixin(value = ItemStack.class, remap = false)
public class ItemStackMixin {

	@Unique
	public Entity currentPlayer;

	//cherry wood grain ring
	@Inject(
		method = "damageItem(ILnet/minecraft/core/entity/Entity;)V",
		at = @At(value = "HEAD"))
	public void cherryRing(int i, Entity entity, CallbackInfo ci){
		currentPlayer = entity;
	}

	@Redirect(
		method = "damageItem(ILnet/minecraft/core/entity/Entity;)V",
		at = @At(value = "INVOKE", target = "net/minecraft/core/item/ItemStack.isItemStackDamageable ()Z")
	)
	private boolean cherryRing2(ItemStack instance){
		if(currentPlayer instanceof EntityPlayer) {
			boolean cherry = false;
			boolean rand = (Math.random() <= .66);

			ItemStack[] accInv = ((IPlayerInventory) (((EntityPlayer) currentPlayer).inventory)).dxiimod$getAccInv();
			if (accInv[0] != null && ((baseAccessory) (accInv[0].getItem())).bonus == EAccBonus.CHERRYWOOD) {
				cherry = true;
			} else if (accInv[1] != null && ((baseAccessory) (accInv[1].getItem())).bonus == EAccBonus.CHERRYWOOD) {
				cherry = true;
			} else if (accInv[2] != null && ((baseAccessory) (accInv[2].getItem())).bonus == EAccBonus.CHERRYWOOD) {
				cherry = true;
			} else if (accInv[3] != null && ((baseAccessory) (accInv[3].getItem())).bonus == EAccBonus.CHERRYWOOD) {
				cherry = true;
			}
			return !instance.isItemStackDamageable() || !(cherry & rand);
		}else{
			return !instance.isItemStackDamageable();
		}
	}

}
