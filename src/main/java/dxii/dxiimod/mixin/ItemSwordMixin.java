package dxii.dxiimod.mixin;


import dxii.dxiimod.dxiimodUtils;
import dxii.dxiimod.interfaces.INewItemFunctions;
import dxii.dxiimod.interfaces.INewItemVars;
import dxii.dxiimod.interfaces.IPlayerStuff;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolSword;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//changes vanilla swords models (I COULD LEAVE THEM UNTOUCHED, BUT THEYRE SO BLAND AFTER CHANGED WEAPONS)
@Mixin(value = ItemToolSword.class, remap = false)
public class ItemSwordMixin extends Item implements INewItemFunctions {

	@Shadow
	private int weaponDamage;

	@Shadow
	private ToolMaterial material;

	@Unique
	public ItemToolSword thisObject = (ItemToolSword)(Object)this;

	public ItemSwordMixin(String name, int id) {
		super(name, id);
	}

	@Inject(
		method = "<init>",
		at = @At(value = "TAIL")
	)
	public void initNewSword(String name, int id, ToolMaterial enumtoolmaterial, CallbackInfo ci){
		((INewItemVars)thisObject).dxiimod$setItemCooldown(5);
		((INewItemVars)thisObject).dxiimod$setItemUsageCooldown(15);
		((INewItemVars)thisObject).dxiimod$setDoesBreakBlocks(false);
	}

	@Override
	public boolean dxiimod$onItemAttack(EntityPlayer player, ItemStack itemstack, boolean flag){
		((INewItemVars)thisObject).dxiimod$setItemCooldown(5);

		if(flag) {
			dxiimodUtils.meleeAABBAttack(itemstack, player, this.weaponDamage, 0, .75, 1.5, 1,"", .35f, 1.4f, .5, true);
		}else{
			player.swingItem();
			if(material == ToolMaterial.stone) {
				player.world.playSoundAtEntity(player, player, "dxiimod.broadsword_swing", 0.25F, (float) (.66 / (Math.random() * 0.4f + 0.8f)));
			}else{
				player.world.playSoundAtEntity(player, player, "dxiimod.broadsword_swing", 0.2F, (float) (1 / (Math.random() * 0.4f + 0.8f)));
			}
		}

		return false;
	}

	@Override
	public boolean dxiimod$onItemAltAttackTimed(EntityPlayer player, ItemStack itemstack, boolean timed){
		((IPlayerStuff)player).dxiimod$setSpecialAnimVariant();
		((INewItemVars)thisObject).dxiimod$setItemCooldown(15);

		if(timed) {
			dxiimodUtils.meleeAABBAttack(itemstack, player, (int)(this.weaponDamage*1.25), 0, 2.5, .5, .5,"dxiimod.damage", .4f, 1, .5, true);
			dxiimodUtils.meleeAABBAttack(itemstack, player, (int)(this.weaponDamage*1.25), 0, .5, 1, 1,"", .35f, 1f, .5, true);
		}else{
			player.swingItem();
			if(material == ToolMaterial.stone) {
				player.world.playSoundAtEntity(player, player, "dxiimod.broadsword_swing", 0.25F, (float) (.66 / (Math.random() * 0.4f + 0.8f)));
			}else{
				player.world.playSoundAtEntity(player, player, "dxiimod.broadsword_swing", 0.2F, (float) (1 / (Math.random() * 0.4f + 0.8f)));
			}
		}

		return true;
	}

}
