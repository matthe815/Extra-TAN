package xyz.deltakiruentertainment.mods.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xyz.deltakiruentertainment.mods.ExtraTAN;
import xyz.deltakiruentertainment.mods.items.ModItems;

public class ExtraTab extends CreativeTabs {
	
	public ExtraTab()
	{
		super(ExtraTAN.modId);
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.hotDrink;
	}
}
