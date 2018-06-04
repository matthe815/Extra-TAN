package extratan.core.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import extratan.core.ExtraTAN;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;

public class DrinkBase extends ItemBucketMilk {
	
	protected int tempModifier;
	protected String name;
	public float temperature;
	public ITemperature temp;
	public int lastInt = 0;

	public DrinkBase(String name, int temperature)
	{
		this.name = name;
		this.tempModifier = temperature;
		this.temperature = temperature * 4;
		this.lastInt = (int)this.temperature;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		setCreativeTab(ExtraTAN.creativeTab);
	}
	
	public void registerItemModel() {
		ExtraTAN.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public DrinkBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity)
	{
		EntityPlayer player = (EntityPlayer)entity;
		
		if (player.isCreative()) return stack;
		
		stack.setItem(ModItems.emptyFlask);	
		
		IThirst th = ThirstHelper.getThirstData(player);
		th.setThirst(th.getThirst() + 6);
		th.setHydration(th.getHydration() + 2);

		if (temp == null) temp = TemperatureHelper.getTemperatureData(player);
		
		boolean canDrink = true;
		
		if (temp.getTemperature().getRawValue() + this.temperature > 22) if (this.temperature < 5) canDrink = false;
		if (temp.getTemperature().getRawValue() + this.temperature < 4) if (this.temperature > -5) canDrink = false;	
		
		if (canDrink) temp.addTemperature(new Temperature(tempModifier));
		
		return stack;
	}
}
