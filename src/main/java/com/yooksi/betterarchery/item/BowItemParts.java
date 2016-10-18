package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.BetterArchery;
import com.yooksi.betterarchery.common.Logger;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
    } 
	
	public static enum ItemPartType implements ItemSubtype
    {
    	TYPE_BODY_SIMPLE_PLAIN(0, "simple_bow_body_item"),
    	TYPE_BODY_RECURVE_PLAIN(1, "recurve_bow_body_item");
    
		private final int metadata;
    	private final String unlocalizedName;
    	
    	private ItemPartType(int meta, String name)
    	{
    		this.unlocalizedName = name;
    		this.metadata = meta;
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
    }
}
