package extratan.creativetabs.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtraTANBlocks extends CreativeTabs {

	public ExtraTANBlocks() {
		super("extratan.extraTANBlocks");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getByNameOrId("extratan:tempered_glass_block"));
	}

}
