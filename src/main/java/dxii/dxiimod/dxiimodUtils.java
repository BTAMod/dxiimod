package dxii.dxiimod;

import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.HitResult;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.EntityPlayerMP;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

import static net.minecraft.core.util.helper.MathHelper.PI;
import static net.minecraft.core.util.helper.MathHelper.clamp;

public class dxiimodUtils {


	public static float normalizedMax(float f, float max){
		if(f < 0){
			return -max;
		}
		if(f > 0){
			return max;
		}
		return f;
	}

	public static boolean isHitBlockSolid(Entity ent, HitResult hitResult){
		Block hitBlock = ent.world.getBlock( hitResult.x, hitResult.y, hitResult.z );

		if(hitBlock != null) {
			return hitBlock.getHardness() > 0 || hitBlock.getHardness() == -1;
		}

		return false;
	}

	public static Block getHitBlock(Entity ent, HitResult hitResult) {
		return ent.world.getBlock(hitResult.x, hitResult.y, hitResult.z);
	}
	//props to Deboni (potato logistics creator), id never in my life realize how to draw some nice thick lines
	public static void draw3dLine(double width, double x1, double y1, double z1, double x2, double y2, double z2, float r, float g, float b) {

		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		Tessellator tessellator = Tessellator.instance;
		double l = Math.sqrt(x2*x2 + y2*y2 + z2*x2);

		Vector3f norm = new Vector3f((float)(x2 - x1), (float) (y2 - y1), (float)(z2 - z1));
		norm.normalise(norm);

		Vector3f perp = new Vector3f(1, 0, 0);
		if (Math.abs(norm.x) > 0.9f) {
			perp.x = 0.0f;
			perp.y = 0.0f;
			perp.z = 1.0f;
		} else if (Math.abs(norm.z) > 0.9f) {
			perp.x = 0.0f;
			perp.y = 1.0f;
			perp.z = 0.0f;
		}

		Vector3f up = new Vector3f(0, 0, 0) ;
		Vector3f.cross(norm, perp, up);
		up.normalise(up);

		Vector3f right = new Vector3f();
		Vector3f.cross(norm, up, right);

		up.x *= (float) (width * 0.5);
		up.y *= (float) (width * 0.5);
		up.z *= (float) (width * 0.5);

		right.x *= (float) (width * 0.5);
		right.y *= (float) (width * 0.5);
		right.z *= (float) (width * 0.5);

		tessellator.startDrawing(GL11.GL_QUADS);

		GL11.glColor4f(r, g, b, 1);

		tessellator.setNormal(-right.x, -right.y, -right.z);
		tessellator.addVertex(x1 - up.x - right.x, y1 - up.y - right.y, z1 - up.z - right.z);
		tessellator.addVertex(x1 + up.x - right.x, y1 + up.y - right.y, z1 + up.z - right.z);
		tessellator.addVertex(x2 + up.x - right.x, y2 + up.y - right.y, z2 + up.z - right.z);
		tessellator.addVertex(x2 - up.x - right.x, y2 - up.y - right.y, z2 - up.z - right.z);

		tessellator.setNormal(right.x, right.y, right.z);
		tessellator.addVertex(x1 - up.x + right.x, y1 - up.y + right.y, z1 - up.z + right.z);
		tessellator.addVertex(x2 - up.x + right.x, y2 - up.y + right.y, z2 - up.z + right.z);
		tessellator.addVertex(x2 + up.x + right.x, y2 + up.y + right.y, z2 + up.z + right.z);
		tessellator.addVertex(x1 + up.x + right.x, y1 + up.y + right.y, z1 + up.z + right.z);

		tessellator.setNormal(up.x, up.y, up.z);
		tessellator.addVertex(x1 - right.x + up.x, y1 - right.y + up.y, z1 - right.z + up.z);
		tessellator.addVertex(x1 + right.x + up.x, y1 + right.y + up.y, z1 + right.z + up.z);
		tessellator.addVertex(x2 + right.x + up.x, y2 + right.y + up.y, z2 + right.z + up.z);
		tessellator.addVertex(x2 - right.x + up.x, y2 - right.y + up.y, z2 - right.z + up.z);

		tessellator.setNormal(-up.x, -up.y, -up.z);
		tessellator.addVertex(x1 - right.x - up.x, y1 - right.y - up.y, z1 - right.z - up.z);
		tessellator.addVertex(x2 - right.x - up.x, y2 - right.y - up.y, z2 - right.z - up.z);
		tessellator.addVertex(x2 + right.x - up.x, y2 + right.y - up.y, z2 + right.z - up.z);
		tessellator.addVertex(x1 + right.x - up.x, y1 + right.y - up.y, z1 + right.z - up.z);

		tessellator.draw();

		GL11.glPopAttrib();
	}

