package com.yooksi.betterarchery.network;

import com.yooksi.betterarchery.common.CommonProxy;
import com.yooksi.betterarchery.init.ModItems;
import com.yooksi.betterarchery.item.BowParts;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		
		// This step is necessary in order to make your block render properly when it is an item (i.e. in the inventory or in your hand or thrown on the ground).
	    // It must be done on client only, and must be done after the block has been created in Common.preinit().
		
		ModelLoader.setCustomModelResourceLocation(ModItems.SIMPLE_BOW, 0, new ModelResourceLocation(ModItems.SIMPLE_BOW.getRegistryName().toString()));
		ModelLoader.setCustomModelResourceLocation(ModItems.RECURVE_BOW, 0, new ModelResourceLocation(ModItems.RECURVE_BOW.getRegistryName().toString()));
	
		ModelLoader.setCustomModelResourceLocation(BowParts.SIMPLE_BOW_LIMB, 0, new ModelResourceLocation(BowParts.SIMPLE_BOW_LIMB.getRegistryName().toString()));
	}
}