package dxii.dxiimod.mixin.accessors;

import net.minecraft.core.entity.ConsumedFood;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.ItemFood;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ConsumedFood.class, remap = false)
public interface IAConsumedFood {

	@Accessor("entity")
	EntityLiving getFoodPlayer();

	@Accessor("food")
	ItemFood getFood();

	@Accessor("healRemaining")
	int getHealRemaining();

	@Accessor("healRemaining")
	void setHealRemaining(int newHR);


	@Accessor("tickCounter")
	int getTickCounter();

	@Accessor("tickCounter")
	void setTickCounter(int ass);



}
