package dxii.dxiimod.mixin;



import dxii.dxiimod.interfaces.IPlayerControllerStuff;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.controller.PlayerController;
import net.minecraft.client.render.ItemRenderer;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = ItemModelStandard.class, remap = false)
public class ItemModelMixin {

	@Unique
	protected ItemModelStandard thisObject = (ItemModelStandard)(Object)this;

	@Unique
	int attackDelay = 0;

	@Unique
	ItemRenderer renderer;

	@Unique
	float partialTick;

	@Shadow
	final Minecraft mc = Minecraft.getMinecraft(Minecraft.class);

	@Inject(
		method ="renderItemFirstPerson(Lnet/minecraft/client/render/tessellator/Tessellator;Lnet/minecraft/client/render/ItemRenderer;Lnet/minecraft/core/entity/player/EntityPlayer;Lnet/minecraft/core/item/ItemStack;F)V",
		at = @At(value = "HEAD")
	)
	public void getLocalVarsIG(Tessellator tessellator, ItemRenderer renderer, EntityPlayer player, ItemStack stack, float partialTick, CallbackInfo ci){
		this.renderer = renderer;
		this.partialTick = partialTick;

		PlayerController pc = this.mc.playerController;
		this.attackDelay = ((IPlayerControllerStuff)pc).dxiimod$getAttackDelay();
	}

	@ModifyArg(
		method ="renderItemFirstPerson(Lnet/minecraft/client/render/tessellator/Tessellator;Lnet/minecraft/client/render/ItemRenderer;Lnet/minecraft/core/entity/player/EntityPlayer;Lnet/minecraft/core/item/ItemStack;F)V",
		at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glTranslatef (FFF)V", ordinal = 1), index = 1
	)
	private float delayAnim(float param){
		return (-0.52f - (1.0f - renderer.getEquippedProgress(partialTick)) * 0.6f - (float) attackDelay / 75);
	}
}
