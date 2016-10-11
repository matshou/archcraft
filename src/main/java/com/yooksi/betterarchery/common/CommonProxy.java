package com.yooksi.betterarchery.common;

import com.yooksi.betterarchery.init.Items;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	/** Called by {@link GenericMod#preInit } in the preInit phase of mod loading. */
	public void preInit(FMLPreInitializationEvent event) 
	{	
		registerResources();
	}
	
	/** 
	 *  Register all items and blocks from the resource library with Forge. <br>
	 *  This is called when the mod is passing the pre-initialization phase.
	 */
	private void registerResources() 
	{
		Logger.info("Preparing to register item and block instances...");
		
		registerItem(Items.REGULAR_BOW, "regular_bow");
		registerItem(Items.RECURVE_BOW, "recurve_bow");
		
		Logger.info("Finished registering object instances. ");
	}
	
	private static <T extends net.minecraft.item.Item> T registerItem(T item, String name) 
	{
		item.setUnlocalizedName(name);
		item.setRegistryName(name);
		return GameRegistry.register(item);
	}
	
	/** Called by {@link GenericMod#init } in the init phase of mod loading. */
	public void init(FMLInitializationEvent event) 
	{
		// Build whatever data structures you care about. Register recipes.
	}
}