package dxii.dxiimod.interfaces;

import net.minecraft.core.entity.Entity;

public interface ILivingEntityFunctions {

	void dxiimod$parryStun(Entity attacker, int damage);
	boolean dxiimod$getIsJumping();


	int dxiimod$getParryTicks();
	int dxiimod$getParriedTicks();
	void dxiimod$Parry(int parryTicks);

}
