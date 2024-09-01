package dxii.dxiimod.item;

import dxii.dxiimod.dxiimodMain;
import turniplabs.halplibe.helper.ItemBuilder;

public class modItems{
	public static stoneDaggerItem stoneDagger;
	public static stoneTomahawkItem stoneTomahawk;
	public static ironTomahawkItem ironTomahawk;
	public static goldTomahawkItem goldTomahawk;
	public static diamondTomahawkItem diamondTomahawk;

	// Function that make the items
	public void Initialize() {
		stoneDagger = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/dagger").build(new stoneDaggerItem("STONE_DAGGER", 12000) );
		stoneTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_stone").build(new stoneTomahawkItem("STONE_TOMAHAWK", 12001) );
		ironTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_iron").build(new ironTomahawkItem("IRON_TOMAHAWK", 12002) );
		goldTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_gold").build(new goldTomahawkItem("GOLD_TOMAHAWK", 12003) );
		diamondTomahawk = new ItemBuilder(dxiimodMain.MOD_ID).setIcon(dxiimodMain.MOD_ID + ":item/tomahawk_diamond").build(new diamondTomahawkItem("DIAMOND_TOMAHAWK", 12004) );

	}

}

