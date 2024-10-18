package dxii.dxiimod.item;

import dxii.dxiimod.dxiimodItems;
import dxii.dxiimod.dxiimodUtils;
import dxii.dxiimod.entity.EntityTomahawk;
import dxii.dxiimod.interfaces.INewItemFunctions;
import dxii.dxiimod.interfaces.INewItemVars;
import dxii.dxiimod.interfaces.IPlayerStuff;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;

public class itemTomahawk extends Item implements INewItemFunctions {

	public ToolMaterial toolMat;
	public int damage;
	public int rangedDamage;

	public itemTomahawk(String name, int id, int stackSize, int cooldown, float range, int meleeDamage, int rangedDamage, ToolMaterial toolMat) {
		super(name, id);
		this.maxStackSize = stackSize;
		this.toolMat = toolMat;
		this.damage = meleeDamage;
		this.rangedDamage = rangedDamage;
		((INewItemVars)this).dxiimod$setItemRange(range);
		((INewItemVars)this).dxiimod$setItemUsageCooldown(cooldown);
		((INewItemVars)this).dxiimod$setItemCooldown(7);
		((INewItemVars)this).dxiimod$setDoesBreakBlocks(false);
	}

	@Override
	public boolean dxiimod$onItemAttack(EntityPlayer player, ItemStack itemstack, boolean flag){
		if(flag) {
			dxiimodUtils.meleeAABBAttack(itemstack, player, this.damage, 0, 1.25, .75, .75,"", .3f, 2, 1, true);
		}else{
			player.swingItem();
			player.world.playSoundAtEntity(player, player, "dxiimod.swing_hammer", 0.2F, 1F);
		}

		return false;
	}


	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		((IPlayerStuff)entityplayer).dxiimod$setSpecialAnimVariant();

		if (!world.isClientSide) {
			EntityTomahawk tomahawk = null;

			if(this.toolMat == ToolMaterial.stone) {
				tomahawk = new EntityTomahawk(world, entityplayer, this.rangedDamage, "/assets/dxiimod/textures/entity/tomahawk_stone.png", 0.9f, 0.08f, true, dxiimodItems.stoneTomahawk);
			}else if(this.toolMat == ToolMaterial.iron){
				tomahawk = new EntityTomahawk(world, entityplayer, this.rangedDamage, "/assets/dxiimod/textures/entity/tomahawk_iron.png", 0.97f, 0.08f, true, dxiimodItems.ironTomahawk);
			}else if(this.toolMat == ToolMaterial.gold){
				tomahawk = new EntityTomahawk(world, entityplayer, this.rangedDamage, "/assets/dxiimod/textures/entity/tomahawk_gold.png", 0.97f, 0.065f, true, dxiimodItems.goldTomahawk);
			}else if(this.toolMat == ToolMaterial.diamond){
				tomahawk = new EntityTomahawk(world, entityplayer, this.rangedDamage, "/assets/dxiimod/textures/entity/tomahawk_diamond.png", 0.97f, 0.065f, false, dxiimodItems.diamondTomahawk);
			}

			if(tomahawk != null) {
				entityplayer.swingItem();
				itemstack.consumeItem(entityplayer);
				world.entityJoinedWorld(tomahawk);
				world.playSoundAtEntity(entityplayer, entityplayer, "dxiimod.throw", 0.33F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			}

		}

		return itemstack;
	}

	@Override
	public boolean dxiimod$onItemAltAttackTimed(EntityPlayer player, ItemStack itemstack, boolean timed){
		return false;
	}

}
