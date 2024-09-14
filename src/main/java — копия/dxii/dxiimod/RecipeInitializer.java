package dxii.dxiimod;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import dxii.dxiimod.item.modItems;


public class RecipeInitializer {

	public static void Initialize(){
		RecipeBuilder.Shaped("dxiimod")
			.setShape("X  ",  " Z ")
			.addInput('X', Item.ammoPebble)
			.addInput('Z', Item.stick)
			.create("stoneDaggerCS", new ItemStack(modItems.stoneDagger, 2, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("XX ",  "XZ ", "  Z")
			.addInput('X', "minecraft:cobblestones")
			.addInput('Z', Item.stick)
			.create("stoneTomahawk", new ItemStack(modItems.stoneTomahawk, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("XX ",  "XZ ", "  Z")
			.addInput('X', Item.ingotIron)
			.addInput('Z', Item.stick)
			.create("ironTomahawk", new ItemStack(modItems.ironTomahawk, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("XX ",  "XZ ", "  Z")
			.addInput('X', Item.ingotGold)
			.addInput('Z', Item.stick)
			.create("goldTomahawk", new ItemStack(modItems.goldTomahawk, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("XZ ",  "XY ", "  Z")
			.addInput('X', Item.diamond)
			.addInput('Y', Item.nethercoal)
			.addInput('Z', Item.ingotSteel)
			.create("diamondTomahawk", new ItemStack(modItems.diamondTomahawk, 1, 0) );


	}

	public static void InitNamespaces(){
		RecipeBuilder.initNameSpace(dxiimodMain.MOD_ID);
	}

}
