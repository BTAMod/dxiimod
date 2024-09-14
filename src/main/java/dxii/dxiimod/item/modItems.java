package dxii.dxiimod.item;

import dxii.dxiimod.dxiimodMain;
import dxii.dxiimod.render.itemModelGreatSword;
import dxii.dxiimod.render.itemModelSpear;
import dxii.dxiimod.render.itemModelSwing;
import net.minecraft.client.render.item.model.ItemModelStandard;
import turniplabs.halplibe.helper.ItemBuilder;

public class modItems{
	public static daggerStoneItem stoneDagger;

	public static tomahawkStoneItem stoneTomahawk;
	public static tomahawkIronItem ironTomahawk;
	public static tomahawkGoldItem goldTomahawk;
	public static tomahawkDiamondItem diamondTomahawk;

	public static hammerStoneItem stoneHammer;
	public static hammerIronItem ironHammer;
	public static hammerGolditem goldHammer;
	public static hammerSteelItem steelHammer;

	public static spearWooden woodenSpear;
	public static spearStone stoneSpear;
	public static spearIron ironSpear;
	public static spearGold goldSpear;
	public static spearDiamond diamondSpear;
	public static spearSteel steelSpear;

	public static greatswordStone greatswordStone;
	public static greatswordIron greatswordIron;
	public static greatswordGold greatswordGold;
	public static greatswordSteel greatswordSteel;

	// Function that make the items
	public void Initialize() {
		stoneDagger = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/dagger").build(new daggerStoneItem("STONE_DAGGER", 12000) );

		stoneTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_stone").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new tomahawkStoneItem("STONE_TOMAHAWK", 12001) );
		ironTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_iron").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new tomahawkIronItem("IRON_TOMAHAWK", 12002) );
		goldTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_gold").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new tomahawkGoldItem("GOLD_TOMAHAWK", 12003) );
		diamondTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_diamond").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new tomahawkDiamondItem("DIAMOND_TOMAHAWK", 12004) );

		stoneHammer = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/hammer_stone").setItemModel(item -> new itemModelSwing(item, dxiimodMain.MOD_ID).setFull3D()).build(new hammerStoneItem("STONE_HAMMER", 12005) );
		ironHammer = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/hammer_iron").setItemModel(item -> new itemModelSwing(item, dxiimodMain.MOD_ID).setFull3D()).build(new hammerIronItem("IRON_HAMMER", 12006) );
		goldHammer = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/hammer_gold").setItemModel(item -> new itemModelSwing(item, dxiimodMain.MOD_ID).setFull3D()).build(new hammerGolditem("GOLD_HAMMER", 12007) );
		steelHammer = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/hammer_steel").setItemModel(item -> new itemModelSwing(item, dxiimodMain.MOD_ID).setFull3D()).build(new hammerSteelItem("STEEL_HAMMER", 12008) );

		woodenSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_wood").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearWooden("WOOD_SPEAR", 12009) );
		stoneSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_stone").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearStone("STONE_SPEAR", 12010) );
		ironSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_iron").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearIron("IRON_SPEAR", 12011) );
		goldSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_gold").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearGold("GOLD_SPEAR", 12012) );
		diamondSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_diamond").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearDiamond("DIAMOND_SPEAR", 12013) );
		steelSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_steel").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearSteel("STEEL_SPEAR", 12014) );

		greatswordStone = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/greatsword_stone").setItemModel(item -> new itemModelGreatSword(item, dxiimodMain.MOD_ID).setFull3D()).build(new greatswordStone("STONE_GREATSWORD", 12015) );
		greatswordIron = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/greatsword_iron").setItemModel(item -> new itemModelGreatSword(item, dxiimodMain.MOD_ID).setFull3D()).build(new greatswordIron("IRON_GREATSWORD", 12016) );
		greatswordGold = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/greatsword_gold").setItemModel(item -> new itemModelGreatSword(item, dxiimodMain.MOD_ID).setFull3D()).build(new greatswordGold("GOLD_GREATSWORD", 12017) );
		greatswordSteel = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/greatsword_steel").setItemModel(item -> new itemModelGreatSword(item, dxiimodMain.MOD_ID).setFull3D()).build(new greatswordSteel("STEEL_GREATSWORD", 12018) );

	}

}

