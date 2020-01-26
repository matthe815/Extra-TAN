package extratan.enchantments;

import extratan.core.ExtraTAN;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ColdResistanceEnchantment extends Enchantment {

	public ColdResistanceEnchantment() {
		super(Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[] {EntityEquipmentSlot.CHEST, EntityEquipmentSlot.HEAD, EntityEquipmentSlot.FEET, EntityEquipmentSlot.LEGS});
		setRegistryName(ExtraTAN.modId + ":" + "cold_resistance");
		setName("cold_resistance");
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}
	
}
