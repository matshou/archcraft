package com.yooksi.betterarchery.common;

import com.yooksi.betterarchery.item.ArchersBow;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler 
{
	@SubscribeEvent
	public void FOVUpdateEvent(net.minecraftforge.client.event.FOVUpdateEvent event)
	{
        EntityPlayer player = event.getEntity();
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
}
