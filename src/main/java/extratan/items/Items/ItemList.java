package extratan.items.Items;

import extratan.blocks.TransparentBlockBase;
import extratan.creativetabs.CreativeTabHandler;
import extratan.items.BaseDrinkableItem;
import extratan.items.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ItemList {

	public static final Block TEMPERED_GLASS_BLOCK = new TransparentBlockBase(Material.GLASS, SoundType.GLASS, "temperedGlassBlock", "tempered_glass_block", 0.8f);
	
	public static final Item  EMPTY_FLASK = new BaseItem("emptyFlask", "empty_flask", CreativeTabHandler.ExtraTANDrinks);
	
	public static final Item  COLD_DRINK  = new BaseDrinkableItem("coldDrink", "flask_with_cold_water", CreativeTabHandler.ExtraTANDrinks); 
	
	public static final Item  HOT_DRINK   = new BaseDrinkableItem("hotDrink", "flask_with_hot_water", CreativeTabHandler.ExtraTANDrinks); 
	
	public static final Item  SUPER_HOT_DRINK  = new BaseDrinkableItem("superHotDrink", "flask_with_super_hot_water", CreativeTabHandler.ExtraTANDrinks); 
	
	public static final Item  SUPER_COLD_DRINK  = new BaseDrinkableItem("superColdDrink", "flask_with_super_cold_water", CreativeTabHandler.ExtraTANDrinks); 
	
}