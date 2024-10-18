package dxii.dxiimod.item;


import dxii.dxiimod.entity.EntityDagger;
import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class throwingDaggerItem extends Item {

	public throwingDaggerItem(String name, int id) {
		super(name, id);
		this.maxStackSize = 32;
		((INewItemVars)this).dxiimod$setItemRange(2f);
		((INewItemVars)this).dxiimod$setItemCooldown(2);
		((INewItemVars)this).dxiimod$setItemUsageCooldown(4);
	}

	public int getDamageVsEntity(Entity entity) {
		return 3;
	}

	@Override
	public boolean beforeDestroyBlock(World world, ItemStack itemStack, int blockId, int x, int y, int z, Side side, EntityPlayer player) {
		return player.getGamemode() != Gamemode.creative;
	}

	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {


		itemstack.consumeItem(entityplayer);
		if (!world.isClientSide) {
			entityplayer.swingItem();
			world.entityJoinedWorld(new EntityDagger(world, entityplayer, !entityplayer.gamemode.areMobsHostile()));
			world.playSoundAtEntity(entityplayer, entityplayer, "dxiimod.throw", 0.33F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}

		return itemstack;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
		entityliving.heartsFlashTime = 0;
		return false;
	}
}
