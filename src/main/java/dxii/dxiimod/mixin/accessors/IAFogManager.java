package dxii.dxiimod.mixin.accessors;

import net.minecraft.client.render.FogManager;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.nio.FloatBuffer;

@Mixin(value = FogManager.class, remap = false)
public interface IAFogManager {

	@Invoker("buffer")
	FloatBuffer bufferMixin(float r, float g, float b, float a);
}
