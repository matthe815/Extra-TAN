package extratan.items.Items;

import java.util.List;

import extratan.creativetabs.CreativeTabHandler;
import lieutenant.items.BaseItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LiquidInfuser extends BaseItem 
{
		public LiquidInfuser()
		{
			super("liquidInfuser", "liquid_infuser", CreativeTabHandler.ExtraTANMaterials);
		}
		
		@Override
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
			super.addInformation(stack, worldIn, tooltip, flagIn);
			
			tooltip.add("Deprecated item, will be removed in a later build.");
			tooltip.add("Does not have a recipe nor a use.");
		}
}