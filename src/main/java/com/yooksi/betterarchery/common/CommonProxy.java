package com.yooksi.betterarchery.common;

import com.yooksi.betterarchery.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

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
		
		registerItem(ModItems.SIMPLE_BOW_PLAIN, "simple_bow_plain");
		registerItem(ModItems.SIMPLE_BOW_LEATHER_GRIP, "simple_bow_leather_grip");
		registerItem(ModItems.SIMPLE_BOW_WOOLEN_GRIP, "simple_bow_woolen_grip");
		
		registerItem(ModItems.RECURVE_BOW_PLAIN, "recurve_bow_plain");
		registerItem(ModItems.RECURVE_BOW_LEATHER_GRIP, "recurve_bow_leather_grip");
		registerItem(ModItems.RECURVE_BOW_WOOLEN_GRIP, "recurve_bow_woolen_grip");

		registerItem(ModItems.BOW_ITEM_PART_BODY, "bow_item_part_body");
		registerItem(ModItems.BOW_STRING_ITEM, "bow_string_item");
		
		registerItem(ModItems.TREE_RESIN_LIQUID, "tree_resin_liquid");
		registerItem(ModItems.TREE_RESIN_GLUE, "tree_resin_glue");
		
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
		
	    //================================================================================
	    //                                Bow Item Parts
	    //================================================================================
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), 
				new Object[] { " / ", "/  ", " / ", '/', Items.STICK});
	
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1),
				new Object[] { " / ", "/@ ", " / ", '/', Items.STICK, '@', ModItems.TREE_RESIN_GLUE});
		
	    //================================================================================
	    //                                  Bow Items
	    //================================================================================
		
		final ItemStack bowStringStack = new ItemStack(ModItems.BOW_STRING_ITEM, 1, OreDictionary.WILDCARD_VALUE);
		final ItemStack woolItemStack = new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, OreDictionary.WILDCARD_VALUE);

		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_PLAIN), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '@', bowStringStack});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_LEATHER_GRIP), 
				new Object[] { "#/@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '#', Items.LEATHER, '@', bowStringStack});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_WOOLEN_GRIP), 
				new Object[] { "#/@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '#', woolItemStack, '@', bowStringStack});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_LEATHER_GRIP), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 2), '@', bowStringStack});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_WOOLEN_GRIP), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 3), '@', bowStringStack});
		
		
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_PLAIN), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '@', ModItems.BOW_STRING_ITEM});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_LEATHER_GRIP), 
				new Object[] { "#/@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '#', Items.LEATHER, '@', bowStringStack});
	
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_WOOLEN_GRIP), 
				new Object[] { "#/@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '#', woolItemStack, '@', bowStringStack});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_LEATHER_GRIP), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 4), '@', bowStringStack});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_WOOLEN_GRIP), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 5), '@', bowStringStack});
		
	    
		// ========================== Alternative bow recipes ===========================
		
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_PLAIN), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '@', Items.STRING});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_LEATHER_GRIP), 
				new Object[] { "  @", "#/@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '#', Items.LEATHER, '@', Items.STRING});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_WOOLEN_GRIP), 
				new Object[] { "  @", "#/@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '#', woolItemStack, '@', Items.STRING});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_LEATHER_GRIP), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 2), '@', Items.STRING});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.SIMPLE_BOW_WOOLEN_GRIP), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 3), '@', Items.STRING});
		
		
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_PLAIN), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '@', Items.STRING});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_LEATHER_GRIP), 
				new Object[] { "  @", "#/@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '#', Items.LEATHER, '@', Items.STRING});
	
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_WOOLEN_GRIP), 
				new Object[] { "  @", "#/@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '#', woolItemStack, '@', Items.STRING});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_LEATHER_GRIP), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 4), '@', Items.STRING});
	
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.RECURVE_BOW_WOOLEN_GRIP), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 5), '@', Items.STRING});
		
		
		//================================================================================
	    //                                 Support Items
	    //================================================================================
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.BOW_STRING_ITEM), new Object[] {Items.STRING, Items.STRING, Items.STRING});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 1), 
				new Object[] { "#x", "y ", 'x', new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 0), '#', woolItemStack, 'y', Items.GLASS_BOTTLE});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 1), 
				new Object[] { "#x", "y ", 'x', new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 0), '#', Items.PAPER, 'y', Items.GLASS_BOTTLE});
    
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 2),
				new Object[] { "x ", "o#", 'x', new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 1), 'o', Items.BOWL, '#', new ItemStack(Items.COAL, 1, 0)});

		GameRegistry.addShapedRecipe(new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 2),
				new Object[] { "x ", "o#", 'x', new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 1), 'o', Items.BOWL, '#', new ItemStack(Items.COAL, 1, 1)});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 3),
				new Object[] { "xy", 'x', new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 2), 'y', Items.GLASS_BOTTLE});
		
        GameRegistry.addSmelting(new ItemStack(ModItems.TREE_RESIN_LIQUID, 1, 3), new ItemStack(ModItems.TREE_RESIN_GLUE), 0.35F);
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