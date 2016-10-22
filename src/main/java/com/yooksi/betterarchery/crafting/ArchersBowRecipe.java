package com.yooksi.betterarchery.crafting;

import javax.annotation.Nullable;

import com.yooksi.betterarchery.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ArchersBowRecipe extends ShapedOreRecipe
{
	public ArchersBowRecipe(ItemStack result, Object... recipe)
	{
		super(result, recipe);
	}

	@Nullable 
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		ItemStack recipeOutput = super.getRecipeOutput();
		
        NBTTagCompound nbt = new NBTTagCompound();
        recipeOutput.setTagCompound(nbt);
		
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
            if (stack == null) continue;

            if (stack.getItem() == ModItems.BOW_STRING_ITEM)
            	nbt.setInteger("bow_string_damage", stack.getItemDamage());

            else if (stack.getItem() == ModItems.BOW_ITEM_PART_BODY)
            {
            	recipeOutput.setItemDamage(stack.getTagCompound().getInteger("item_damage"));

            	if (stack.getTagCompound().hasKey("dyeColorMeta"))
            	{
                	int dyeColorMeta = stack.getTagCompound().getInteger("dyeColorMeta");
            		nbt.setInteger("dyeColorMeta", EnumDyeColor.byMetadata(dyeColorMeta).getMapColor().colorValue);
            	}
            }
            else if (stack.getItem() == Item.getItemFromBlock(Blocks.WOOL))
            {
            	EnumDyeColor dyeColor = EnumDyeColor.byMetadata(stack.getMetadata());
            	nbt.setInteger("dyeColorMeta", dyeColor.getMetadata());
            }
		}
		
		return super.getCraftingResult(inv);
	}
}
