package dxii.dxiimod;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;


public class dxiimodRecipes {

	public static void Initialize(){
		RecipeBuilder.Shaped("dxiimod")
			.setShape("X  ",  "X  ")
			.addInput('X', "minecraft:logs")
			.create("suspItem", new ItemStack(dxiimodItems.suspiciousTotem, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape(" X ",  " X ")
			.addInput('X', "minecraft:logs")
			.create("suspItem2", new ItemStack(dxiimodItems.suspiciousTotem, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("  X",  "  X")
			.addInput('X', "minecraft:logs")
			.create("suspItem3", new ItemStack(dxiimodItems.suspiciousTotem, 1, 0) );


		RecipeBuilder.Shaped("dxiimod")
			.setShape("X  ",  " Z ")
			.addInput('X', Item.ingotIron)
			.addInput('Z', Item.stick)
			.create("throwingDagger", new ItemStack(dxiimodItems.trowingDagger, 2, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("XX ",  "XZ ", "  Z")
			.addInput('X', "minecraft:cobblestones")
			.addInput('Z', Item.stick)
			.create("stoneTomahawk", new ItemStack(dxiimodItems.stoneTomahawk, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("XX ",  "XZ ", "  Z")
			.addInput('X', Item.ingotIron)
			.addInput('Z', Item.stick)
			.create("ironTomahawk", new ItemStack(dxiimodItems.ironTomahawk, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("XX ",  "XZ ", "  Z")
			.addInput('X', Item.ingotGold)
			.addInput('Z', Item.stick)
			.create("goldTomahawk", new ItemStack(dxiimodItems.goldTomahawk, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
			.setShape("XXX",  "XYX", " Y ")
			.addInput('X', "minecraft:cobblestones")
			.addInput('Y', Item.stick)
			.create("stoneHammer", new ItemStack(dxiimodItems.stoneHammer, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("XXX",  "XYX", " Y ")
					.addInput('X', Item.ingotIron)
					.addInput('Y', Item.stick)
					.create("ironHammer", new ItemStack(dxiimodItems.ironHammer, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("XXX",  "XYX", " Y ")
					.addInput('X', Item.ingotGold)
					.addInput('Y', Item.stick)
					.create("goldHammer", new ItemStack(dxiimodItems.goldHammer, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("XXX",  "XYX", " Y ")
					.addInput('X', Item.ingotSteel)
					.addInput('Y', Item.stick)
					.create("steelHammer", new ItemStack(dxiimodItems.steelHammer, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', "minecraft:planks")
					.addInput('Y', Item.stick)
					.create("woodenSpear", new ItemStack(dxiimodItems.woodenSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', "minecraft:cobblestones")
					.addInput('Y', Item.stick)
					.create("stoneSpear", new ItemStack(dxiimodItems.stoneSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', Item.ingotIron)
					.addInput('Y', Item.stick)
					.create("ironSpear", new ItemStack(dxiimodItems.ironSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', Item.ingotGold)
					.addInput('Y', Item.stick)
					.create("goldSpear", new ItemStack(dxiimodItems.goldSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', Item.diamond)
					.addInput('Y', Item.stick)
					.create("diamondSpear", new ItemStack(dxiimodItems.diamondSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape("X  ",  " Y ", "  Y")
					.addInput('X', Item.ingotSteel)
					.addInput('Y', Item.stick)
					.create("steelSpear", new ItemStack(dxiimodItems.steelSpear, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape(" XX",  "XXX", "YX ")
					.addInput('X', "minecraft:cobblestones")
					.addInput('Y', Item.stick)
					.create("stoneGS", new ItemStack(dxiimodItems.greatswordStone, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape(" XX",  "XXX", "YX ")
					.addInput('X', Item.ingotIron)
					.addInput('Y', Item.stick)
					.create("ironGS", new ItemStack(dxiimodItems.greatswordIron, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape(" XX",  "XXX", "YX ")
					.addInput('X', Item.ingotGold)
					.addInput('Y', Item.stick)
					.create("ironGS", new ItemStack(dxiimodItems.greatswordGold, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
					.setShape(" XX",  "XXX", "YX ")
					.addInput('X', Item.ingotSteel)
					.addInput('Y', Item.stick)
					.create("ironGS", new ItemStack(dxiimodItems.greatswordSteel, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
							.setShape(" X ",  "XYX", " X ")
							.addInput('X', Item.leather)
							.addInput('Y', "minecraft:planks")
							.create("buckler", new ItemStack(dxiimodItems.shieldBuckler, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
							.setShape("AC ",  "ABA", " AD")
							.addInput('A', Item.ingotIron)
							.addInput('B', Item.ingotSteel)
							.addInput('C', Item.sulphur)
							.addInput('D', "minecraft:logs")
							.create("flintlock", new ItemStack(dxiimodItems.itemFlintlock, 1, 0) );

		RecipeBuilder.Shapeless("dxiimod")
							.addInput(dxiimodItems.soulpeace)
							.addInput(dxiimodItems.soulpeace)
							.addInput(dxiimodItems.soulpeace)
							.addInput(dxiimodItems.soulpeace)
							.create("greatsoulpeace", new ItemStack(dxiimodItems.soulpeace_great, 1, 0) );

		RecipeBuilder.Shapeless("dxiimod")
							.addInput(dxiimodItems.soulevil)
							.addInput(dxiimodItems.soulevil)
							.addInput(dxiimodItems.soulevil)
							.addInput(dxiimodItems.soulevil)
							.create("greatsouldark", new ItemStack(dxiimodItems.soulevil_great, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
							.setShape(" D ",  "DXD", " D ")
							.addInput('D', dxiimodItems.soulpeace_great)
							.addInput('X', dxiimodItems.sacrificering_broken)
							.create("sacrificering_repair", new ItemStack(dxiimodItems.sacrificering, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
							.setShape(" Z ",  "YXY", " Y ")
							.addInput('X', dxiimodItems.soulpeace)
							.addInput('Y', Item.featherChicken)
							.addInput('Z', Item.string)
							.create("featherfall_necklace", new ItemStack(dxiimodItems.featherfall, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
							.setShape("XX ",  "XY ", "  Y")
							.addInput('X', Item.ingotIron)
							.addInput('Y', Item.rope)
							.create("ghook", new ItemStack(dxiimodItems.ghook, 1, 0) );

		RecipeBuilder.Shaped("dxiimod")
							.setShape(" Y ",  "YXY", " Z ")
							.addInput('X', Item.ingotIron)
							.addInput('Y', Item.paper)
							.addInput('Z', Item.sulphur)
							.create("bulletiron", new ItemStack(dxiimodItems.bullet_iron, 4, 0) );

		RecipeBuilder.Shaped("dxiimod")
							.setShape(" Y ",  "YXY", " Z ")
							.addInput('X', Item.ingotSteel)
							.addInput('Y', Item.paper)
							.addInput('Z', Item.sulphur)
							.create("bulletsteel", new ItemStack(dxiimodItems.bullet_steel, 4, 0) );


	}

	public static void InitNamespaces(){
		RecipeBuilder.initNameSpace(dxiimodMain.MOD_ID);
	}

}
