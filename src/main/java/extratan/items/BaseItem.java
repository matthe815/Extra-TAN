package extratan.items;

import net.minecraft.item.Item;
import extratan.core.ExtraTAN;
import net.minecraft.creativetab.CreativeTabs;

public class BaseItem extends Item 
{
	
		public BaseItem(String name, String registryName, CreativeTabs creativeTab)
		{
			this.setUnlocalizedName(ExtraTAN.modId + "." + name);
			this.setRegistryName(registryName);
			this.setCreativeTab(creativeTab);
			this.setMaxDamage(5);
		}

}