	public static HitResult quickEyeRayCast(EntityPlayer player){
		float f = 1.0f;
		float f1 = player.xRotO + (player.xRot - player.xRotO) * f;
		float f2 = player.yRotO + (player.yRot - player.yRotO) * f;
		double posX = player.xo + (player.x - player.xo) * (double)f;
		float yOff = player instanceof EntityPlayerMP ? player.getHeightOffset() : 0.0f;
		double posY = player.yo + (player.y - player.yo) + (double)yOff;
		double posZ = player.zo + (player.z - player.zo) * (double)f;
		Vec3d vec3d = Vec3d.createVector(posX, posY, posZ);
		float f3 = MathHelper.cos(-f2 * 0.01745329f - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.01745329f - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.01745329f);
		float f6 = MathHelper.sin(-f1 * 0.01745329f);
		float f7 = f4 * f5;
		float f9 = f3 * f5;
		double reachDistance = player.getGamemode().getBlockReachDistance();
		Vec3d vec3d1 = vec3d.addVector((double)f7 * reachDistance, (double) f6 * reachDistance, (double)f9 * reachDistance);
		return dxiimodUtils.checkBlockCollisionBetweenPointsNoGrass(player.world, vec3d, vec3d1, false);
	}
//not sure if i really had to rewrite this function just to avoid non-solid blocks, but anywayh
	@Nullable
	public static HitResult checkBlockCollisionBetweenPointsNoGrass(World world, Vec3d start, Vec3d end, boolean flag1) {
		boolean shouldCollideWithFluids = false;

		HitResult movingobjectposition;
		if (Double.isNaN(start.xCoord) || Double.isNaN(start.yCoord) || Double.isNaN(start.zCoord)) {
			return null;
		}
		if (Double.isNaN(end.xCoord) || Double.isNaN(end.yCoord) || Double.isNaN(end.zCoord)) {
			return null;
		}
		int blockEndX = MathHelper.floor_double(end.xCoord);
		int blockEndY = MathHelper.floor_double(end.yCoord);
		int blockEndZ = MathHelper.floor_double(end.zCoord);
		int blockStartX = MathHelper.floor_double(start.xCoord);
		int blockStartY = MathHelper.floor_double(start.yCoord);
		int blockStartZ = MathHelper.floor_double(start.zCoord);
		int id = world.getBlockId(blockStartX, blockStartY, blockStartZ);
		int meta = world.getBlockMetadata(blockStartX, blockStartY, blockStartZ);
		Block block = Block.blocksList[id];
		if ((!flag1 || block == null || block.getCollisionBoundingBoxFromPool(world, blockStartX, blockStartY, blockStartZ) != null) && id > 0 && block.canCollideCheck(meta, shouldCollideWithFluids) && (movingobjectposition = block.collisionRayTrace(world, blockStartX, blockStartY, blockStartZ, start, end)) != null) {
			return movingobjectposition;
		}
		int l1 = 200;
		while (l1-- >= 0) {
			HitResult movingobjectposition1;
			if (Double.isNaN(start.xCoord) || Double.isNaN(start.yCoord) || Double.isNaN(start.zCoord)) {
				return null;
			}
			if (blockStartX == blockEndX && blockStartY == blockEndY && blockStartZ == blockEndZ) {
				return null;
			}
			boolean flag2 = true;
			boolean flag3 = true;
			boolean flag4 = true;
			double d = 999.0;
			double d1 = 999.0;
			double d2 = 999.0;
			if (blockEndX > blockStartX) {
				d = (double)blockStartX + 1.0;
			} else if (blockEndX < blockStartX) {
				d = (double)blockStartX + 0.0;
			} else {
				flag2 = false;
			}
			if (blockEndY > blockStartY) {
				d1 = (double)blockStartY + 1.0;
			} else if (blockEndY < blockStartY) {
				d1 = (double)blockStartY + 0.0;
			} else {
				flag3 = false;
			}
			if (blockEndZ > blockStartZ) {
				d2 = (double)blockStartZ + 1.0;
			} else if (blockEndZ < blockStartZ) {
				d2 = (double)blockStartZ + 0.0;
			} else {
				flag4 = false;
			}
			double d3 = 999.0;
			double d4 = 999.0;
			double d5 = 999.0;
			double d6 = end.xCoord - start.xCoord;
			double d7 = end.yCoord - start.yCoord;
			double d8 = end.zCoord - start.zCoord;
			if (flag2) {
				d3 = (d - start.xCoord) / d6;
			}
			if (flag3) {
				d4 = (d1 - start.yCoord) / d7;
			}
			if (flag4) {
				d5 = (d2 - start.zCoord) / d8;
			}
			int byte0 = 0;
			if (d3 < d4 && d3 < d5) {
				byte0 = blockEndX > blockStartX ? 4 : 5;
				start.xCoord = d;
				start.yCoord += d7 * d3;
				start.zCoord += d8 * d3;
			} else if (d4 < d5) {
				byte0 = blockEndY > blockStartY ? 0 : 1;
				start.xCoord += d6 * d4;
				start.yCoord = d1;
				start.zCoord += d8 * d4;
			} else {
				byte0 = blockEndZ > blockStartZ ? 2 : 3;
				start.xCoord += d6 * d5;
				start.yCoord += d7 * d5;
				start.zCoord = d2;
			}
			Vec3d vec3d2 = Vec3d.createVector(start.xCoord, start.yCoord, start.zCoord);
			vec3d2.xCoord = MathHelper.floor_double(start.xCoord);
			blockStartX = (int)vec3d2.xCoord;
			if (byte0 == 5) {
				--blockStartX;
				vec3d2.xCoord += 1.0;
			}
			vec3d2.yCoord = MathHelper.floor_double(start.yCoord);
			blockStartY = (int)vec3d2.yCoord;
			if (byte0 == 1) {
				--blockStartY;
				vec3d2.yCoord += 1.0;
			}
			vec3d2.zCoord = MathHelper.floor_double(start.zCoord);
			blockStartZ = (int)vec3d2.zCoord;
			if (byte0 == 3) {
				--blockStartZ;
				vec3d2.zCoord += 1.0;
			}
			int j2 = world.getBlockId(blockStartX, blockStartY, blockStartZ);
			int k2 = world.getBlockMetadata(blockStartX, blockStartY, blockStartZ);
			Block block1 = Block.blocksList[j2];
			if (flag1 && block1 != null && block1.getCollisionBoundingBoxFromPool(world, blockStartX, blockStartY, blockStartZ) == null || j2 <= 0 || !block1.canCollideCheck(k2, shouldCollideWithFluids) || block1.getHardness() == 0 || (movingobjectposition1 = block1.collisionRayTrace(world, blockStartX, blockStartY, blockStartZ, start, end)) == null) continue;

			return movingobjectposition1;
		}
		return null;
	}

