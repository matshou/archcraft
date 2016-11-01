package com.yooksi.betterarchery.common;

import com.yooksi.betterarchery.client.ArchersBowModel;
import com.yooksi.betterarchery.init.ModItems;
import com.yooksi.betterarchery.item.ArchersBow;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class EventHandler 
{
	@SubscribeEvent
	public static void FOVUpdateEvent(net.minecraftforge.client.event.FOVUpdateEvent event)
	{
		net.minecraft.entity.player.EntityPlayer player = event.getEntity();
        ItemStack activeItem = player != null ? player.getActiveItemStack() : null;
        
        /* 
         * Every time a bow pulling animation is played, the FOV gradually drops.
         * This effects is for visual purposes only as it emulates vision focus.
         * For some reason FOV is not update when we're dealing with custom bows.
         */
		if (activeItem != null && activeItem.getItem() instanceof ArchersBow)
		{
			ArchersBow bow = (ArchersBow) activeItem.getItem();
			final float animationProgress = bow.getPullingAnimationProgress(activeItem, player);
		    
			/*
			 *  The bow pulling animation updates entity FOV up to 0.85F max.
			 *  
			 *  TODO: The gradual rate of FOV decrease should be exponential and corresponds 
			 *        to the three animation stages the bow goes through when it's pulled.
			 *        Take a look at how vanilla bows do it in-game.   
			 */
			
			event.setNewfov(event.getFov() - (float)(0.15F * animationProgress));
		}
	}
	
	@SubscribeEvent
	public static void onRenderSpecificHandEvent(net.minecraftforge.client.event.RenderSpecificHandEvent event)
	{
		net.minecraft.entity.player.EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		EnumHand activeHand = player.getActiveHand();
		
		/*
		 *  Note that in case the player is not using an item getActiveHand() 
		 *  on client will return the last hand that was active. 
		 */
		if (player.getItemInUseCount() > 0 && event.getHand() != activeHand)
		{
			ItemStack itemInActiveHand = player.getHeldItem(activeHand);
			if (itemInActiveHand != null && itemInActiveHand.getItem() instanceof ArchersBow)
				event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onPlayerBreakingBlock(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event)
	{
		net.minecraft.entity.player.EntityPlayer player = event.getEntityPlayer();
		Block target = event.getState().getBlock();
		
		if (player != null && player.isServerWorld() && target instanceof net.minecraft.block.BlockLog)
		{
			/*
			 *  This will not work for custom glass bottles from third party mods,
			 *  implement special support here to achieve compatibility.
			 */
			
			ItemStack offHandStack = player.getHeldItem(EnumHand.OFF_HAND);
			if (offHandStack != null && offHandStack.getItem() == net.minecraft.init.Items.GLASS_BOTTLE)
			{
				if (Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown()) 
				{
					// TODO: Either do a random roll or implement a timer to make this harder.
					
					ItemStack stack = new ItemStack(ModItems.TREE_RESIN_LIQUID, 1);
				    player.setHeldItem(EnumHand.OFF_HAND, stack);
				}
			}
		}
	}
	
	/**
	 * Called twice during startup, after creating a new model manager and setting up the model registry. <br>
	 * Use this event to import custom IBakeModels for your items and blocks.
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onModelBakeEvent(net.minecraftforge.client.event.ModelBakeEvent event)
	{
		/*
		 *  Replace the models of all bows variants that should not be using ItemColorHandler.
		 *  Adding a custom model class allows us to reduce the amount of model files. 
		 */
		
		java.util.List<ArchersBow> bowsWithNoGrip = ArchersBow.BowItemVariant.getVariantsWithNoColor();
		final java.util.Iterator<ArchersBow> iter;
		
		for (iter = bowsWithNoGrip.iterator(); iter.hasNext();)
		{
			final ModelResourceLocation location = iter.next().getModelResourceLocation();
			Object object = event.getModelRegistry().getObject(location);
			if (object instanceof IBakedModel) 
			{
				IBakedModel oldBakedModel = (IBakedModel)object;
				event.getModelRegistry().putObject(location, new ArchersBowModel(oldBakedModel));
			}
		}
	}
}
