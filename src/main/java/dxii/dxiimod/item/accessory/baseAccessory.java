package dxii.dxiimod.item.accessory;

import dxii.dxiimod.item.enums.EAccBonus;
import net.minecraft.core.item.Item;


//nothing there lol, all functionality is made thru mixins
public class baseAccessory extends Item {

	public EAccBonus bonus;

	public baseAccessory(String name, int id, EAccBonus bonus){
		super(name, id);
		this.bonus = bonus;
		this.maxStackSize = 1;
	}
}
