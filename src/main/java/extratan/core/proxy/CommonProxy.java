package extratan.core.proxy;

import extratan.core.items.DrinkBase;
import extratan.core.items.FlaskBase;
import extratan.core.items.ItemBase;
import extratan.core.items.PotionBase;

public class CommonProxy {

	public CommonProxy() {
		// TODO Auto-generated constructor stub
	}

	public void registerItemRenderer(DrinkBase item, int meta, String id) {}

	public void registerItemRenderer(ItemBase item, int meta, String id) {}
	
	public void registerItemRenderer(PotionBase item, int meta, String id) {}
	
	public void registerItemRenderer(FlaskBase item, int meta, String id) {}

}
