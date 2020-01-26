package extratan.core;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import extratan.core.init.ItemInit;
import extratan.core.init.PotionInit;
import extratan.enchantments.ColdResistanceEnchantment;
import extratan.enchantments.HeatResistanceEnchantment;
import extratan.gui.handlers.HUDHandler;
import extratan.gui.handlers.SyncHandler;
import extratan.lootfunctions.ApplyRandomTempProt;
import extratan.proxy.CommonProxy;
import lieutenant.core.Lieutenant;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction.Serializer;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;

@Mod(modid = ExtraTAN.modId, version = ExtraTAN.version, name = ExtraTAN.name, dependencies = ExtraTAN.dependencies, guiFactory="extratan.gui.GUIFactory" )
public class ExtraTAN {

	public static final String modId = "extratan";
	public static final String name = "Extra TAN";
	public static final String version = "5.2.158";
	public static final String dependencies = "required-after:toughasnails;required-after:lieutenant@1.5;after:harvestcraft";

	@Mod.Instance(modId)
	public static ExtraTAN instance;

	@Mod.Instance("lieutenant")
	public static Lieutenant lieutenant;
	
	@SidedProxy(serverSide = "extratan.proxy.CommonProxy", clientSide = "extratan.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	public static float lastExhaustion;
	
	public static Map<EntityPlayer, Float> lastExhaustions = new HashMap<EntityPlayer, Float>();
	
	public static HeatResistanceEnchantment heat_resistance = new HeatResistanceEnchantment();
	public static ColdResistanceEnchantment cold_resistance = new ColdResistanceEnchantment();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		System.out.println(modId + " is pre-loading!");
		
		if (ConfigHandler.disableTANFeatures)
			return;
		
		ItemInit.Init();
		PotionInit.Init();
		
		ForgeRegistries.ENCHANTMENTS.register(heat_resistance);
		ForgeRegistries.ENCHANTMENTS.register(cold_resistance);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) 
	{
		System.out.println(modId + " is loading!");
		
		if (ConfigHandler.disableTANFeatures)
			return;
		
		GameRegistry.addSmelting(Item.getByNameOrId("extratan:filled_flask"), new ItemStack(Item.getByNameOrId("extratan:flask_with_hot_water")), 0);
		GameRegistry.addSmelting(Item.getByNameOrId("extratan:flask_with_cold_water"), new ItemStack(Item.getByNameOrId("extratan:filled_flask")), 0);
		
		if (ConfigHandler.UseFlintandSteelRecipes) {
			GameRegistry.addShapelessRecipe(new ResourceLocation(ExtraTAN.modId+"flintsteelheat"), null, new ItemStack(Item.getByNameOrId("extratan:flask_with_hot_water")), new Ingredient[] {Ingredient.fromItem(Item.getByNameOrId("extratan:filled_flask")), Ingredient.fromItem(Items.FLINT_AND_STEEL)});
			GameRegistry.addShapelessRecipe(new ResourceLocation(ExtraTAN.modId+"flintsteelheat2"), null, new ItemStack(Item.getByNameOrId("extratan:filled_flask")), new Ingredient[] {Ingredient.fromItem(Item.getByNameOrId("extratan:flask_with_cold_water")), Ingredient.fromItem(Items.FLINT_AND_STEEL)});
		}
		
		SyncHandler.init();
		
		if (event.getSide() == Side.CLIENT)
			HUDHandler.init();
		
		LootFunctionManager.registerFunction(new Serializer<ApplyRandomTempProt>(new ResourceLocation("extratan:apply_random_temp_prot"), ApplyRandomTempProt.class) {

			@Override
			public ApplyRandomTempProt deserialize(JsonObject object, JsonDeserializationContext deserializationContext,
					LootCondition[] conditionsIn) {
				return new ApplyRandomTempProt();
			}

			@Override
			public void serialize(JsonObject object, ApplyRandomTempProt functionClazz,
					JsonSerializationContext serializationContext) {}
			
		});
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		System.out.println(modId + " is post-loading!");
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent evt) {
	    if (evt.getName().toString().equals("minecraft:chests/simple_dungeon") || evt.getName().toString().equals("minecraft:chests/spawn_bonus_chest") || evt.getName().toString().equals("minecraft:chests/abandoned_mineshaft")) {
	    	LootEntry entry = new LootEntryTable(new ResourceLocation("extratan:inject/simple_dungeon"), 100, 1, new LootCondition[] {}, "minecraft:enchanted_book");
	    	LootPool pool = new LootPool(new LootEntry[]{entry}, new LootCondition[] {}, new RandomValueRange(10), new RandomValueRange(5), "main_pool");

	    	evt.getTable().addPool(pool);
	    }
	}
	
