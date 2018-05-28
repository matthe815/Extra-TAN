package xyz.deltakiruentertainment.mods;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.deltakiruentertainment.mods.items.ModItems;
import xyz.deltakiruentertainment.mods.proxy.CommonProxy;
import xyz.deltakiruentertainment.mods.recipes.ModRecipes;
import xyz.deltakiruentertainment.mods.tabs.ExtraTab;
import xyz.deltakiruentertainment.mods.tabs.PotionTab;

@Mod(modid = ExtraTAN.modId, version = "1.0", name = ExtraTAN.name, dependencies = "required-after:ToughAsNails")
public class ExtraTAN {

	public static final String modId = "extratan";
	public static final String name = "Extra TAN";

	@Mod.Instance(modId)
	public static ExtraTAN instance;
	
	public static final ExtraTab creativeTab = new ExtraTab();
	public static final PotionTab creativeTab2 = new PotionTab();
	
	@SidedProxy(serverSide = "xyz.deltakiruentertainment.mods.proxy.CommonProxy", clientSide = "xyz.deltakiruentertainment.mods.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(modId + " is loading!");
		ModItems.init();
		ModRecipes.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}