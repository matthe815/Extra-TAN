package extratan.items.Items;

import extratan.creativetabs.CreativeTabHandler;
import extratan.items.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EmptyTemperedFlask extends BaseItem {
	
	public EmptyTemperedFlask()
	{
		super("emptyTemperedFlask", "empty_tempered_flask", CreativeTabHandler.ExtraTANDrinks);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		RayTraceResult result = this.rayTrace(worldIn, playerIn, true); // Ray trace.
		ItemStack stack = playerIn.getHeldItemMainhand();
		
		if (result == null) // If the ray trace result comes back null...
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		
		BlockPos pos = result.getBlockPos(); // Get the ray trace block position.
		
		if (pos == null) // If there's no block at that position somehow...
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		
		Block block = worldIn.getBlockState(pos).getBlock(); // Get the block at that position!
		
		if (block == null) // If no block comes up at that position...
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		
		float blockTemp = worldIn.getBiome(pos).getTemperature(pos);
		ItemStack newstack;
		
		if (blockTemp < 0.2f)
			newstack = new ItemStack(Item.getByNameOrId("extratan:tempered_flask_with_cold_water"));
		else if (blockTemp > 0.85f)
			newstack = new ItemStack(Item.getByNameOrId("extratan:tempered_flask_with_hot_water"));
		else
			newstack = new ItemStack(Item.getByNameOrId("extratan:filled_tempered_flask"));
			
		if (block.getRegistryName() != Blocks.WATER.getRegistryName())
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		
		playerIn.playSound(new SoundEvent(new ResourceLocation("minecraft:item.bottle.fill")), 1f, 1f);
		
		if (stack.getCount() > 1) {
			stack.setCount(stack.getCount() - 1);
			
			if (!playerIn.inventory.addItemStackToInventory(newstack))
				playerIn.dropItem(newstack, false);
			
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		else {
			stack = newstack;
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, newstack);
		}
	}
	
}
