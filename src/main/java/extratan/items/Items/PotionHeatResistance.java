package extratan.items.Items;

import extratan.items.BasePotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.temperature.TemperatureHelper;

public class PotionHeatResistance extends BasePotion {
	
	public PotionHeatResistance(int id) {
		super(false, 0x0002b, 1, 1);
	}
	
    @Override
    public void performEffect(EntityLivingBase entity, int amplifier)
    {
    	System.out.println("Potion event fired");
    	
    	if (!(entity instanceof EntityPlayer))
    		return;
    	
    	EntityPlayer player = (EntityPlayer) entity;
		ITemperature temp = TemperatureHelper.getTemperatureData(player);
		int target = temp.getPlayerTarget(player);
		
		if (temp.getTemperature().getRawValue() < target && temp.getTemperature().getRawValue() >= 12)
			temp.setChangeTime(0);
    }
    
    @Override
    public boolean isReady(int duration, int amplifier)
    {
        int time = 50 >> amplifier;
        return time > 0 ? duration % time == 0 : true;
    }
	
}
