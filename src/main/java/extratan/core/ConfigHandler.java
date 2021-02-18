package extratan.core;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresWorldRestart;

@Config(modid = "extratan")
public class ConfigHandler {
	@Name("Client Settings")
	public static Client client = new Client();
	
	@Name("Common Settings")
	public static Common common = new Common();
	
	public static class Client {
		
	}
	
	public static class Common {
		@Name("Disable TAN Features")
		@RequiresWorldRestart
		@Comment(value = { "Determines whether or not to disable the main functionality, only leaving HarvestCraft expansions." })
		public boolean disableTANFeatures = false;
		
		@Name("Use Flint and Steel Recipes")
		@RequiresWorldRestart
		@Comment(value= {"Determines whether or not the player can use a Flint and Steel to warm their drink.", "Could be considered overpowered in some cases."})
		public Boolean UseFlintandSteelRecipes = false;
		
		@Name("Disable Enchantments")
		@RequiresWorldRestart
		@Comment(value="Whether or not to disable the enchantment features.")
		public Boolean disableEnchantments = false;
		
		@Name("Thirst Items")
		@RequiresWorldRestart
		@Comment(value = { 
			"Add additional items to affect thirst not included with the mod by default.",
			"To add an item, pop a new value into the array with the format of ITEMID:THIRST",
			"Negative numbers are accepted."
		})
		public String[] thirstItems = new String[] {"MINECRAFT:APPLE:2"};
	}

}