	@SubscribeEvent
	public void livingUpdate(PlayerTickEvent event) 
	{
		if (!(event.player instanceof EntityPlayer))
			return;
		
		EntityPlayer player = event.player;
		boolean hasBuff = false;
		int buff1Level = 1;
		
		boolean hasBuff2 = false;
		int buff2Level = 1;
		
		for (ItemStack item : player.getArmorInventoryList()) {
            NBTTagList tagList = item.getEnchantmentTagList();
            
            for (int i = 0; i < tagList.tagCount(); i++)
            {
                NBTTagCompound idTag = tagList.getCompoundTagAt(i);
                
                if (idTag.getShort("id") == Enchantment.getEnchantmentID(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("extratan:heat_resistance")))) {
                    hasBuff = true;
                    buff1Level = EnchantmentHelper.getMaxEnchantmentLevel(heat_resistance, player);
                }
                
                if (idTag.getShort("id") == Enchantment.getEnchantmentID(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("extratan:cold_resistance")))) {
                    hasBuff2 = true;
                    buff2Level = EnchantmentHelper.getMaxEnchantmentLevel(cold_resistance, player);
                }
            }
		}
		 
		if (hasBuff) {
			ITemperature temp = TemperatureHelper.getTemperatureData(player);
			int target = temp.getPlayerTarget(player);
			
			if (temp.getTemperature().getRawValue() < target && temp.getTemperature().getRawValue() >= 12) {
				temp.setChangeTime(temp.getChangeTime()-1);
			} else
				temp.setChangeTime(0);
		}
		
		if (hasBuff2) {
			ITemperature temp = TemperatureHelper.getTemperatureData(player);
			int target = temp.getPlayerTarget(player);
				
			if (temp.getTemperature().getRawValue() > target && temp.getTemperature().getRawValue() <= 8) {
				if (buff1Level == 1)
					temp.setChangeTime(temp.getChangeTime()-2);
				else
					temp.setChangeTime(0);
			}
		}
	}
	
	@SubscribeEvent
	public void livingEntityUseItem(LivingEntityUseItemEvent.Finish event)
	{	
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			ItemStack item = event.getItem();
			
			///
			/// All of the Harvestcraft drink items.
			///
			if (item.getItem().getRegistryName().toString().contains("harvestcraft")) {
				ResourceLocation itemName = item.getItem().getRegistryName();
				
				if (itemName.toString().toLowerCase().contains("smoothie"))
					NudgeTemperature(player, -2);
				
				if (itemName.toString().toLowerCase().contains("tea") || itemName.toString().toLowerCase().contains("cocoa") || itemName.toString().toLowerCase().contains("coffee"))
					NudgeTemperature(player, 2);
				
				IThirst th = ThirstHelper.getThirstData(player);
				
				if ((th.getThirst() + 3) > 20)
					th.setThirst(20);
				else
					th.setThirst(th.getThirst() + 3);
				
				if ((th.getHydration() + 2) > 20)
					th.setHydration(20);
				else
					th.setHydration(th.getHydration() + 2);
			}
			
			///
			/// Config supported items.
			///
			if (SearchConfigArrayForItemThirst(item) != 0) {
				int effect = SearchConfigArrayForItemThirst(item);
				IThirst th = ThirstHelper.getThirstData(player);
				
				if ((th.getThirst() + effect) > 20)
					th.setThirst(20);
				else
					th.setThirst(th.getThirst() + effect);
				
				if ((th.getHydration() + (effect/2)) > 20)
					th.setHydration(20);
				else
					th.setHydration(th.getHydration() + (effect/2));
			}
		}
		
	}
	
	public int SearchConfigArrayForItemThirst(ItemStack item)
	{
		String[] drinkThirst = ConfigHandler.thirstItems;
		
		for (String drink : drinkThirst) {
			if (drink.toLowerCase().startsWith(item.getItem().getRegistryName().toString().toLowerCase()) == true) {
				return Integer.parseInt(drink.substring(item.getItem().getRegistryName().toString().length()+1));
			}
		}
		
		return 0;
	}
	
	
	public void NudgeTemperature (EntityPlayer player, int amount)
	{
		ITemperature temp = TemperatureHelper.getTemperatureData(player);
		
		if ((temp.getTemperature().getRawValue() - amount) > 9 && (temp.getTemperature().getRawValue() - amount) < 15)
			temp.addTemperature(new Temperature(amount));
		else {
			if (amount > 0 && temp.getTemperature().getRawValue() < 15)
				temp.setTemperature(new Temperature(15));
			else if (amount < 0 && temp.getTemperature().getRawValue() > 9)
				temp.setTemperature(new Temperature(9));
		}
	}

}