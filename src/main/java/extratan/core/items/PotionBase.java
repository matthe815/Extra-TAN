package extratan.core.items;

import extratan.core.ExtraTAN;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPotion;

public class PotionBase extends ItemPotion {
	
	protected String name;
	
	public PotionBase(String name)
	{
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		setCreativeTab(ExtraTAN.creativeTab2);
	}
	
	public void registerItemModel() {
		ExtraTAN.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public PotionBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
}
