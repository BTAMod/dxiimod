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
			.setShape("XXX",  "XYX", " Y ")
			.addInput('X', "minecraft:cobblestones")
			.addInput('Y', Item.stick)
			.create("stoneHammer", new ItemStack(modItems.stoneHammer, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("XXX",  "XYX", " Y ")
					.addInput('X', Item.ingotIron)
					.addInput('Y', Item.stick)
					.create("ironHammer", new ItemStack(modItems.ironHammer, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("XXX",  "XYX", " Y ")
					.addInput('X', Item.ingotGold)
					.addInput('Y', Item.stick)
					.create("goldHammer", new ItemStack(modItems.goldHammer, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("XXX",  "XYX", " Y ")
					.addInput('X', Item.ingotSteel)
					.addInput('Y', Item.stick)
					.create("steelHammer", new ItemStack(modItems.steelHammer, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', "minecraft:planks")
					.addInput('Y', Item.stick)
					.create("woodenSpear", new ItemStack(modItems.woodenSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', "minecraft:cobblestones")
					.addInput('Y', Item.stick)
					.create("stoneSpear", new ItemStack(modItems.stoneSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', Item.ingotIron)
					.addInput('Y', Item.stick)
					.create("ironSpear", new ItemStack(modItems.ironSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', Item.ingotGold)
					.addInput('Y', Item.stick)
					.create("goldSpear", new ItemStack(modItems.goldSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', Item.diamond)
					.addInput('Y', Item.stick)
					.create("diamondSpear", new ItemStack(modItems.diamondSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', Item.ingotSteel)
					.addInput('Y', Item.stick)
					.create("steelSpear", new ItemStack(modItems.steelSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape(" XX",  "XXX", "YX ")
					.addInput('X', "minecraft:cobblestones")
					.addInput('Y', Item.stick)
					.create("stoneGS", new ItemStack(modItems.greatswordStone, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape(" XX",  "XXX", "YX ")
					.addInput('X', Item.ingotIron)
					.addInput('Y', Item.stick)
					.create("ironGS", new ItemStack(modItems.greatswordIron, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape(" XX",  "XXX", "YX ")
					.addInput('X', Item.ingotGold)
					.addInput('Y', Item.stick)
					.create("ironGS", new ItemStack(modItems.greatswordGold, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape(" XX",  "XXX", "YX ")
					.addInput('X', Item.ingotSteel)
					.addInput('Y', Item.stick)
					.create("ironGS", new ItemStack(modItems.greatswordSteel, 1, 0) );


	}

	public static void InitNamespaces(){
		RecipeBuilder.initNameSpace(dxiimodMain.MOD_ID);
	}

}
