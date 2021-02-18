package extratan.items;

import extratan.core.EventEngine;
import extratan.core.ExtraTAN;
import extratan.items.Items.ItemList;
import lieutenant.tabs.BaseCreativeTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.thirst.ThirstHelper;

public class BaseDrinkableItem extends ItemBucketMilk implements IConsumableThirst {
	
	public BaseDrinkableItem(String name, String registryName, BaseCreativeTab creativeTab) {
		setUnlocalizedName(ExtraTAN.modId + "." + name)
			.setRegistryName(registryName);
		
		setCreativeTab(creativeTab);
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
		
		EntityPlayer player = (EntityPlayer)entity;
		IThirst      thirst = ThirstHelper.getThirstData(player);
		
		thirst.setThirst(Math.min(thirst.getThirst()+GetThirstModifier(), 20));
		thirst.setHydration(Math.min(thirst.getHydration()+GetThirstModifier()/2, 20));
		
		EventEngine.NudgeTemperature(player, GetTemperatureModifier()); // Apply the temperature effect.

		// If the player is in creative, don't actually consume the bottle.
		if (player.isCreative()) return stack;
		
		ItemStack iStack = new ItemStack(GetConsumedItem());
		iStack.setItemDamage(stack.getItemDamage());
		
		return iStack;
	}

	@Override
	public int GetThirstModifier() {
		return 3;
	}

	@Override
	public int GetTemperatureModifier() {
		return 0;
	}

	@Override
	public Item GetConsumedItem() {
		return ItemList.EMPTY_FLASK;
	}
}
