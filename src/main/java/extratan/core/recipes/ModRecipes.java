package extratan.core.recipes;

import extratan.core.items.ModItems;
import lieutenant.api.recipe.RecipeManager;
import net.minecraft.init.Blocks;
import toughasnails.api.item.*;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init() {
		RecipeManager.registerSmelting(Items.GLASS_BOTTLE, new ItemStack(ModItems.emptyFlask), 0);
		RecipeManager.registerSmelting(ModItems.waterDrink, new ItemStack(ModItems.hotDrink), 0);
		RecipeManager.registerSmelting(ModItems.hotDrink, new ItemStack(ModItems.superHotDrink), 0);
		RecipeManager.registerShapeless(new ItemStack(ModItems.emptyFlask), Items.GLASS_BOTTLE, Items.FLINT_AND_STEEL);
		RecipeManager.registerShapeless(new ItemStack(ModItems.waterDrink), ModItems.emptyFlask, Items.WATER_BUCKET);
		RecipeManager.registerShapeless(new ItemStack(ModItems.superHotDrink), ModItems.waterDrink, Items.LAVA_BUCKET);
		RecipeManager.registerShapeless(new ItemStack(ModItems.hotDrink), ModItems.waterDrink, Items.FLINT, Items.STICK);
		RecipeManager.registerShapeless(new ItemStack(ModItems.hotDrink), ModItems.waterDrink, Items.FLINT_AND_STEEL);
		RecipeManager.registerShapeless(new ItemStack(ModItems.coolDrink), ModItems.waterDrink, Blocks.ICE);
		RecipeManager.registerShapeless(new ItemStack(ModItems.coolDrink), ModItems.waterDrink, TANItems.ice_cube);
		RecipeManager.registerShapeless(new ItemStack(ModItems.superCoolDrink), ModItems.coolDrink, TANItems.ice_cube, TANItems.ice_cube, TANItems.ice_cube);
		RecipeManager.registerShapeless(new ItemStack(Blocks.ICE), TANItems.ice_cube, TANItems.ice_cube, TANItems.ice_cube, TANItems.ice_cube, TANItems.ice_cube, TANItems.ice_cube, TANItems.ice_cube, TANItems.ice_cube, TANItems.ice_cube);
		RecipeManager.registerShapeless(new ItemStack(ModItems.superCoolDrink), ModItems.waterDrink, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE, Blocks.ICE);
		RecipeManager.registerShapeless(new ItemStack(ModItems.superCoolDrink), ModItems.waterDrink, Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.PACKED_ICE);
	}

}