	public static void meleeAABBAttack(ItemStack itemstack, EntityPlayer player, int damage, double knockback, double range, double radiusXZ, double radiusY, String impactSound, float soundVol, float soundPitch, double knockbackMul, boolean ignoreIframes){
		Vec3d plyLook1 = player.getViewVector(1);

		plyLook1.xCoord *= range;
		plyLook1.yCoord *= range;
		plyLook1.zCoord *= range;

		double AABBpos1x = player.x + plyLook1.xCoord;
		double AABBpos1y = player.y + player.getHeadHeight() + plyLook1.yCoord;
		double AABBpos1z = player.z + plyLook1.zCoord;

//		player.world.spawnParticle("largesmoke", AABBpos1x, AABBpos1y, AABBpos1z, 0.0, 0.0, 0.0, 0);

		AABB aabb1 = new AABB(
			AABBpos1x - radiusXZ,
			AABBpos1y - radiusY,
			AABBpos1z - radiusXZ,
			AABBpos1x + radiusXZ,
			AABBpos1y + radiusY,
			AABBpos1z + radiusXZ
		);

		List<Entity> entityList = player.world.getEntitiesWithinAABB(EntityLiving.class, aabb1);

		float pitch = (float)(soundPitch/ (Math.random() * 0.4f + 0.8f));
		for (Entity entity : entityList) {
			if (!(entity instanceof EntityPlayer)) {
				if (entity != player && dxiimodUtils.canEntityBeSeenNoGrass(entity, player) ) {
					if(ignoreIframes){
						entity.heartsFlashTime = 0;
					}

					double dist = entity.distanceTo(player);

					double d = clamp((entity.x - player.x)/dist, -1, 1);
					double d1 = clamp((entity.z - player.z)/dist, -1, 1);
					entity.push(d * knockback, 0,d1 * knockback);
					entity.xd *= knockbackMul;
					entity.yd *= knockbackMul;
					entity.zd *= knockbackMul;

					entity.hurt(player, damage, DamageType.COMBAT);

					player.world.playSoundAtEntity(player, entity, impactSound, soundVol/entityList.size(), pitch);
				}
			}
		}
		if(entityList.size() > 1 && player.gamemode != Gamemode.creative && itemstack.getMaxDamage() > 0){
			itemstack.damageItem(1, player);
		}
	}

