package dxii.dxiimod.entity.enemy;

import net.minecraft.core.world.World;

public class EnemyFogLurker2 extends EnemyFogLurker{
	public EnemyFogLurker2(World world){
		super(world);
		this.moveBoostDistTR1 = 18;
		this.moveBoostDistTR2 = 15;
		this.scoreValue = 150;
		this.attackStrength = 20;
		this.moveSpeed = .6f;
		this.moveBoost = .6;
		this.bbHeight = this.bbHeight * 2.33f;
		this.attackDist = 2.5;
	}


	public String getEntityTexture() {return "/assets/dxiimod/textures/enemy/foglurker2.png";}

	@Override
	public void onLivingUpdate(){
		super.onLivingUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
		return false;
	}

	@Override
	public int getMaxHealth() {
		return 60;
	}
}
