package dxii.dxiimod.entity.enemy;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.List;

public class EnemyFogLurker extends EntityMonster {

	public EnemyFogLurker(World world){
		super(world);
		this.moveSpeed = 100.0f;
		this.scoreValue = 200;
		this.attackStrength = 15;
		this.entityToAttack = null;
	}

	public String getEntityTexture() {return "/assets/dxiimod/textures/enemy/foglurker1.png";}
	public String getDefaultEntityTexture() {
		return "/assets/dxiimod/textures/enemy/foglurker1.png";
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if(this.entityToAttack == null){
			this.getEntToAttack();
		}

		if(this.entityToAttack != null){
			if (this.entityToAttack.distanceTo(this) > 20) {
				this.entityToAttack = null;
			}
			if(this.entityToAttack instanceof EntityPlayer) {
				if (((EntityPlayer) this.entityToAttack).getGamemode() == Gamemode.creative) {
					this.entityToAttack = null;
				}
			}
//			System.out.println(this + " : current target -  " + entityToAttack.getClass().getName() + " , distance - " + entityToAttack.distanceTo(this));;
			this.searchForPlayers();
		}
	}

	public void searchForPlayers(){
		if(this.findPlayerToAttack() == null){
			return;
		}
		this.entityToAttack = this.findPlayerToAttack();
	}

	public void getEntToAttack(){
		double bound = 15;
		List<Entity> entityList = this.world.getEntitiesWithinAABB(EntityLiving.class, this.bb.expand(bound, bound, bound) );

		for (Entity entity : entityList) {

			String entName = entity.getClass().getName();
			if (
					entName == "net.minecraft.core.entity.animal.EntitySheep" |
					entName == "net.minecraft.core.entity.animal.EntityCow" |
					entName == "net.minecraft.core.entity.monster.EntityZombie" |
					entName == "net.minecraft.core.entity.monster.EntitySkeleton" |
					entName == "net.minecraft.core.entity.monster.EntityArmoredZombie" |
					entName == "net.minecraft.core.entity.monster.EntityCreeper" |
					entName == "net.minecraft.core.entity.monster.EntitySlime" |
					entName == "net.minecraft.core.entity.animal.EntityChicken" |
					entName == "net.minecraft.core.entity.monster.EntitySpider" |
					entName == "net.minecraft.core.entity.monster.EntitySpider"
			) {
				this.entityToAttack = entity;
//				System.out.println(this + " : found ent to attack!!");
			}
		}
	}

	@Override
	public String getLivingSound() {
		return "mob.zombie";
	}

	@Override
	protected String getHurtSound() {
		return "mob.zombiehurt";
	}

	@Override
	protected String getDeathSound() {
		return "mob.zombiedeath";
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 16;
	}

}
