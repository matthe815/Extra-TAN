package extratan.core;

import extratan.core.items.ModItems;
import extratan.core.proxy.CommonProxy;
import extratan.core.recipes.ModRecipes;
import extratan.core.tabs.ExtraTab;
import extratan.core.tabs.PotionTab;
import lieutenant.api.tab.TabManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExtraTAN.modId, version = "1.0", acceptedMinecraftVersions = "[1.10,1.12.2]", name = ExtraTAN.name, dependencies = ExtraTAN.dependencies)
public class ExtraTAN { 
	
	public static final String modId = "extratan";
	public static final String name = "Extra TAN";
	public static final String dependencies = "required-after:toughasnails;required-after:lieutenant";

	@Mod.Instance(modId)
	public static ExtraTAN instance;
	
	public static final CreativeTabs creativeTab = TabManager.registerTab(new ExtraTab());
	public static final CreativeTabs creativeTab2 = TabManager.registerTab(new PotionTab());
	
	@SidedProxy(serverSide = "extratan.core.proxy.CommonProxy", clientSide = "extratan.core.proxy.ClientProxy")
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