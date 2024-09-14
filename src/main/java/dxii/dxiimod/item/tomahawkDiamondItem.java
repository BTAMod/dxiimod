package dxii.dxiimod.item;

import dxii.dxiimod.entity.EntityDiamondTomahawk;
import dxii.dxiimod.interfaces.INewItemVars;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class tomahawkDiamondItem extends Item {

	public tomahawkDiamondItem(String name, int id) {
		super(name, id);
		this.maxStackSize = 1;
		((INewItemVars)this).dxiimod$setItemRange(3f);
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
		entityliving.hurt(entityliving, 6, DamageType.COMBAT);
		return false;
	}

	@Override
	public boolean beforeDestroyBlock(World world, ItemStack itemStack, int blockId, int x, int y, int z, Side side, EntityPlayer player) {
		return player.getGamemode() != Gamemode.creative;
	}

	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!world.isClientSide) {
			entityplayer.swingItem();
			world.entityJoinedWorld(new EntityDiamondTomahawk(world, entityplayer, !entityplayer.gamemode.areMobsHostile()));
			world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		}

		return itemstack;
	}

	/*
	public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer ply, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		ply.hurt(ply, 1000, DamageType.COMBAT);
		return false;
	}*/
}
