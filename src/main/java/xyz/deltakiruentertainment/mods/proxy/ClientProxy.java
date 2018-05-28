package xyz.deltakiruentertainment.mods.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import xyz.deltakiruentertainment.mods.ExtraTAN;
import xyz.deltakiruentertainment.mods.items.DrinkBase;
import xyz.deltakiruentertainment.mods.items.ItemBase;
import xyz.deltakiruentertainment.mods.items.PotionBase;

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

}
