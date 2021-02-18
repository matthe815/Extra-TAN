package extratan.items;

import net.minecraft.item.Item;
import extratan.core.ExtraTAN;
import lieutenant.registry.RegisterHandler;
import net.minecraft.creativetab.CreativeTabs;

public class BaseItem extends Item {
	public BaseItem(String name, String registryName, CreativeTabs creativeTab) {
		this.setUnlocalizedName(ExtraTAN.modId + "." + name)
			.setRegistryName(registryName)
			.setCreativeTab(creativeTab)
			.setMaxDamage(5);
		
		RegisterHandler.AddItem(this);
	}
}
