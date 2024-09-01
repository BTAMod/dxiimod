package dxii.dxiimod;

import dxii.dxiimod.entity.EntityStoneDagger;
import dxii.dxiimod.entity.EntityStoneTomahawk;
import dxii.dxiimod.entity.EntityIronTomahawk;
import dxii.dxiimod.entity.EntityGoldTomahawk;
import dxii.dxiimod.entity.EntityDiamondTomahawk;
import dxii.dxiimod.entity.enemy.EnemyFogLurker;
import dxii.dxiimod.item.modItems;
import dxii.dxiimod.render.RenderStoneDagger;
import dxii.dxiimod.render.RenderStoneTomahawk;
import dxii.dxiimod.render.RenderIronTomahawk;
import dxii.dxiimod.render.RenderGoldTomahawk;
import dxii.dxiimod.render.RenderDiamondTomahawk;
import dxii.dxiimod.render.enemy.RenderFogLurker1;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;




public class dxiimodMain implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "dxiimod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
		new modItems().Initialize();
    }

	@Override
	public void beforeGameStart() {
		EntityHelper.createEntity(EntityStoneDagger.class, 1489, "dxiimod$stonedagger", RenderStoneDagger::new);
		EntityHelper.createEntity(EntityStoneTomahawk.class, 1490, "dxiimod$stonetomahawk", RenderStoneTomahawk::new);
		EntityHelper.createEntity(EntityIronTomahawk.class, 1491, "dxiimod$irontomahawk", RenderIronTomahawk::new);
		EntityHelper.createEntity(EntityGoldTomahawk.class, 1492, "dxiimod$goldtomahawk", RenderGoldTomahawk::new);
		EntityHelper.createEntity(EntityDiamondTomahawk.class, 1493, "dxiimod$Diamondtomahawk", RenderDiamondTomahawk::new);
		EntityHelper.createEntity(EnemyFogLurker.class, 1494, "foglurker1", RenderFogLurker1::new);
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {
		RecipeInitializer.Initialize();
	}

	@Override
	public void initNamespaces() {
		RecipeInitializer.InitNamespaces();
	}
}
