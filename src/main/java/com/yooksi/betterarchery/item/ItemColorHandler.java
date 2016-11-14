package com.yooksi.betterarchery.item;

import java.awt.Color;

import javax.annotation.Nullable;

import com.yooksi.betterarchery.client.ArchersBowModel;
import com.yooksi.betterarchery.init.ModItems;
import com.yooksi.betterarchery.item.ItemBowPartBody.BodyPartType;

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
	/**
	 *  All colors used by this mod are stored here for convenient access.
	 */
	public enum ArcheryColor
	{
		COLOR_LEATHER(new Color(107, 46, 22)),
		COLOR_WOOL(new Color(238, 238, 238)),
		COLOR_NULL(null);

		private final Color color;

		ArcheryColor(@Nullable Color color)
		{
			this.color = color;
		}


		/**
		 *  Returns a decimal color value <i>(accepted by Minecraft)</i> 
		 *  of the variant, or <b>-1</b> if no color.  
		 */
		public int getColor()
		{
			return this != COLOR_NULL ? this.color.getRGB() : -1;
		}
	}

	@SideOnly(Side.CLIENT)
	public static class BowColorHandler implements IItemColor 
	{	
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) 
		{
			if (tintIndex == ArchersBowModel.GRIP_TINT_INDEX)
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
			if (tintIndex == ArchersBowModel.GRIP_TINT_INDEX && stack.getMetadata() > 2)
			{
				/*
				 *  The color value from NBT will always override default subtype color.
				 */
				if (stack.hasTagCompound() && stack.getTagCompound().hasKey("dyeColorMeta"))
				{
					int dyeColorMeta = stack.getTagCompound().getInteger("dyeColorMeta");
					return EnumDyeColor.byMetadata(dyeColorMeta).getMapColor().colorValue;
				}
				else return BodyPartType.getTypeByMeta(stack.getMetadata()).getColorRGB();
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

		java.util.List<Item> colorVars = new java.util.ArrayList<Item>();
		for (BodyPartType type : BodyPartType.values())
		{
			/*
			 *  Find all bow variants that are assigned a valid color.
			 */
			if (type.getColorRGB() != -1)
				colorVars.add(ArchersBow.getCraftingOutputFor(type));
		}

		itemColors.registerItemColorHandler(new BowColorHandler(), colorVars.toArray(new Item[colorVars.size()]));
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
