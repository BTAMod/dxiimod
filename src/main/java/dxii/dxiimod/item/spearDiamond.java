package dxii.dxiimod.item;

import dxii.dxiimod.interfaces.INewItemVars;

public class spearDiamond extends itemSpear {


	public spearDiamond(String name, int id){
		super(name, id, 4, 18, 512);
		this.damage = 10;
		((INewItemVars)this).dxiimod$setItemRange(range);
		((INewItemVars)this).dxiimod$setItemCooldown(coolDown);

	}

}
