package extratan.items;

import extratan.core.ExtraTAN;
import lieutenant.registry.RegisterHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBucket;

public class BaseItemBucket extends ItemBucket 
{
	
	public BaseItemBucket(String name, String registryName, CreativeTabs creativeTab)
	{
		super(null);
		
		setUnlocalizedName(ExtraTAN.modId + "." + name)
			.setRegistryName(registryName)
			.setCreativeTab(creativeTab);
		
		RegisterHandler.AddItem(this);
	}
	
}
