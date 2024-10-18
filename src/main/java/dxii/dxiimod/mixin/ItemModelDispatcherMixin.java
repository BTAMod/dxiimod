package dxii.dxiimod.mixin;


import dxii.dxiimod.render.itemModelBroadSword;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.core.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

//changes vanilla swords models (I COULD LEAVE THEM UNTOUCHED, BUT THEYRE SO BLAND AFTER CHANGED WEAPONS)
@Mixin(value = ItemModelDispatcher.class, remap = false)
public class ItemModelDispatcherMixin {



	@ModifyArg(
		method = "<init>()V",
		at = @At(value = "INVOKE", target = "net/minecraft/client/render/item/model/ItemModelDispatcher.addDispatch (Lnet/minecraft/client/render/item/model/ItemModel;)V", ordinal = 11), index = 0
	)
	public ItemModel newSword1(ItemModel dispatchable){
		return new itemModelBroadSword(Item.toolSwordIron, "minecraft").setFull3D();
	}

	@ModifyArg(
		method = "<init>()V",
		at = @At(value = "INVOKE", target = "net/minecraft/client/render/item/model/ItemModelDispatcher.addDispatch (Lnet/minecraft/client/render/item/model/ItemModel;)V", ordinal = 12), index = 0
	)
	public ItemModel newSword2(ItemModel dispatchable){
		return new itemModelBroadSword(Item.toolSwordWood, "minecraft").setFull3D();
	}

	@ModifyArg(
		method = "<init>()V",
		at = @At(value = "INVOKE", target = "net/minecraft/client/render/item/model/ItemModelDispatcher.addDispatch (Lnet/minecraft/client/render/item/model/ItemModel;)V", ordinal = 16), index = 0
	)
	public ItemModel newSword3(ItemModel dispatchable){
		return new itemModelBroadSword(Item.toolSwordStone, "minecraft").setFull3D();
	}

	@ModifyArg(
		method = "<init>()V",
		at = @At(value = "INVOKE", target = "net/minecraft/client/render/item/model/ItemModelDispatcher.addDispatch (Lnet/minecraft/client/render/item/model/ItemModel;)V", ordinal = 20), index = 0
	)
	public ItemModel newSword4(ItemModel dispatchable){
		return new itemModelBroadSword(Item.toolSwordDiamond, "minecraft").setFull3D();
	}

	@ModifyArg(
		method = "<init>()V",
		at = @At(value = "INVOKE", target = "net/minecraft/client/render/item/model/ItemModelDispatcher.addDispatch (Lnet/minecraft/client/render/item/model/ItemModel;)V", ordinal = 27), index = 0
	)
	public ItemModel newSword5(ItemModel dispatchable){
		return new itemModelBroadSword(Item.toolSwordGold, "minecraft").setFull3D();
	}
	@ModifyArg(
		method = "<init>()V",
		at = @At(value = "INVOKE", target = "net/minecraft/client/render/item/model/ItemModelDispatcher.addDispatch (Lnet/minecraft/client/render/item/model/ItemModel;)V", ordinal = 112), index = 0
	)
	public ItemModel newSword6(ItemModel dispatchable){
		return new itemModelBroadSword(Item.toolSwordSteel, "minecraft").setFull3D();
	}

}
