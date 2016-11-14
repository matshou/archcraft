package com.yooksi.betterarchery.common;

import com.yooksi.betterarchery.client.ArchersBowModel;
import com.yooksi.betterarchery.init.ModItems;
import com.yooksi.betterarchery.item.ArchersBow;
import com.yooksi.betterarchery.item.ItemBowPartBody;

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
	@SideOnly(Side.CLIENT)
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
	@SideOnly(Side.CLIENT)
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
	 * Fired when the ModelManager is notified of the resource manager reloading. <br>
	 * Called after model registry is setup, but before it's passed to BlockModelShapes. <p>
	 * 
	 * <i>Use this event to import custom IBakeModels for your items and blocks. </i>
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onModelBakeEvent(net.minecraftforge.client.event.ModelBakeEvent event)
	{
		/*
		 *  Model registry is mapping model locations to baked models,
		 *  meaning that when you replace a model for some location in the registry,
		 *  every item that is registered with that location will now use the overriding model.
		 *  
		 *  Remember that every item can ONLY have ONE resource location,
		 *  and each resource location can only have one overriding model.
		 */

		java.util.List<ArchersBow.BowItemVariant> parents = ArchersBow.BowItemVariant.getParents();
		for (java.util.Iterator<ArchersBow.BowItemVariant> iter = parents.iterator(); iter.hasNext();) 
		{
			ArchersBow.BowItemVariant parent = iter.next();
			ModelResourceLocation location = parent.getModelResourceLocation(false);

			Object object = event.getModelRegistry().getObject(location);
			if (object instanceof IBakedModel) 
			{
				IBakedModel oldBakedModel = (IBakedModel)object;
				ModelResourceLocation pseudoLocation = parent.getModelResourceLocation(true);

				event.getModelRegistry().putObject(pseudoLocation, new ArchersBowModel(oldBakedModel));
			}
		}

		java.util.List<ItemBowPartBody.BodyPartType> parents2 = ItemBowPartBody.BodyPartType.getParents();
		for (java.util.Iterator<ItemBowPartBody.BodyPartType> iter = parents2.iterator(); iter.hasNext();) 
		{
			ItemBowPartBody.BodyPartType parent = iter.next();
			ModelResourceLocation location = parent.getModelResourceLocation(false);

			Object object = event.getModelRegistry().getObject(location);
			if (object instanceof IBakedModel) 
			{
				IBakedModel oldBakedModel = (IBakedModel)object;
				ModelResourceLocation pseudoLocation = parent.getModelResourceLocation(true);

				event.getModelRegistry().putObject(pseudoLocation, new ArchersBowModel(oldBakedModel));
			}
		}
	}
}
