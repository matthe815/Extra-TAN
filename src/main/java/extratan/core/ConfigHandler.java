package extratan.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

@Config(modid = "extratan")
public class ConfigHandler {
	@Name("Disable TAN Features")
	@RequiresWorldRestart
	@Comment(value = { "You can set this to true if you'd like to disable Extra TAN features and only keep Harvest Craft features." })
	public static boolean disableTANFeatures = false;
	
	@Name("Thirst Items")
	@RequiresWorldRestart
	@Comment(value = { 
		"Add additional items to affect thirst not included with the mod by default.",
		"To add an item, pop a new value into the array with the format of ITEMID: THIRST",
		"Negative numbers are accepted."
	})
	public static String[] thirstItems = new String[] {"MINECRAFT:APPLE: 2"};
	
	@Name("Use Flint and Steel Recipes")
	@RequiresWorldRestart
	@Comment(value="Whether or not players can use flint and steel to warm their drinks, could be considered OP in some cases.")
	public static Boolean UseFlintandSteelRecipes = false;
}
