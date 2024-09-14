package dxii.dxiimod.mixin;

import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.core.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;


@Mixin(value = Item.class, remap = false)
public class ItemMixin implements INewItemVars {

	@Unique
	boolean itemSwingable = true;

	@Unique
	int itemCooldown = 5;

	@Unique
	float itemRange = 3.5f;

	@Override
	public boolean dxiimod$itemSwingable(){
		return this.itemSwingable;
	}

	@Override
	public void dxiimod$setItemSwingable(boolean swing){
		this.itemSwingable = swing;
	}

	@Override
	public int dxiimod$getItemCooldown(){
		return this.itemCooldown;
	}

	@Override
	public void dxiimod$setItemCooldown(int cooldown){
		this.itemCooldown = cooldown;
	}

	@Override
	public float dxiimod$getItemRange(){
		return this.itemRange;
	}

	@Override
	public void dxiimod$setItemRange(float range){
		this.itemRange = range;
	}

}
