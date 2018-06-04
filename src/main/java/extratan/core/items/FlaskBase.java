package extratan.core.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class FlaskBase extends ItemBase {
	
	public FlaskBase(String name)
	{
		super(name);
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
	{
		RayTraceResult result = this.rayTrace(world, player, true);
		
		if (result == null) return ActionResult.newResult(EnumActionResult.FAIL, stack);
		
		BlockPos pos = result.getBlockPos();
		
		if (pos == null) return ActionResult.newResult(EnumActionResult.FAIL, stack);
		
		Block block = world.getBlockState(pos).getBlock();
		
		if (block == null) return ActionResult.newResult(EnumActionResult.FAIL, stack);
		
		ItemStack newstack = new ItemStack(ModItems.waterDrink);
		
		System.out.println(block.getRegistryName());
		System.out.println(Blocks.WATER.getRegistryName());
		
		if (block.getRegistryName() != Blocks.WATER.getRegistryName()) return ActionResult.newResult(EnumActionResult.FAIL, stack);
		
		System.out.println(stack.stackSize);
		
		if (stack.stackSize > 1) 
		{
			stack.stackSize = stack.stackSize - 1;
			
			if (!player.inventory.addItemStackToInventory(newstack))
			{
				player.dropItem(newstack, false);
			}
		}
		else
		{
			System.out.println("May crash here.");
			stack.setItem(newstack.getItem());	
			System.out.println("Nope. It didn't.");
		}
			
		return ActionResult.newResult(EnumActionResult.SUCCESS, newstack);
	}
	
}
