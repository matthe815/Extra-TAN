package xyz.deltakiruentertainment.mods.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xyz.deltakiruentertainment.mods.ExtraTAN;
import xyz.deltakiruentertainment.mods.items.ModItems;

public class PotionTab extends CreativeTabs {
	
	public PotionTab()
	{
		super("potionTab");
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.potionFlask;
	}
}
