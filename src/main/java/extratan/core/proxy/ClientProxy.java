package extratan.core.proxy;

import extratan.core.ExtraTAN;
import extratan.core.items.DrinkBase;
import extratan.core.items.FlaskBase;
import extratan.core.items.ItemBase;
import extratan.core.items.PotionBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	public ClientProxy() {
		// TODO Auto-generated constructor stub
	}
	
	public void registerItemRenderer(DrinkBase item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ExtraTAN.modId + ":" + id, "inventory"));
	}

	public void registerItemRenderer(ItemBase item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ExtraTAN.modId + ":" + id, "inventory"));
	}
	
	public void registerItemRenderer(PotionBase item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ExtraTAN.modId + ":" + id, "inventory"));
	}
	
	public void registerItemRenderer(FlaskBase item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ExtraTAN.modId + ":" + id, "inventory"));
	}

}
