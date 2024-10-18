package dxii.dxiimod.mixin.accessors;

import net.minecraft.core.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Random;

@Mixin(value = EntityLiving.class, remap = false)
public interface IAEntityLiving {

	@Invoker("jump")
	void jumpMixin();

	@Invoker("getHurtSound")
	String getHurtSoundNew();

	@Invoker("getSoundVolume")
	float getSoundVolNew();
}
