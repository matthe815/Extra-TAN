package extratan.items.Items;

import extratan.creativetabs.CreativeTabHandler;
import extratan.items.BaseDrinkableItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.thirst.ThirstHelper;

public class AppleJuice extends BaseDrinkableItem {

	public AppleJuice() {
		super("appleJuice", "apple_juice", -1, CreativeTabHandler.ExtraTANDrinks);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity)
	{
		EntityPlayer player = (EntityPlayer) entity;
		
		IThirst th = ThirstHelper.getThirstData(player); // Get the player's Thirst Helper.
		th.setThirst(th.getThirst() + 9); // Add 9 to the player's Thirst value.
		th.setHydration(th.getHydration() + 8); // Add 8 to the player's Hydration value.
		
		return super.onItemUseFinish(stack, world, entity);
	}

}
