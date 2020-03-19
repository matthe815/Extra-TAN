package extratan.core;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;

public class EventEngine {
	
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
                    buff1Level = EnchantmentHelper.getMaxEnchantmentLevel(ExtraTAN.heat_resistance, player);
                }
                
                if (idTag.getShort("id") == Enchantment.getEnchantmentID(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("extratan:cold_resistance")))) {
                    hasBuff2 = true;
                    buff2Level = EnchantmentHelper.getMaxEnchantmentLevel(ExtraTAN.cold_resistance, player);
                }
            }
		}
		 
		if (hasBuff) {
			ITemperature temp = TemperatureHelper.getTemperatureData(player);
			int target = temp.getPlayerTarget(player);
			
			if (temp.getTemperature().getRawValue() < target && temp.getTemperature().getRawValue() >= 12)  {
				if (buff1Level == 1)
					temp.setChangeTime(temp.getChangeTime()-2);
				else
					temp.setChangeTime(0);
			}
		}
		
		if (hasBuff2) {
			ITemperature temp = TemperatureHelper.getTemperatureData(player);
			int target = temp.getPlayerTarget(player);
				
			if (temp.getTemperature().getRawValue() > target && temp.getTemperature().getRawValue() <= 8) {
				if (buff2Level == 1)
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
				
				if (itemName.toString().toLowerCase().contains("smoothie") || itemName.toString().toLowerCase().contains("juice"))
					NudgeTemperature(player, -2);
				else if (itemName.toString().toLowerCase().contains("tea") || itemName.toString().toLowerCase().contains("cocoa") || itemName.toString().toLowerCase().contains("coffee"))
					NudgeTemperature(player, 2);
				else
					return;
				
				IThirst th = ThirstHelper.getThirstData(player);
				
				if (SearchConfigArrayForItemThirst(item) == -1) {
					if ((th.getThirst() + 3) > 20)
						th.setThirst(20);
					else
						th.setThirst(th.getThirst() + 3);
				
					if ((th.getHydration() + 2) > 20)
						th.setHydration(20);
					else
						th.setHydration(th.getHydration() + 2);
				}
			}
			
			///
			/// Config supported items.
			///
			if (SearchConfigArrayForItemThirst(item) != -1) {
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
		
		return -1;
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
