package extratan.core.items;

import lieutenant.api.item.ItemManager;
import net.minecraft.item.Item;

public class ModItems {

	public static final ItemManager MANAGER = new ItemManager();
	public static FlaskBase emptyFlask;
	public static DrinkBase waterDrink;
	public static DrinkBase coolDrink;
	public static DrinkBase hotDrink;
	public static DrinkBase superCoolDrink;
	public static DrinkBase superHotDrink;
	
	public static void init() {
		emptyFlask = register(new FlaskBase("emptyFlask"));
		waterDrink = register(new DrinkBase("waterDrink", 0));
		coolDrink = register(new DrinkBase("coolDrink", -3));
		hotDrink = register(new DrinkBase("hotDrink", 3));
		superCoolDrink = register(new DrinkBase("superCoolDrink", -7));
		superHotDrink = register(new DrinkBase("superHotDrink", 7));
	}

	private static <T extends Item> T register(T item) {
		MANAGER.register(item);

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