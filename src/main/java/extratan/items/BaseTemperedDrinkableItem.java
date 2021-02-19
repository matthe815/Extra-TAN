package extratan.items;

import extratan.core.ExtraTAN;
import lieutenant.tabs.BaseCreativeTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class BaseTemperedDrinkableItem extends BaseDrinkableItem implements IConsumableThirst
{
	public BaseTemperedDrinkableItem(String name, String registryName, int temperature, BaseCreativeTab creativeTab)
	{
		super(registryName, registryName, creativeTab);

		setUnlocalizedName(ExtraTAN.modId + "." + name) // Set the unlocalized name.
			.setRegistryName(registryName) // Set the localized name.
			.setCreativeTab(creativeTab); // Set the creative tab.
		
		setMaxDamage(5); // Set the max damage.
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
}
