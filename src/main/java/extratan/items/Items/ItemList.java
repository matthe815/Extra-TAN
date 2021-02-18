package extratan.items.Items;

import extratan.blocks.TransparentBlockBase;
import extratan.creativetabs.CreativeTabHandler;
import extratan.items.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ItemList {

	public static final Block TEMPERED_GLASS_BLOCK = new TransparentBlockBase(Material.GLASS, SoundType.GLASS, "temperedGlassBlock", "tempered_glass_block", 0.8f);
	
	public static final Item  EMPTY_FLASK = new BaseItem("emptyFlask", "empty_flask", CreativeTabHandler.ExtraTANDrinks);
	
}
