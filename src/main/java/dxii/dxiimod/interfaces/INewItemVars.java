package dxii.dxiimod.interfaces;

public interface INewItemVars {

	boolean dxiimod$doesBreakBlocks();
	void dxiimod$setDoesBreakBlocks(boolean breaks);

	int dxiimod$getItemCooldown();
	void dxiimod$setItemCooldown(int cooldown);

	int dxiimod$getItemUsageCooldown();
	void dxiimod$setItemUsageCooldown(int ucooldown);

	double dxiimod$getItemSpeedMod();
	void dxiimod$setItemSpeedPenalty(double mod);

	double dxiimod$getItemRange();
	void dxiimod$setItemRange(double range);

}
