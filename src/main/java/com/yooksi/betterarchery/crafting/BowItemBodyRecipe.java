package com.yooksi.betterarchery.crafting;

import javax.annotation.Nullable;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BowItemBodyRecipe extends ShapedOreRecipe
{
	public BowItemBodyRecipe(ItemStack result, Object... recipe)
	{
		super(result, recipe);
	}

	@Nullable
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		ItemStack recipeOutput = super.getRecipeOutput();

		NBTTagCompound nbt = new NBTTagCompound();
		recipeOutput.setTagCompound(nbt);
		
		return super.getCraftingResult(inv);
	}
}
