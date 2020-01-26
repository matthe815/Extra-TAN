package extratan.enchantments;

import extratan.core.ExtraTAN;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class HeatResistanceEnchantment extends Enchantment {

	public HeatResistanceEnchantment() {
		super(Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[] {EntityEquipmentSlot.CHEST, EntityEquipmentSlot.HEAD, EntityEquipmentSlot.FEET, EntityEquipmentSlot.LEGS});
		setRegistryName(ExtraTAN.modId + ":" + "heat_resistance");
		setName("heat_resistance");
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}

}
