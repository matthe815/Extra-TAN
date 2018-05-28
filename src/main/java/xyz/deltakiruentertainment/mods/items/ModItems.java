package xyz.deltakiruentertainment.mods.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static ItemBase emptyFlask;
	public static DrinkBase waterDrink;
	public static DrinkBase coolDrink;
	public static DrinkBase hotDrink;
	public static DrinkBase superCoolDrink;
	public static DrinkBase superHotDrink;
	public static PotionBase potionFlask;
	
	public static void init() {
		emptyFlask = register(new ItemBase("emptyFlask"));
		waterDrink = register(new DrinkBase("waterDrink", 0));
		coolDrink = register(new DrinkBase("coolDrink", -3));
		hotDrink = register(new DrinkBase("hotDrink", 3));
		superCoolDrink = register(new DrinkBase("superCoolDrink", -7));
		superHotDrink = register(new DrinkBase("superHotDrink", 7));
		potionFlask = register(new PotionBase("emptyPotionFlask"));
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof DrinkBase) {
			((DrinkBase)item).registerItemModel();
		}
		
		if (item instanceof ItemBase) {
			((ItemBase)item).registerItemModel();
		}
		
		if (item instanceof PotionBase) {
			((PotionBase)item).registerItemModel();
		}

		return item;
	}

}