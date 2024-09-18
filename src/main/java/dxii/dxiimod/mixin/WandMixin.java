package dxii.dxiimod.mixin;


/*
this one is made solely for debug purposes, im tired of swtiching gamemodes dmasmdsmsf
 */
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemWandSpawner;
import net.minecraft.core.player.gamemode.Gamemode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ItemWandSpawner.class, remap = false)
public class WandMixin {
	@Redirect(
		method ="onUseItem(Lnet/minecraft/core/item/ItemStack;Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/EntityPlayer;)Lnet/minecraft/core/item/ItemStack;",
		at = @At(value = "FIELD", target = "net/minecraft/core/entity/player/EntityPlayer.gamemode : Lnet/minecraft/core/player/gamemode/Gamemode;")
	)
	private Gamemode wandMixin(EntityPlayer instance){
		return Gamemode.creative;
	}


}
