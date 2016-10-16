package com.yooksi.betterarchery.item;

import java.awt.Color;

import com.yooksi.betterarchery.common.BetterArchery;
import com.yooksi.betterarchery.common.Logger;
import com.yooksi.betterarchery.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BowItemParts extends Item
{
	/** 
	 * Name of the directory where item part models are located. 
	 */
	public static final String modelDir = "bow_item_part_models";
	
	public BowItemParts()
	{
		this.setCreativeTab(BetterArchery.creativeTab);
		this.setHasSubtypes(true);
		this.setMaxStackSize(5);
		this.setMaxDamage(0);
	}

	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + ItemPartType.getTypeByMeta(stack.getMetadata()).unlocalizedName;
    }
	
	 /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, java.util.List<ItemStack> subItems)
    {
    	subItems.add(new ItemStack(itemIn, 1, 0));     // TYPE_BODY_SIMPLE_PLAIN
        subItems.add(new ItemStack(itemIn, 1, 1));     // TYPE_BODY_SIMPLE_LEATHER_GRIP
        subItems.add(new ItemStack(itemIn, 1, 2));     // TYPE_BODY_SIMPLE_WOOLEN_GRIP
        
        subItems.add(new ItemStack(itemIn, 1, 3));     // TYPE_BODY_RECURVE_PLAIN
        subItems.add(new ItemStack(itemIn, 1, 4));     // TYPE_BODY_RECURVE_LEATHER_GRIP
        subItems.add(new ItemStack(itemIn, 1, 5));     // TYPE_BODY_RECURVE_WOOLEN_GRIP
    } 
	
	public static enum ItemPartType
    {
    	TYPE_BODY_SIMPLE_PLAIN(0, "simple_bow_body_plain", "simple_bow_body_plain", null),
    	TYPE_BODY_SIMPLE_LEATHER_GRIP(1, "simple_bow_body_with_grip", "simple_bow_body_leather_grip", new Color(107, 46, 22)),
    	TYPE_BODY_SIMPLE_WOOLEN_GRIP(2, "simple_bow_body_with_grip", "simple_bow_body_woolen_grip", new Color(255, 255, 255)),

    	TYPE_BODY_RECURVE_PLAIN(3, "recurve_bow_body_plain", "recurve_bow_body_plain", null),
    	TYPE_BODY_RECURVE_LEATHER_GRIP(4, "recurve_bow_body_with_grip", "recurve_bow_body_leather_grip", new Color(107, 46, 22)),
    	TYPE_BODY_RECURVE_WOOLEN_GRIP(5, "recurve_bow_body_with_grip", "recurve_bow_body_woolen_grip", new Color(255, 255, 255));
    
		private final int metadata;
    	private final String modelFileName;
    	private final String unlocalizedName;
    	private final Color variantColor;
    	
    	private ItemPartType(int meta, String modelName, String name, Color color)
    	{
    		this.modelFileName = modelName;
    		this.unlocalizedName = name;
    		this.metadata = meta;
    		this.variantColor = color;
    	}
    	
    	public int getTypeMetadata()
    	{
    		return metadata;
    	}
    	
    	/**
		 *  Returns a decimal color value <i>(accepted by Minecraft)</i> of the variant, or <b>-1</b> if no color.  
		 */
		private int getColorRGB()
		{
			return variantColor != null ? variantColor.getRGB() : -1;
		}
    	
		/** 
         *  Create and return a new instance of the texture model file resource location.
		 */
		public ModelResourceLocation getModelResourceLocation()
		{
			return new ModelResourceLocation(BetterArchery.MODID + ":" + modelDir + "/" + modelFileName);
		}
		
    	/** 
    	 * Get bow item part type from metadata value. <br>
    	 * 
    	 * @param meta used to distinguish different item subtypes.  
    	 * @throws IllegalArgumentException if argument in an unregistered value.
    	 */
    	private static ItemPartType getTypeByMeta(int meta)
    	{
    		for (ItemPartType type : ItemPartType.values())
    		{
    			if (type.metadata == meta)
    				return type;
    		}
    		
    		Logger.error("Tried to get type with an unregistered metadata value"); 
    		throw new IllegalArgumentException();
    	}
    }
	
	/**
	 *  This handler will take care of all bow item part variants that require different texture colors. <br>
	 *  Register it with Minecraft using {@link #registerColorHandler()}.
	 */
	@SideOnly(Side.CLIENT)
	public static class ColorHandler implements IItemColor 
	{
		/**
		 *  A list of item parts that are considered color variation, and require to be registered with ColorHandler.
		 */
		private static final Item[] colorVariants = new Item[] 
		{
				ModItems.BOW_ITEM_PART_BODY
		};
		
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) 
		{
			return tintIndex == 1 ? ItemPartType.getTypeByMeta(stack.getMetadata()).getColorRGB() : -1;
		}
		
		/**
		 *  Register this color handler with Minecraft for all mod bow item parts.
		 */
		public static void registerColorHandler()
		{
			ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
			itemColors.registerItemColorHandler(new ColorHandler(), colorVariants);
		}
	}
}
