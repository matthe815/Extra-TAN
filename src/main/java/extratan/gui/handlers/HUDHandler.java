package extratan.gui.handlers;

import org.lwjgl.opengl.GL11;

import extratan.core.ExtraTAN;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.thirst.ThirstHelper;

@SideOnly(Side.CLIENT)
public class HUDHandler 
{
	protected int offset;
	
	private static final ResourceLocation icons = new ResourceLocation(ExtraTAN.modId, "textures/gui/icons.png");
	
    public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new HUDHandler());
	}
	
	@SubscribeEvent(priority=EventPriority.LOW)
	public void onPreRender(RenderGameOverlayEvent.Pre event)
	{
		if (event.getType() != ElementType.FOOD)
			return;
		
		offset = GuiIngameForge.right_height;
		
		if (event.isCanceled())
			return;
		
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.player;
		
		ScaledResolution scale = event.getResolution();
		
		int left = scale.getScaledWidth() / 2 + 91;
		int top = scale.getScaledHeight() - offset - 10;
		
		IThirst th = ThirstHelper.getThirstData(player);
 
		drawExhaustionOverlay(th.getExhaustion(), mc, left, top, 1f);
	}
	
	public static void drawExhaustionOverlay (float exhaustion, Minecraft mc, int left, int top, float alpha)
	{
		mc.getTextureManager().bindTexture(icons);

		float maxExhaustion = 4;
		float ratio = exhaustion / maxExhaustion;
		int width = (int) (ratio * 81);
		int height = 9;

		enableAlpha(.75f);
		mc.ingameGUI.drawTexturedModalRect(left - width, top, 81 - width, 18, width, height);
		disableAlpha(.75f);

		// rebind default icons
		mc.getTextureManager().bindTexture(Gui.ICONS);
	}
	
    public static void enableAlpha(float alpha)
	{
		GlStateManager.enableBlend();

		if (alpha == 1f)
			return;

		GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void disableAlpha(float alpha)
	{
		GlStateManager.disableBlend();

		if (alpha == 1f)
			return;

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}

	
}
