package dxii.dxiimod.mixin.accessors;

import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = Container.class, remap = false)
public interface IAContainerPlayer {

	@Invoker("addSlot")
	void addSlotMixin(Slot slot);
}
