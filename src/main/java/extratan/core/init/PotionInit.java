package extratan.core.init;

import extratan.core.ExtraTAN;
import extratan.items.Items.PotionColdResistance;
import extratan.items.Items.PotionHeatResistance;
import extratan.items.Items.PotionTemperatureImmunity;
import extratan.items.Items.PotionTemperatureResistance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit {
	
	public static final Potion HEAT_RESISTANCE = new PotionHeatResistance(ForgeRegistries.POTIONS.getEntries().size()+1);
	public static final Potion COLD_RESISTANCE = new PotionColdResistance(ForgeRegistries.POTIONS.getEntries().size()+2);
	
	public static void Init ()
	{
		ForgeRegistries.POTIONS.register(HEAT_RESISTANCE
			.setRegistryName(ExtraTAN.modId + ":" + "heat_resistance")
			.setPotionName("potion.chilled").setBeneficial());
			
		ForgeRegistries.POTIONS.register(COLD_RESISTANCE
			.setRegistryName(ExtraTAN.modId + ":" + "cold_resistance")
			.setPotionName("potion.steaming").setBeneficial());
			
		ForgeRegistries.POTIONS.register(new PotionTemperatureResistance(54)
			.setRegistryName(ExtraTAN.modId + ":" + "temperature_resistance")
			.setPotionName("potion.temperature_resistance").setBeneficial());
			
		ForgeRegistries.POTIONS.register(new PotionTemperatureImmunity(54)
			.setRegistryName(ExtraTAN.modId + ":" + "temperature_immunity")
			.setPotionName("potion.temperature_immunity").setBeneficial());
			
		ForgeRegistries.POTION_TYPES.register(new PotionType(new PotionEffect[] {
			new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation(ExtraTAN.modId + ":" + "temperature_resistance")), 3600)
		}).setRegistryName("temperature_resistance"));
			
		ForgeRegistries.POTION_TYPES.register(new PotionType(new PotionEffect[] {
			new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation(ExtraTAN.modId + ":" + "temperature_immunity")), 2400)
		}).setRegistryName("temperature_immunity"));
	}
}
