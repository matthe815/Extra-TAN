package extratan.core.init;

import extratan.blocks.Blocks.TemperedGlassBlock;
import extratan.items.Items.AppleJuice;
import extratan.items.Items.ColdDrink;
import extratan.items.Items.ColdDrinkTempered;
import extratan.items.Items.EmptyFlask;
import extratan.items.Items.EmptyTemperedFlask;
import extratan.items.Items.HotDrink;
import extratan.items.Items.HotDrinkTempered;
import extratan.items.Items.Lemonade;
import extratan.items.Items.LiquidInfuser;
import extratan.items.Items.OrangeJuice;
import extratan.items.Items.SuperColdDrink;
import extratan.items.Items.SuperColdDrinkTempered;
import extratan.items.Items.SuperHotDrink;
import extratan.items.Items.SuperHotDrinkTempered;
import extratan.items.Items.WaterFilledFlask;
import extratan.items.Items.WaterFilledTemperedFlask;
import lieutenant.registry.RegisterHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemInit {

	public static void Init() {
		AddItems(new Item[] {
			new EmptyFlask(),
			new EmptyTemperedFlask(),
			new ColdDrink(),
			new HotDrink(),
			new SuperHotDrink(),
			new SuperColdDrink(),
			new LiquidInfuser(),
			new WaterFilledFlask(),
			new WaterFilledTemperedFlask(),
			new ColdDrinkTempered(),
			new SuperColdDrinkTempered(),
			new HotDrinkTempered(),
			new SuperHotDrinkTempered(),
			new AppleJuice(),
			new Lemonade(),
			new OrangeJuice()
		});
			
		AddBlocks(new Block[] {
			new TemperedGlassBlock()	
		});
	}
	
	/**
	 * Add a group of items.
	 */
	public static void AddItems(Item[] items)
	{
		for (Item item : items)
			RegisterHandler.AddItem(item);
	}
	
	/**
	 * Add a group of blocks.
	 */
	public static void AddBlocks(Block[] blocks)
	{
		for (Block block : blocks)
			RegisterHandler.AddBlock(block);
	}

}
