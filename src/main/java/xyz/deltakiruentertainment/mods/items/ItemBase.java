package xyz.deltakiruentertainment.mods.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xyz.deltakiruentertainment.mods.ExtraTAN;

public class ItemBase extends Item {
	
	protected String name;
	
	public ItemBase(String name)
	{
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		setCreativeTab(ExtraTAN.creativeTab);
	}
	
	public void registerItemModel() {
		ExtraTAN.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
