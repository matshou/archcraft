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
		this.setHasSubtypes(true);
		this.setMaxStackSize(5);
		this.setMaxDamage(0);
	}

	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + ItemPartType.getTypeByMeta(stack.getMetadata()).unlocalizedName;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, java.util.List<ItemStack> subItems)
    {
    	subItems.add(new ItemStack(itemIn, 1, 0));     // TYPE_BODY_SIMPLE_PLAIN
        subItems.add(new ItemStack(itemIn, 1, 1));     // TYPE_BODY_RECURVE_PLAIN
        
        subItems.add(new ItemStack(itemIn, 1, 2));     // TYPE_BODY_SIMPLE_WITH_GRIP
        subItems.add(new ItemStack(itemIn, 1, 3));     // TYPE_BODY_RECURVE_WITH_GRIP

        subItems.add(new ItemStack(itemIn, 1, 4));     // TYPE_BODY_RECURVE_WITH_LEATHER_GRIP
        subItems.add(new ItemStack(itemIn, 1, 5));     // TYPE_BODY_RECURVE_WITH_WOOLEN_GRIP
    } 
	
	public static enum ItemPartType implements ItemSubtype
    {
    	TYPE_BODY_SIMPLE_PLAIN(0, "simple_bow_body_plain", null),
    	TYPE_BODY_RECURVE_PLAIN(1, "recurve_bow_body_plain", null),
    	
    	TYPE_BODY_SIMPLE_WITH_LEATHER_GRIP(2, "simple_bow_body_with_grip", new Color(107, 46, 22)),
    	TYPE_BODY_SIMPLE_WITH_WOOLEN_GRIP(3, "simple_bow_body_with_grip", new Color(255, 255, 255)),
    	
    	TYPE_BODY_RECURVE_WITH_LEATHER_GRIP(4, "recurve_bow_body_with_grip", new Color(107, 46, 22)),
    	TYPE_BODY_RECURVE_WITH_WOOLEN_GRIP(5, "recurve_bow_body_with_grip", new Color(255, 255, 255));
    	
		private final int metadata;
    	private final String unlocalizedName;
    	private final Color subtypeColor;
    	
    	private ItemPartType(int meta, String name, Color color)
    	{
    		this.metadata = meta;
    		this.unlocalizedName = name;
    		this.subtypeColor = color;
    	}
    	
    	public int getTypeMetadata()
    	{
    		return metadata;
    	}
    	
    	public ModelResourceLocation getModelResourceLocation()
		{
			return new ModelResourceLocation(BetterArchery.MODID + ":" + modelDir + "/" + unlocalizedName);
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
    		
    		Logger.error("Tried to get ItemPartType with an unregistered metadata value"); 
    		throw new IllegalArgumentException();
    	}
    	
    	@SideOnly(Side.CLIENT)
    	public static class ColorHandler implements IItemColor 
    	{
    		@Override
    		public int getColorFromItemstack(ItemStack stack, int tintIndex) 
    		{
    			int meta = stack.getMetadata();
    			return tintIndex == 1 && meta > 1 ? getTypeByMeta(meta).subtypeColor.getRGB() : -1;
    		}
    		
    		public static void registerColorHandler()
    		{
    			ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
    			itemColors.registerItemColorHandler(new ColorHandler(), ModItems.BOW_ITEM_PART_BODY);
    		}
    	}
    }
}
