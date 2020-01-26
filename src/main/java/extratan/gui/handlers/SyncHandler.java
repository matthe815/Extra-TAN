package extratan.gui.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import extratan.core.ExtraTAN;
import extratan.networking.MessageExhaustionUpdate;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import toughasnails.api.thirst.ThirstHelper;

public class SyncHandler {
	
	public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(ExtraTAN.modId);
	
	private static final Map<UUID, Float> lastExhaustionLevels = new HashMap<UUID, Float>();
	
	public static void init()
	{
		CHANNEL.registerMessage(MessageExhaustionUpdate.class, MessageExhaustionUpdate.class, 1, Side.CLIENT);

		MinecraftForge.EVENT_BUS.register(new SyncHandler());
	}
	
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event)
	{
		if (!(event.getEntity() instanceof EntityPlayerMP))
			return;

		EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
		Float lastExhaustionLevel = lastExhaustionLevels.get(player.getUniqueID());

		/*if (lastSaturationLevel == null || lastSaturationLevel != player.getFoodStats().getSaturationLevel())
		{
			CHANNEL.sendTo(new MessageSaturationSync(player.getFoodStats().getSaturationLevel()), player);
			lastSaturationLevels.put(player.getUniqueID(), player.getFoodStats().getSaturationLevel());
		}*/

		float exhaustionLevel = ThirstHelper.getThirstData(player).getExhaustion();
		if (lastExhaustionLevel == null || Math.abs(lastExhaustionLevel - exhaustionLevel) >= 0.01f)
		{
			CHANNEL.sendTo(new MessageExhaustionUpdate(exhaustionLevel), player);
			lastExhaustionLevels.put(player.getUniqueID(), exhaustionLevel);
		}
	}
	
}
