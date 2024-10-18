package dxii.dxiimod.gui;

import dxii.dxiimod.interfaces.IPlayerInventory;
import dxii.dxiimod.item.accessory.baseAccessory;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

public class SlotAccessory extends Slot {
	InventoryPlayer inventory;

	public SlotAccessory(InventoryPlayer inventory, int id, int x, int y){
		super(inventory, id, x, y);
		this.inventory = inventory;
	}

	@Override
	public boolean canPutStackInSlot(ItemStack itemstack) {
		if(itemstack != null) {
			if (itemstack.getItem() instanceof baseAccessory) {
				ItemStack[] plyAccInc = ((IPlayerInventory) (inventory)).dxiimod$getAccInv();
				boolean nou = false;

				for (ItemStack is : plyAccInc) {
					if(is != null) {
						if (((baseAccessory) is.getItem()).bonus == ((baseAccessory) itemstack.getItem()).bonus) {
							nou = true;
							break;
						}
					}
				}

				return !nou;
			}
		}
		return false;
	}

	@Override
	public String getBackgroundIconId(){
		return "dxiimod:item/accessory_outline";
	}
}
