package dxii.dxiimod;

import dxii.dxiimod.entity.EntityDagger;
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
import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.components.ToggleableOptionComponent;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.RangeOption;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.enums.EnumCreatureType;
import net.minecraft.core.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;




public class dxiimodMain implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "dxiimod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static OptionsPage optionsPage;
	public static RangeOption FogDist;

	public static int ITEM_ID = 12000;
	public static int ENTITY_ID = 15000;

    @Override
    public void onInitialize() {
		new modItems().Initialize();

		SoundHelper.addSound(MOD_ID, "greatsword_impact.ogg");
		SoundHelper.addSound(MOD_ID, "foglurker_random1.ogg");
		SoundHelper.addSound(MOD_ID, "foglurker_random2.ogg");
		SoundHelper.addSound(MOD_ID, "foglurker_random3.ogg");
		SoundHelper.addSound(MOD_ID, "foglurker_random4.ogg");
		SoundHelper.addSound(MOD_ID, "foglurker_target.ogg");

		for(Biome b : Registries.BIOMES){
			b.getSpawnableList(EnumCreatureType.monster).add(new SpawnListEntry(EnemyFogLurker.class, 300));
		}
    }

	@Override
	public void beforeGameStart() {
		EntityHelper.createEntity(EntityDagger.class, ENTITY_ID++, "dxiimod$stonedagger", RenderStoneDagger::new);
		EntityHelper.createEntity(EntityStoneTomahawk.class, ENTITY_ID++, "dxiimod$stonetomahawk", RenderStoneTomahawk::new);
		EntityHelper.createEntity(EntityIronTomahawk.class, ENTITY_ID++, "dxiimod$irontomahawk", RenderIronTomahawk::new);
		EntityHelper.createEntity(EntityGoldTomahawk.class, ENTITY_ID++, "dxiimod$goldtomahawk", RenderGoldTomahawk::new);
		EntityHelper.createEntity(EntityDiamondTomahawk.class, ENTITY_ID++, "dxiimod$Diamondtomahawk", RenderDiamondTomahawk::new);
		EntityHelper.createEntity(EnemyFogLurker.class, ENTITY_ID++, "foglurker1", RenderFogLurker1::new);
	}

	@Override
	public void afterGameStart() {
		optionsPage = new OptionsPage(MOD_ID+".options", modItems.stoneTomahawk.getDefaultStack());
		OptionsPages.register(optionsPage);

		optionsPage
			.withComponent(
				new OptionsCategory(MOD_ID + ".category.fog")
					.withComponent(new ToggleableOptionComponent<>(FogDist)) );

	}

	public static void optionsInit(GameSettings gs){
		//credits to big sir for conveniently showing me how to implement mod's own settings :troll
		FogDist = new RangeOption(gs, MOD_ID+ ".fog", 1, 5);

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
