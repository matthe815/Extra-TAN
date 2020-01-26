package extratan.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import extratan.core.ExtraTAN;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;

public class BaseBreakableDrinkableItem extends ItemBucketMilk {

	protected int tempModifier;
	protected String name;
	public ITemperature temp;
	public Item emptyVersion;
	
	List<ItemStack> timedItems = new ArrayList<ItemStack>();
	Map<ItemStack,Timer> itemTimers = new HashMap<ItemStack,Timer>();
	
	public BaseBreakableDrinkableItem(String name, String registryName, int temperature, CreativeTabs creativeTab)
	{
		this.name = name;
		this.tempModifier = temperature;
		
		setUnlocalizedName(ExtraTAN.modId + "." + name)
			.setRegistryName(registryName)
			.setCreativeTab(creativeTab);
		
		setMaxDamage(5);
		
		this.tempModifier = temperature;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}
	
	@Override
	public int getItemEnchantability() {
		return 0;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity)
	{
		super.onItemUseFinish(stack, world, entity);
		
		EntityPlayer player = (EntityPlayer)entity;
		
		if (player.isCreative())
			return stack;
		
		IThirst th = ThirstHelper.getThirstData(player);
		
		if ((th.getThirst() + 3) > 20)
			th.setThirst(20);
		else
			th.setThirst(th.getThirst() + 3);
		
		if ((th.getHydration() + 2) > 20)
			th.setHydration(20);
		else
			th.setHydration(th.getHydration() + 2);
		
		ITemperature temp = TemperatureHelper.getTemperatureData(player);
		
		if ((temp.getTemperature().getRawValue() - this.tempModifier) > 9 && (temp.getTemperature().getRawValue() - this.tempModifier) < 15)
			temp.addTemperature(new Temperature(this.tempModifier));
		else {
			if (this.tempModifier > 0)
				temp.setTemperature(new Temperature(15));
			else
				temp.setTemperature(new Temperature(9));
		}
		
		itemTimers.get(stack).cancel();
		itemTimers.remove(stack);
		timedItems.remove(timedItems.indexOf(stack));
		
		ItemStack iStack = new ItemStack(Item.getByNameOrId("extratan:empty_flask"));
		iStack.setItemDamage(stack.getItemDamage());
		
		return iStack;
	}
	
	
	@Override
	public void onUpdate(final ItemStack stack, final World worldIn, final Entity entityIn, final int itemSlot, final boolean isSelected) 
	{
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected); // Do the typical action.
		
		if (!timedItems.contains(stack))
		{
			timedItems.add(stack); // Add the stack.
			Timer timer = new Timer(); // Add a new timer.
			
			itemTimers.put(stack, timer); // Add the timer to the map.
			
			timer.schedule(new TimerTask() { public void run() { damageTimer(stack, worldIn, entityIn, itemSlot); return; } }, 60000, 60000);
		}
	}
	
	public void damageTimer(ItemStack stack, World worldIn, Entity entityIn, int itemSlot) 
	{
		if (entityIn instanceof EntityPlayer)
		{
			setDamage(stack, getDamage(stack)+1); // Up the damage a bit
			
			if (getDamage(stack) > getMaxDamage(stack)-1)
			{
				EntityPlayer player = (EntityPlayer)entityIn; // Fetch the player.
				player.playSound(new SoundEvent(new ResourceLocation("minecraft:block.glass.break")), 1, 1); // Play a glass break sound.
				player.inventory.removeStackFromSlot(itemSlot); // Delete the item.
				itemTimers.get(stack).cancel(); // Cancel the timer when done.
				itemTimers.remove(stack); // Remove the timer.
				timedItems.remove(timedItems.indexOf(stack)); // Removed the stack from the list.
			}	
		}
	}
}
