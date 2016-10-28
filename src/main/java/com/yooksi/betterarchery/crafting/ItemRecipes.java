package com.yooksi.betterarchery.crafting;

import static net.minecraftforge.oredict.RecipeSorter.Category.SHAPED;

import com.yooksi.betterarchery.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;

public class ItemRecipes 
{
	/**
	 *  Register all custom recipes with GameRegistry.
	 */
	public static void registerRecipes()
	{
		//================================================================================
	    //                                Bow Item Parts
	    //================================================================================
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), 
				new Object[] { " / ", "/  ", " / ", '/', Items.STICK}));
	
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1),
				new Object[] { " / ", "/@ ", " / ", '/', Items.STICK, '@', ModItems.TREE_RESIN_GLUE}));
		
	    //================================================================================
	    //                                  Bow Items
	    //================================================================================
		
		final ItemStack bowStringStack = new ItemStack(ModItems.BOW_STRING_ITEM, 1, OreDictionary.WILDCARD_VALUE);
		final ItemStack woolItemStack = new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, OreDictionary.WILDCARD_VALUE);

		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_PLAIN), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '@', bowStringStack}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_LEATHER_GRIP), 
				new Object[] { "#/@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '#', Items.LEATHER, '@', bowStringStack}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_WOOLEN_GRIP), 
				new Object[] { "#/@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '#', woolItemStack, '@', bowStringStack}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_LEATHER_GRIP), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 2), '@', bowStringStack}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_WOOLEN_GRIP), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 3), '@', bowStringStack}));
		
		
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_PLAIN), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '@', ModItems.BOW_STRING_ITEM}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_LEATHER_GRIP), 
				new Object[] { "#/@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '#', Items.LEATHER, '@', bowStringStack}));
	
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_WOOLEN_GRIP), 
				new Object[] { "#/@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '#', woolItemStack, '@', bowStringStack}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_LEATHER_GRIP), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 4), '@', bowStringStack}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_WOOLEN_GRIP), 
				new Object[] { " /@", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 5), '@', bowStringStack}));
		
	    
		// ========================== Alternative bow recipes ===========================
		
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_PLAIN), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '@', Items.STRING}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_LEATHER_GRIP), 
				new Object[] { "  @", "#/@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '#', Items.LEATHER, '@', Items.STRING}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_WOOLEN_GRIP), 
				new Object[] { "  @", "#/@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 0), '#', woolItemStack, '@', Items.STRING}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_LEATHER_GRIP), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 2), '@', Items.STRING}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.SIMPLE_BOW_WOOLEN_GRIP), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 3), '@', Items.STRING}));
		
		
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_PLAIN), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '@', Items.STRING}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_LEATHER_GRIP), 
				new Object[] { "  @", "#/@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '#', Items.LEATHER, '@', Items.STRING}));
	
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_WOOLEN_GRIP), 
				new Object[] { "  @", "#/@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 1), '#', woolItemStack, '@', Items.STRING}));
		
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_LEATHER_GRIP), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 4), '@', Items.STRING}));
	
		GameRegistry.addRecipe(new ArchersBowRecipe(new ItemStack(ModItems.RECURVE_BOW_WOOLEN_GRIP), 
				new Object[] { "  @", " /@", "  @", '/', new ItemStack(ModItems.BOW_ITEM_PART_BODY, 1, 5), '@', Items.STRING}));
		
		
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
	 *  Remove vanilla recipes from the CraftingManager recipe list.
	 *
	 *  @return number of recipes removed from the recipe list. 
	 */
	public static int removeRecipesFromVanilla()
	{
		int recipesRemovedCount = 0;
		
		recipesRemovedCount += removeRecipe(Items.BOW);
		
		return recipesRemovedCount;
	}
	
	/** 
	 *  Removes vanilla recipes from the CraftingManager recipe list.
	 *  
	 *  @param toRemove Item corresponding to the output of the recipe we want to remove.
	 *  @return number of recipes we removed from the recipe list.
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
	
	/** 
	 *  Register our custom recipe classes with Forge. <br>
	 *  <i>We need to do this to make our recipes work in-game.</i>
	 */
	public static void registerRecipeTypes()
	{
		RecipeSorter.register("betterarchery:archersBowRecipe", ArchersBowRecipe.class, SHAPED, "after:forge:shapedore");
	}
}
