package extratan.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import extratan.core.ExtraTAN;
import lieutenant.tabs.BaseCreativeTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import toughasnails.api.stat.capability.ITemperature;

public class BaseBreakableDrinkableItem extends BaseDrinkableItem implements IConsumableThirst {

	protected int tempModifier;
	protected String name;
	public ITemperature temp;
	public Item emptyVersion;
	
	List<ItemStack> timedItems = new ArrayList<ItemStack>();
	Map<ItemStack,Timer> itemTimers = new HashMap<ItemStack,Timer>();
	
	public BaseBreakableDrinkableItem(String name, String registryName, BaseCreativeTab creativeTab)
	{
		super(registryName, registryName, creativeTab);
		
		setUnlocalizedName(ExtraTAN.modId + "." + name)
			.setRegistryName(registryName)
			.setCreativeTab(creativeTab);
		
		setMaxDamage(5);
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
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
		super.onItemUseFinish(stack, world, entity);
		
		itemTimers.get(stack).cancel();
		itemTimers.remove(stack);
		timedItems.remove(timedItems.indexOf(stack));
		
		ItemStack iStack = new ItemStack(GetConsumedItem());
		iStack.setItemDamage(stack.getItemDamage());
		
		return iStack;
	}
	
	
	@Override
	public void onUpdate(final ItemStack stack, final World worldIn, final Entity entityIn, final int itemSlot, final boolean isSelected)  {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected); // Do the typical action.
		
		if (timedItems.contains(stack))
			return;
		
		Timer timer = new Timer(); // Create a new "this item will break" timer and store it.
		
		timedItems.add(stack);
		itemTimers.put(stack, timer);
		
		timer.schedule(new TimerTask() { public void run() { damageTimer(stack, worldIn, entityIn, itemSlot); return; } }, 60000, 60000);
	}
	
	public void damageTimer(ItemStack stack, World worldIn, Entity entityIn, int itemSlot)  {
		if (!(entityIn instanceof EntityPlayer))
			return;
		
		setDamage(stack, getDamage(stack)+1); // Up the damage a bit
			
		if (getDamage(stack) < getMaxDamage(stack)-1)
			return;
		
		// Obtain the player that this bottle is stored within.
		EntityPlayer player = (EntityPlayer)entityIn;
		
		player.playSound(new SoundEvent(new ResourceLocation("minecraft:block.glass.break")), 1, 1); // Play a glass break sound.
		player.inventory.removeStackFromSlot(player.inventory.getSlotFor(stack)); // Delete the item.
		itemTimers.get(stack).cancel(); // Cancel the timer when done.
		itemTimers.remove(stack); // Remove the timer.
		timedItems.remove(timedItems.indexOf(stack)); // Removed the stack from the list.
	}
}
