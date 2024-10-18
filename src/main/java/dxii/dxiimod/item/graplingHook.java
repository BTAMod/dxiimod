package dxii.dxiimod.item;


import dxii.dxiimod.entity.EntityHook;
import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class graplingHook extends Item {

	public EntityHook currentHook;

	public graplingHook(String name, int id) {
		super(name, id);
		this.maxStackSize = 1;
		((INewItemVars)this).dxiimod$setItemUsageCooldown(10);
	}


	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!world.isClientSide) {
			this.throwHook(world, entityplayer);
		}

		return itemstack;
	}

	public void throwHook(World world, EntityPlayer entityplayer){
		if(currentHook == null || currentHook.removed){
			currentHook = null;
			currentHook = new EntityHook(world, entityplayer);
			world.entityJoinedWorld(currentHook);
			world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.2F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}
		if(currentHook != null && currentHook.tickCount > 2){
			currentHook.remove();
			currentHook = null;
			world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.2F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}

		entityplayer.swingItem();

	}

}
