package extratan.core;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import extratan.core.init.PotionInit;
import extratan.enchantments.ColdResistanceEnchantment;
import extratan.enchantments.HeatResistanceEnchantment;
import extratan.gui.handlers.HUDHandler;
import extratan.gui.handlers.SyncHandler;
import extratan.items.Items.ItemList;
import extratan.lootfunctions.ApplyRandomTempProt;
import lieutenant.core.Lieutenant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction.Serializer;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ExtraTAN.modId, version = ExtraTAN.version, name = ExtraTAN.name, dependencies = ExtraTAN.dependencies, guiFactory="extratan.gui.GUIFactory" )
public class ExtraTAN {

	public static final String modId = "extratan";
	public static final String name = "Extra TAN";
	public static final String version = "6";
	public static final String dependencies = "required-after:toughasnails;required-after:lieutenant@1.5;after:harvestcraft";

	@Mod.Instance(modId)
	public static ExtraTAN instance;

	@Mod.Instance("lieutenant")
	public static Lieutenant lieutenant;
	
	public static float lastExhaustion;
	
	public static ItemList list;
	
	public static Map<EntityPlayer, Float> lastExhaustions = new HashMap<EntityPlayer, Float>();
	
	public static HeatResistanceEnchantment heat_resistance = new HeatResistanceEnchantment();
	public static ColdResistanceEnchantment cold_resistance = new ColdResistanceEnchantment();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		System.out.println(modId + " is pre-loading!");
		
		// If the user has disabled TAN features, then ignore the rest of setup.
		if (ConfigHandler.common.disableTANFeatures) return;
		
		list = new ItemList();
		PotionInit.Init();
		
		ForgeRegistries.ENCHANTMENTS.register(heat_resistance);
		ForgeRegistries.ENCHANTMENTS.register(cold_resistance);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) 
	{
		System.out.println(modId + " is loading!");
		
		// Also skip initalization if the user has disabled core functionality.
		if (ConfigHandler.common.disableTANFeatures) return;
		
		GameRegistry.addSmelting(ItemList.WATER_FILLED_FLASK, new ItemStack(ItemList.HOT_DRINK), 0);
		GameRegistry.addSmelting(ItemList.COLD_DRINK, new ItemStack(ItemList.WATER_FILLED_FLASK), 0);
		
		// Add the new Flint and Steel recipes if its' enabled.
		if (ConfigHandler.common.UseFlintandSteelRecipes) {
			GameRegistry.addShapelessRecipe(new ResourceLocation(ExtraTAN.modId+"flintsteelheat"), null, new ItemStack(ItemList.HOT_DRINK), new Ingredient[] {Ingredient.fromItem(ItemList.WATER_FILLED_FLASK), Ingredient.fromItem(Items.FLINT_AND_STEEL)});
			GameRegistry.addShapelessRecipe(new ResourceLocation(ExtraTAN.modId+"flintsteelheat2"), null, new ItemStack(ItemList.WATER_FILLED_FLASK), new Ingredient[] {Ingredient.fromItem(ItemList.COLD_DRINK), Ingredient.fromItem(Items.FLINT_AND_STEEL)});
		}
		
		SyncHandler.init(); // Initialize the packet synchronizer.
		
		// Initalize the HUD (only if on the client side, this is ignored on the server)
		if (event.getSide() == Side.CLIENT) HUDHandler.init();
		
		// Register the loot functionality.
		LootFunctionManager.registerFunction(new Serializer<ApplyRandomTempProt>(new ResourceLocation("extratan:apply_random_temp_prot"), ApplyRandomTempProt.class) {

			@Override
			public ApplyRandomTempProt deserialize(JsonObject object, JsonDeserializationContext content, LootCondition[] conditionsIn) {
				return new ApplyRandomTempProt();
			}

			@Override
			public void serialize(JsonObject object, ApplyRandomTempProt functionClazz, JsonSerializationContext content) {}
			
		});
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		System.out.println(modId + " is post-loading!");
		
		MinecraftForge.EVENT_BUS.register(new EventEngine());
	}
}