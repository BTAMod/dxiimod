package dxii.dxiimod.mixin.accessors;

import net.minecraft.core.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Random;

@Mixin(value = Entity.class, remap = false)
public interface IAEntity {

	@Accessor("muteStepSounds")
	boolean getMuteStepSounds();

	@Accessor("random")
	Random randomm();
}
