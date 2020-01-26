package extratan.items;

import extratan.core.ExtraTAN;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;

public class BaseTemperedDrinkableItem extends ItemBucketMilk 
{

	protected int tempModifier;
	protected String name;
	public ITemperature temp;
	public Item emptyVersion;
	
	public BaseTemperedDrinkableItem(String name, String registryName, int temperature, CreativeTabs creativeTab)
	{
		this.name = name; // Set the item's name.
		this.tempModifier = temperature; // Set the item's temperature.
		
		setUnlocalizedName(ExtraTAN.modId + "." + name) // Set the unlocalized name.
			.setRegistryName(registryName) // Set the localized name.
			.setCreativeTab(creativeTab); // Set the creative tab.
		
		setMaxDamage(5); // Set the max damage.
		
		this.tempModifier = temperature; // The temperature modifier for the drink.
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
		
		ItemStack iStack = new ItemStack(Item.getByNameOrId("extratan:empty_tempered_flask"));
		iStack.setItemDamage(stack.getItemDamage());
		
		return iStack;
	}
}
