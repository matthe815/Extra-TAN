package extratan.items;

import net.minecraft.item.Item;

public interface IConsumableThirst {
	public int GetThirstModifier();
	public int GetTemperatureModifier();
	public Item GetConsumedItem();
}
