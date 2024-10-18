package dxii.dxiimod.render;

import dxii.dxiimod.item.ItemWeaponFlintlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.ItemRenderer;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class itemModelGun extends ItemModelStandard {

	final Minecraft mc = Minecraft.getMinecraft(Minecraft.class);

	public itemModelGun(Item item, String namespace) {
		super(item, namespace);
		if (namespace != null) {
			this.icon = TextureRegistry.getTexture(namespace + ":item/" + item.getKey().substring("item.".length()).replace(".", "_"));
		}
	}

	@Override
	public void renderItemFirstPerson(Tessellator tessellator, ItemRenderer renderer, EntityPlayer player, ItemStack stack, float partialTick) {
		float brightness = 1.0f;
		if (this.mc.fullbright || this.itemfullBright || LightmapHelper.isLightmapEnabled()) {
			if (LightmapHelper.isLightmapEnabled()) {
				int lightmapCoord = player.getLightmapCoord(partialTick);
				if (this.itemfullBright) {
					lightmapCoord = LightmapHelper.setBlocklightValue(lightmapCoord, 15);
				}
				LightmapHelper.setLightmapCoord(lightmapCoord);
			}
		} else {
			brightness = player.getBrightness(1.0f);
		}
		float swingProgress = player.getSwingProgress(partialTick);
		float animationProgress2 = MathHelper.sin(swingProgress * (float)Math.PI);
		float animationProgress = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
		int itemStatus = ((ItemWeaponFlintlock)this.item).status;

		if(itemStatus == 1) {
			GL11.glTranslatef(animationProgress * 0.3f, -animationProgress * 0.2f, animationProgress2 * 0.3f);
		}else{
			GL11.glTranslatef(-animationProgress * 0.4f, MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float) Math.PI * 2.0f) * 0.2f, -animationProgress2 * 0.2f);
		}
			GL11.glTranslatef(0.56f, -0.52f - (1.0f - renderer.getEquippedProgress(partialTick)) * 0.6f, -0.81999997f);
			GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
		GL11.glEnable(32826);

		float scale = 0.4f;
		GL11.glScalef(scale, scale, scale);
		this.heldTransformFirstPerson(renderer, player, stack);
		this.renderItem(tessellator, renderer, stack, player, brightness, true);
	}

	@Override
	public void renderItem(Tessellator tessellator, ItemRenderer renderer, ItemStack itemstack, @Nullable Entity entity, float brightness, boolean handheldTransform) {

		if (this.itemfullBright || LightmapHelper.isLightmapEnabled()) {
			brightness = 1.0f;
		}
		if (handheldTransform) {
			GL11.glTranslatef(-0.0f, -0.3f, 0.0f);
			float handheldScale = 1.5f;
			GL11.glScalef(handheldScale, handheldScale, handheldScale);
			GL11.glRotatef(50.0f, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(335.0f, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-0.9375f, -0.0625f, 0.0f);
		}
		this.renderItemInWorld(tessellator, entity, itemstack, brightness, 1.0f, false);
	}

	@Override
	public void renderItemThirdPerson(Tessellator tessellator, ItemRenderer renderer, Entity entity, ItemStack itemstack, boolean handheldTransform) {
		float brightness = 1.0f;
		if (this.mc.fullbright || this.itemfullBright || LightmapHelper.isLightmapEnabled()) {
			if (LightmapHelper.isLightmapEnabled()) {
				int lightmapCoord = entity.getLightmapCoord(1.0f);
				if (this.itemfullBright) {
					lightmapCoord = LightmapHelper.getLightmapCoord(15, 15);
				}
				LightmapHelper.setLightmapCoord(lightmapCoord);
			}
		} else {
			brightness = entity.getBrightness(1.0f);
		}
		GL11.glTranslatef(-0.0625f, 0.6375f, 0.1625f);
		this.heldTransformThirdPerson(renderer, entity, itemstack);
		this.renderItem(tessellator, renderer, itemstack, entity, brightness, handheldTransform);
	}
}


