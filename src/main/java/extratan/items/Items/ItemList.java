package extratan.items.Items;

import extratan.blocks.TransparentBlockBase;
import extratan.creativetabs.CreativeTabHandler;
import extratan.items.BaseBreakableDrinkableItem;
import extratan.items.BaseDrinkableItem;
import extratan.items.BaseFlask;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ItemList {

	public static final Block TEMPERED_GLASS_BLOCK = new TransparentBlockBase(Material.GLASS, SoundType.GLASS, "temperedGlassBlock", "tempered_glass_block", 0.8f);
	
	public static final Item  EMPTY_FLASK = new BaseFlask("emptyFlask", "empty_flask", CreativeTabHandler.ExtraTANDrinks) {
		public Item GetWarmItem() {
			return HOT_DRINK;
		};
		
		public Item GetColdItem() {
			return COLD_DRINK;
		};
		
		public Item GetNeutralItem() {
			return WATER_FILLED_FLASK;
		};
	};
	
	public static final Item  TEMPERED_EMPTY_FLASK = new BaseFlask("emptyTemperedFlask", "empty_tempered_flask", CreativeTabHandler.ExtraTANDrinks) {
		public Item GetWarmItem() {
			return TEMPERED_HOT_DRINK;
		};
		
		public Item GetColdItem() {
			return TEMPERED_COLD_DRINK;
		};
		
		public Item GetNeutralItem() {
			return TEMPERED_FILLED_FLASK;
		};
	};
	
	public static final Item  WATER_FILLED_FLASK = new BaseDrinkableItem("waterFilledFlask", "filled_flask", CreativeTabHandler.ExtraTANDrinks);
	
	public static final Item  TEMPERED_FILLED_FLASK  = new BaseDrinkableItem("waterFilledFlask", "tempered_filled_flask", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return -4;
		};
		
		public Item GetConsumedItem() {
			return TEMPERED_EMPTY_FLASK;
		};
	};
	
	public static final Item  COLD_DRINK  = new BaseDrinkableItem("coldDrink", "flask_with_cold_water", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return -4;
		};
	};
	
	public static final Item  TEMPERED_COLD_DRINK  = new BaseDrinkableItem("coldDrink", "tempered_flask_with_cold_water", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return -4;
		};
		
		public Item GetConsumedItem() {
			return TEMPERED_EMPTY_FLASK;
		};
	};
	
	public static final Item  HOT_DRINK   = new BaseDrinkableItem("hotDrink", "flask_with_hot_water", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return 4;
		};
	};
	
	public static final Item  TEMPERED_HOT_DRINK  = new BaseDrinkableItem("hotDrink", "tempered_flask_with_hot_water", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return -4;
		};
		
		public Item GetConsumedItem() {
			return TEMPERED_EMPTY_FLASK;
		};
	};
	
	public static final Item  SUPER_HOT_DRINK  = new BaseBreakableDrinkableItem("superHotDrink", "flask_with_super_hot_water", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return 8;
		};
	};
	
	public static final Item  SUPER_HOT_TEMPERED_DRINK  = new BaseDrinkableItem("superHotDrink", "tempered_flask_with_super_hot_water", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return 8;
		};
		
		public Item GetConsumedItem() {
			return TEMPERED_EMPTY_FLASK;
		};
	};
	
	public static final Item  SUPER_COLD_DRINK  = new BaseBreakableDrinkableItem("superColdDrink", "flask_with_super_cold_water", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return -8;
		};
	};
	
	public static final Item  SUPER_COLD_TEMPERED_DRINK  = new BaseDrinkableItem("superColdDrink", "tempered_flask_with_super_cold_water", CreativeTabHandler.ExtraTANDrinks) {
		public int GetTemperatureModifier() {
			return -8;
		};
		
		public Item GetConsumedItem() {
			return TEMPERED_EMPTY_FLASK;
		};
	};
	
}