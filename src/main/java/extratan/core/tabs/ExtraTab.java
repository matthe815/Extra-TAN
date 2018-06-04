package extratan.core.tabs;

import extratan.core.ExtraTAN;
import extratan.core.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

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