	public static boolean canEntityBeSeenNoGrass(Entity entity, Entity entity1) {
		return dxiimodUtils.checkBlockCollisionBetweenPointsNoGrass(entity1.world, Vec3d.createVector(entity1.x, entity1.y + (double)entity1.getHeadHeight(), entity1.z), Vec3d.createVector(entity.x, entity.y + (double)entity.getHeadHeight(), entity.z), false) == null;
	}

	public static Vec3d getViewVectorEnt(Entity entity, float partialTick) {
		if (partialTick == 1.0f) {
			float f1 = MathHelper.cos(-entity.yRot * ((float)Math.PI / 180) - (float)Math.PI);
			float f3 = MathHelper.sin(-entity.yRot * ((float)Math.PI / 180) - (float)Math.PI);
			float f5 = -MathHelper.cos(-entity.xRot * ((float)Math.PI / 180));
			float f7 = MathHelper.sin(-entity.xRot * ((float)Math.PI / 180));
			return Vec3d.createVector(f3 * f5, f7, f1 * f5);
		}
		float pitch = entity.xRotO + (entity.xRot - entity.xRotO) * partialTick;
		float yaw = entity.yRotO + (entity.yRot - entity.yRotO) * partialTick;
		float xzLen = -MathHelper.cos(-pitch * ((float)Math.PI / 180));
		float x = MathHelper.sin(-yaw * ((float)Math.PI / 180) - (float)Math.PI);
		float y = MathHelper.sin(-pitch * ((float)Math.PI / 180));
		float z = MathHelper.cos(-yaw * ((float)Math.PI / 180) - (float)Math.PI);
		return Vec3d.createVector(x * xzLen, y, z * xzLen);
	}

	public static void pushRelative(Entity entity, float right, float forward, float amount) {

		float f3 = MathHelper.sqrt_float(right * right + forward * forward);
		if (f3 < 0.01f) {
			return;
		}
		if (f3 < 1.0f) {
			f3 = 1.0f;
		}
		f3 = amount / f3;
		float sinYaw = MathHelper.sin(entity.yRot * 3.141593f / 180.0f);
		float cosYaw = MathHelper.cos(entity.yRot * 3.141593f / 180.0f);
		entity.xd += ((right *= f3) * cosYaw - (forward *= f3) * sinYaw);
		entity.zd += (forward * cosYaw + right * sinYaw);
	}
}
