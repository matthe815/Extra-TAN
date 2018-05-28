package xyz.deltakiruentertainment.mods.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.deltakiruentertainment.mods.items.ModItems;

public class ModRecipes {
	
	public static void init() {
		GameRegistry.addSmelting(Items.GLASS_BOTTLE, new ItemStack(ModItems.emptyFlask), 0);
		GameRegistry.addSmelting(ModItems.waterDrink, new ItemStack(ModItems.hotDrink), 0);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.emptyFlask), Items.GLASS_BOTTLE, Items.FLINT_AND_STEEL);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.waterDrink), ModItems.emptyFlask, Items.WATER_BUCKET);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superHotDrink), ModItems.waterDrink, Items.LAVA_BUCKET);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.hotDrink), ModItems.waterDrink, Items.FLINT, Items.STICK);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.hotDrink), ModItems.waterDrink, Items.FLINT_AND_STEEL);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.coolDrink), ModItems.waterDrink, Blocks.ICE);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superCoolDrink), ModItems.waterDrink, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superCoolDrink), ModItems.waterDrink, Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.PACKED_ICE);
	}

}
