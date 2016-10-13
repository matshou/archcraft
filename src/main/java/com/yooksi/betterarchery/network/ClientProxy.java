package com.yooksi.betterarchery.network;

import com.yooksi.betterarchery.common.CommonProxy;
import com.yooksi.betterarchery.init.ModItems;

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

		ModelLoader.setCustomModelResourceLocation(ModItems.SIMPLE_BOW, 0, new ModelResourceLocation(ModItems.SIMPLE_BOW.getRegistryName().toString()));
		ModelLoader.setCustomModelResourceLocation(ModItems.RECURVE_BOW, 0, new ModelResourceLocation(ModItems.RECURVE_BOW.getRegistryName().toString()));
	}
}