package extratan.lootfunctions;

import java.util.Random;

import extratan.core.ExtraTAN;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class ApplyRandomTempProt extends LootFunction {
	public ApplyRandomTempProt() {
		super(new LootCondition[] {});
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		if (rand.nextInt() % 2 == 1)
			stack.addEnchantment(ExtraTAN.cold_resistance, 1);
		else
			stack.addEnchantment(ExtraTAN.heat_resistance, 1);
		
		return stack;
	}

}
