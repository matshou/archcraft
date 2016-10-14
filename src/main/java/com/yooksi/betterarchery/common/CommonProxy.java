package com.yooksi.betterarchery.common;

import com.yooksi.betterarchery.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
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
		
		registerItem(ModItems.SIMPLE_BOW, "simple_bow_plain");
		registerItem(ModItems.RECURVE_BOW, "recurve_bow_plain");

		registerItem(ModItems.BOW_ITEM_PART_BODY, "bow_item_part_body");
		
		Logger.info("Finished registering object instances. ");
	}
	
	private static <T extends net.minecraft.item.Item> T registerItem(T item, String name) 
	{
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
		// Remove vanilla recipes here
		int recipesRemovedCount = 0;
		
		recipesRemovedCount += removeRecipe(Items.BOW);		
		
		Logger.info("Removed " + recipesRemovedCount + " vanilla recipes.");

		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW), 
				new Object[] { " /@", "/ @", " /@", '/', Items.STICK, '@', Items.STRING});
    }
	
	/** 
	 *  Removes vanilla recipes from the CraftingManager recipe list.
	 *  
	 *  @param toRemove Item corresponding to the output of the recipe we want to remove.
	 *  @return Number of recipes we removed from the recipe list.
	 */ 
	private static int removeRecipe(Item toRemove)
	{
		int recipesRemoved = 0;
		java.util.List<IRecipe> recipeList = net.minecraft.item.crafting.CraftingManager.getInstance().getRecipeList();
		
		// Iterate through the recipe list and find the recipes we're looking for.
		// Search using iterators instead of manual indexing to increase reliability.
		
	    java.util.Iterator<IRecipe> recipeEntry = recipeList.iterator();
	    while (recipeEntry.hasNext())
	    {
	    	net.minecraft.item.ItemStack outputItem = recipeEntry.next().getRecipeOutput();
			if (outputItem != null && outputItem.getItem() == toRemove)
			{
				recipeEntry.remove();
				recipesRemoved++;
			}
	    }   return recipesRemoved;
	}
	
	/** Called by {@link GenericMod#init } in the init phase of mod loading. */
	public void init(FMLInitializationEvent event) 
	{
		handleRecipes();
	}
}