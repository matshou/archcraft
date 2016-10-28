package com.yooksi.betterarchery.common;

import com.google.common.base.Objects;
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
		
		registerItem(ModItems.SIMPLE_BOW_PLAIN, "simple_bow_plain");
		registerItem(ModItems.SIMPLE_BOW_LEATHER_GRIP, "simple_bow_leather_grip");
		registerItem(ModItems.SIMPLE_BOW_WOOLEN_GRIP, "simple_bow_woolen_grip");
		
		registerItem(ModItems.RECURVE_BOW_PLAIN, "recurve_bow_plain");
		registerItem(ModItems.RECURVE_BOW_LEATHER_GRIP, "recurve_bow_leather_grip");
		registerItem(ModItems.RECURVE_BOW_WOOLEN_GRIP, "recurve_bow_woolen_grip");
		
		registerItem(ModItems.LONG_BOW_PLAIN, "long_bow_plain");
		registerItem(ModItems.LONG_BOW_LEATHER_GRIP, "long_bow_leather_grip");
		registerItem(ModItems.LONG_BOW_WOOLEN_GRIP, "long_bow_woolen_grip");

		registerItem(ModItems.BOW_ITEM_PART_BODY, "bow_item_part_body");
		registerItem(ModItems.BOW_STRING_ITEM, "item_bow_string");
		
		registerItem(ModItems.TREE_RESIN_LIQUID, "tree_resin_liquid");
		registerItem(ModItems.TREE_RESIN_GLUE, "tree_resin_glue");
		
		Logger.info("Finished registering object instances. ");
	}
	
	private static <T extends net.minecraft.item.Item> T registerItem(T item, String name) 
	{
		/*
		 *  If the unlocalized name has already been set by item constructor skip this.
		 */
		if (Objects.equal(item.getUnlocalizedName(), new String("item.null")))
			item.setUnlocalizedName(name);
		
		item.setRegistryName(name);
		return GameRegistry.register(item);
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