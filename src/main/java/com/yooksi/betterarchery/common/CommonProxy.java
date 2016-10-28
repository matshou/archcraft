package com.yooksi.betterarchery.common;

import com.yooksi.betterarchery.crafting.ItemRecipes;
import com.yooksi.betterarchery.init.ModItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	/** Called by {@link GenericMod#preInit } in the preInit phase of mod loading. */
	public void preInit(FMLPreInitializationEvent event) 
	{	
		registerCommons();
	}
	
	/** 
	 *  Register all items and blocks from the resource library with Forge. <br>
	 *  This is called when the mod is passing the pre-initialization phase.
	 */
	private void registerCommons()
	{
		Logger.info("Preparing to register item and block instances...");
		
		registerItem(ModItems.SIMPLE_BOW_PLAIN, "simple_bow_plain", "simple_bow");
		registerItem(ModItems.RECURVE_BOW_PLAIN, "recurve_bow_plain", "recurve_bow");
		registerItem(ModItems.LONG_BOW_PLAIN, "long_bow_plain", "long_bow");
		
		registerItem(ModItems.SIMPLE_BOW_LEATHER_GRIP, "simple_bow_leather_grip", "simple_bow");
		registerItem(ModItems.SIMPLE_BOW_WOOLEN_GRIP, "simple_bow_woolen_grip", "simple_bow");
		registerItem(ModItems.RECURVE_BOW_LEATHER_GRIP, "recurve_bow_leather_grip", "recurve_bow");
		registerItem(ModItems.RECURVE_BOW_WOOLEN_GRIP, "recurve_bow_woolen_grip", "recurve_bow");
		registerItem(ModItems.LONG_BOW_LEATHER_GRIP, "long_bow_leather_grip", "long_bow");
		registerItem(ModItems.LONG_BOW_WOOLEN_GRIP, "long_bow_woolen_grip", "long_bow");

		registerItem(ModItems.BOW_ITEM_PART_BODY, "bow_item_part_body");
		registerItem(ModItems.BOW_STRING_ITEM, "item_bow_string", "archers_bow_string");
		
		registerItem(ModItems.TREE_RESIN_LIQUID, "tree_resin_liquid");
		registerItem(ModItems.TREE_RESIN_GLUE, "tree_resin_glue");
		
		Logger.info("Finished registering object instances. ");
	}
	
	private static <T extends net.minecraft.item.Item> T registerItem(T item, String registryName, String unlocalizedName) 
	{
		item.setUnlocalizedName(unlocalizedName);
		item.setRegistryName(registryName);
		return GameRegistry.register(item);
	}
	
	private static <T extends net.minecraft.item.Item> T registerItem(T item, String registryName) 
	{
		return registerItem(item, registryName, registryName);
	}
	
	/** 
	 *  Register new custom recipes and remove vanilla ones. <br>
	 *  This is called when the mod is passing the initialization phase.
	 */
	private void handleRecipes() 
    {			
		final int recipesRemoved = ItemRecipes.removeRecipesFromVanilla();
		Logger.info("Removed " + recipesRemoved + " vanilla recipes.");

		ItemRecipes.registerRecipeTypes();
		ItemRecipes.registerRecipes();
    }
	
	/** Called by {@link GenericMod#init } in the init phase of mod loading. */
	public void init(FMLInitializationEvent event) 
	{
		handleRecipes();
	}
}