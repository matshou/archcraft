package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.init.ModItems;
import com.yooksi.betterarchery.item.BowItemParts.ItemPartType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorHandler
{
	@SideOnly(Side.CLIENT)
	public static class BowColorHandler implements IItemColor 
	{
		/**
		 *  A list of items that are considered color variation, 
		 *  and require to be registered with ColorHandler.
		 */
		private static final Item[] colorVariants = new Item[] 
		{
				ModItems.SIMPLE_BOW_LEATHER_GRIP,
				ModItems.RECURVE_BOW_LEATHER_GRIP,
				ModItems.SIMPLE_BOW_WOOLEN_GRIP,
				ModItems.RECURVE_BOW_WOOLEN_GRIP
		};
		
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) 
		{
			if (tintIndex == 1)
			{
				if (stack.hasTagCompound() && stack.getTagCompound().hasKey("dyeColorMeta"))
				{
					int dyeColorMeta = stack.getTagCompound().getInteger("dyeColorMeta");
					return EnumDyeColor.byMetadata(dyeColorMeta).getMapColor().colorValue;
				}
				else return ArchersBow.getBowItemVariant(stack.getItem()).getColorRGB();
			}
			else return -1;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static class BowBodyColorHandler implements IItemColor 
	{
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) 
		{
			/*
			 *  Skip first few subtypes because they don't have color values.
			 *  This is a just a bit of performance optimizing.
			 */
			if (tintIndex == 1 && stack.getMetadata() > 2)
			{
				/*
				 *  The color value from NBT will always override default subtype color.
				 */
				if (stack.hasTagCompound() && stack.getTagCompound().hasKey("dyeColorMeta"))
				{
					int dyeColorMeta = stack.getTagCompound().getInteger("dyeColorMeta");
					return EnumDyeColor.byMetadata(dyeColorMeta).getMapColor().colorValue;
				}
				else return ItemPartType.getTypeByMeta(stack.getMetadata()).getColorRGB();
			}
			else return -1;
		}
	}
	
	/**
	 *  Register all item color handlers for this mod.
	 */
	public static void registerItemColorHandlers()
	{
		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		
		itemColors.registerItemColorHandler(new BowColorHandler(), BowColorHandler.colorVariants);
		itemColors.registerItemColorHandler(new BowBodyColorHandler(), ModItems.BOW_ITEM_PART_BODY);
	}
	
	/** 
	 *  Return the name of the color assigned to this ItemStack.
	 *  @return <i>"unknown"</i> is no color was found.
	 */
	public static String getDyeColorNameForStack(ItemStack stack)
	{
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("dyeColorMeta"))
		{	
			return EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("dyeColorMeta")).getName(); 
		}
		else return "unknown";
	}
}
