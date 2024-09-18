package dxii.dxiimod.item;

import dxii.dxiimod.dxiimodMain;
import dxii.dxiimod.render.itemModelGreatSword;
import dxii.dxiimod.render.itemModelSpear;
import dxii.dxiimod.render.itemModelSwing;
import net.minecraft.client.render.item.model.ItemModelStandard;
import turniplabs.halplibe.helper.ItemBuilder;

public class modItems{
	public static itemSuspiciousTotem suspiciousTotem;
	public static bucklerItem shieldBuckler;

	public static throwingDaggerItem trowingDagger;

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

		trowingDagger = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/dagger").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new throwingDaggerItem("THROWING_DAGGER", dxiimodMain.ITEM_ID++) );

		stoneTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_stone").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new tomahawkStoneItem("STONE_TOMAHAWK", dxiimodMain.ITEM_ID++) );
		ironTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_iron").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new tomahawkIronItem("IRON_TOMAHAWK", dxiimodMain.ITEM_ID++) );
		goldTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_gold").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new tomahawkGoldItem("GOLD_TOMAHAWK", dxiimodMain.ITEM_ID++) );
		diamondTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_diamond").setItemModel(item -> new ItemModelStandard(item, dxiimodMain.MOD_ID).setFull3D()).build(new tomahawkDiamondItem("DIAMOND_TOMAHAWK", dxiimodMain.ITEM_ID++) );

		stoneHammer = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/hammer_stone").setItemModel(item -> new itemModelSwing(item, dxiimodMain.MOD_ID).setFull3D()).build(new hammerStoneItem("STONE_HAMMER", dxiimodMain.ITEM_ID++) );
		ironHammer = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/hammer_iron").setItemModel(item -> new itemModelSwing(item, dxiimodMain.MOD_ID).setFull3D()).build(new hammerIronItem("IRON_HAMMER", dxiimodMain.ITEM_ID++) );
		goldHammer = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/hammer_gold").setItemModel(item -> new itemModelSwing(item, dxiimodMain.MOD_ID).setFull3D()).build(new hammerGolditem("GOLD_HAMMER", dxiimodMain.ITEM_ID++) );
		steelHammer = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/hammer_steel").setItemModel(item -> new itemModelSwing(item, dxiimodMain.MOD_ID).setFull3D()).build(new hammerSteelItem("STEEL_HAMMER", dxiimodMain.ITEM_ID++) );

		woodenSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_wood").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearWooden("WOOD_SPEAR", dxiimodMain.ITEM_ID++) );
		stoneSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_stone").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearStone("STONE_SPEAR", dxiimodMain.ITEM_ID++) );
		ironSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_iron").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearIron("IRON_SPEAR", dxiimodMain.ITEM_ID++) );
		goldSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_gold").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearGold("GOLD_SPEAR", dxiimodMain.ITEM_ID++) );
		diamondSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_diamond").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearDiamond("DIAMOND_SPEAR", dxiimodMain.ITEM_ID++) );
		steelSpear = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/spear_steel").setItemModel(item -> new itemModelSpear(item, dxiimodMain.MOD_ID).setFull3D()).build(new spearSteel("STEEL_SPEAR", dxiimodMain.ITEM_ID++) );

		greatswordStone = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/greatsword_stone").setItemModel(item -> new itemModelGreatSword(item, dxiimodMain.MOD_ID).setFull3D()).build(new greatswordStone("STONE_GREATSWORD", dxiimodMain.ITEM_ID++) );
		greatswordIron = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/greatsword_iron").setItemModel(item -> new itemModelGreatSword(item, dxiimodMain.MOD_ID).setFull3D()).build(new greatswordIron("IRON_GREATSWORD", dxiimodMain.ITEM_ID++) );
		greatswordGold = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/greatsword_gold").setItemModel(item -> new itemModelGreatSword(item, dxiimodMain.MOD_ID).setFull3D()).build(new greatswordGold("GOLD_GREATSWORD", dxiimodMain.ITEM_ID++) );
		greatswordSteel = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/greatsword_steel").setItemModel(item -> new itemModelGreatSword(item, dxiimodMain.MOD_ID).setFull3D()).build(new greatswordSteel("STEEL_GREATSWORD", dxiimodMain.ITEM_ID++) );


		suspiciousTotem = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/susp_totem").build(new itemSuspiciousTotem("SUSP_TOTEM", dxiimodMain.ITEM_ID++) );

		shieldBuckler = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/buckler").build(new bucklerItem("BUCKLER", dxiimodMain.ITEM_ID++) );
	}

}

