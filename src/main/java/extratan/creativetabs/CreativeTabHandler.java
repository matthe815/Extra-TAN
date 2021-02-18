package extratan.creativetabs;

import extratan.items.Items.ItemList;
import lieutenant.tabs.BaseCreativeTab;
import net.minecraft.item.ItemStack;

public class CreativeTabHandler {
	
	public static final BaseCreativeTab ExtraTANDrinks    = new BaseCreativeTab("extratan.extraTANDrinks") {
		public net.minecraft.item.ItemStack getTabIconItem() {
			return new ItemStack(ItemList.EMPTY_FLASK);
		};
	};
	
	public static final BaseCreativeTab ExtraTANBlocks    = new BaseCreativeTab("extratan.extraTANBlocks")  {
		public net.minecraft.item.ItemStack getTabIconItem() {
			return new ItemStack(ItemList.TEMPERED_GLASS_BLOCK);
		};
	};
	
}
