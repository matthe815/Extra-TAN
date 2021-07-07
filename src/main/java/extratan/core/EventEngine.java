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
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;

/**
 * The core event listener, this controls all of the events ExtraTAN utilizes.
 * @author Matthew
 *
 */
public class EventEngine {
	
	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent evt) {
	    if (!(evt.getName().toString().equals("minecraft:chests/simple_dungeon") 
	    		|| evt.getName().toString().equals("minecraft:chests/spawn_bonus_chest") 
	    		|| evt.getName().toString().equals("minecraft:chests/abandoned_mineshaft")))
	    	return;
	    	
	    LootEntry entry = new LootEntryTable(new ResourceLocation("extratan:inject/simple_dungeon"), 100, 1, new LootCondition[] {}, "minecraft:enchanted_book");
	    LootPool  pool  = new LootPool(new LootEntry[]{entry}, new LootCondition[] {}, new RandomValueRange(10), new RandomValueRange(5), "main_pool");

	    evt.getTable().addPool(pool);
	}
	
	@SubscribeEvent
	public void livingUpdate(PlayerTickEvent event) 
	{	
		EntityPlayer player = event.player;
		boolean[] buffStatus = {false, false};
		int[]     buffLevel  = {0, 0};
		
		// Loop through the player's armor, and check to see if any of them have a special temperature enchant.
		for (ItemStack item : player.getArmorInventoryList()) {
            NBTTagList tagList = item.getEnchantmentTagList();
            
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound idTag = tagList.getCompoundTagAt(i);
                
                if (idTag.getShort("id") == Enchantment.getEnchantmentID(ExtraTAN.heat_resistance)) {
                    buffStatus[0] = true;
                    buffLevel[0]  = EnchantmentHelper.getMaxEnchantmentLevel(ExtraTAN.heat_resistance, player);
                }
                
                if (idTag.getShort("id") == Enchantment.getEnchantmentID(ExtraTAN.cold_resistance)) {
                    buffStatus[1] = true;
                    buffLevel[1]  = EnchantmentHelper.getMaxEnchantmentLevel(ExtraTAN.cold_resistance, player);
                }
            }
		}
		
		if (!buffStatus[0]&&!buffStatus[1]) return; // As there's no buffs to process, end here.
		 
		ITemperature temp   = TemperatureHelper.getTemperatureData(player);
		int          target = temp.getPlayerTarget(player);
			
		/// Both of these effects work by checking the target, and current values. By checking the target, you can see if the effect is opposite to the intended effect.
		/// Using this, we can still allow temperature in the opposite direction, while preventing that of the positive direction.
		
		// Apply heat resistance effect.
		if ((temp.getTemperature().getRawValue() < target && temp.getTemperature().getRawValue() >= 12) && buffStatus[0])
			temp.setChangeTime(buffLevel[0] == 1 ? temp.getChangeTime()-2 : 0);
		
		// Apply cold resistance effect.
		if (temp.getTemperature().getRawValue() > target && temp.getTemperature().getRawValue() <= 8 && buffStatus[1])
			temp.setChangeTime(buffLevel[1] == 1 ? temp.getChangeTime()-2 : 0);
	}
	
	/**
	 * This set of events controls the config and harvestcraft drinking effects.
	 * @param event
	 */
	@SubscribeEvent
	public void livingEntityUseItem(LivingEntityUseItemEvent.Finish event)
	{	
		// Since this effect should only apply to the player, stop if it's not the player.
		if (!(event.getEntity() instanceof EntityPlayer)) return;
		
		EntityPlayer player = (EntityPlayer) event.getEntity();
		ItemStack item = event.getItem();
			
		///
		/// All of the Harvestcraft drink items.
		///
		if (item.getItem().getRegistryName().toString().contains("harvestcraft")) {
			ResourceLocation itemName = item.getItem().getRegistryName();
				
			if (itemName.toString().toLowerCase().contains("smoothie") || itemName.toString().toLowerCase().contains("juice"))
				NudgeTemperature(player, -2);
			else if (itemName.toString().toLowerCase().contains("tea") || itemName.toString().toLowerCase().contains("cocoa") || itemName.toString().toLowerCase().contains("coffee") || itemName.toString().toLowerCase().contains("espresso"))
				NudgeTemperature(player, 2);
			else
				return;
				
			IThirst th = ThirstHelper.getThirstData(player);
			
			// If there's no manually specified values for the harvestcraft items, then just default to 3 for each.
			if (SearchConfigArrayForItemThirst(item) == -1) {
				th.setThirst(Math.min(th.getThirst()+3, 20));
				th.setHydration(Math.min(th.getHydration()+1.5f, 20));
			}
		}
			
		///
		/// Config supported items.
		///
		if (SearchConfigArrayForItemThirst(item) != -1) {
			int effect = SearchConfigArrayForItemThirst(item);
			IThirst th = ThirstHelper.getThirstData(player);
				
			th.setThirst(Math.min(th.getThirst()+effect, 20));
			th.setHydration(Math.min(th.getHydration()+(effect/2), 20));
		}
	}
	
	/**
	 * Search the defined thirst items array for an item-stack by checking its' name.
	 * @param item
	 * @return
	 */
	public int SearchConfigArrayForItemThirst(ItemStack item)
	{
		for (String drink : ConfigHandler.common.thirstItems) {
			if (drink.toLowerCase().startsWith(item.getItem().getRegistryName().toString().toLowerCase()))
				return Integer.parseInt(drink.substring(item.getItem().getRegistryName().toString().length()+1));
		}
		
		return -1;
	}
	
	/**
	 * Slightly increment a players' temperature in a specified direction. Preventing horrible effects from happening through drinking.
	 * @param player
	 * @param amount
	 */
	public static void NudgeTemperature (EntityPlayer player, int amount)
	{
		ITemperature temp = TemperatureHelper.getTemperatureData(player); // Get the players' temperature capability.
		
		if ((temp.getTemperature().getRawValue() - amount) > 9 && (temp.getTemperature().getRawValue() - amount) < 15)
			temp.addTemperature(new Temperature(amount));
		else {
			if (amount > 0)
				temp.setTemperature(new Temperature(15));
			else if (amount < 0)
				temp.setTemperature(new Temperature(9));
		}
	}

}
