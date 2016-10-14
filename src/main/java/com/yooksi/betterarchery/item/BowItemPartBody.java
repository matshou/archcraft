package com.yooksi.betterarchery.item;

import java.util.List;

import com.yooksi.betterarchery.common.BetterArchery;
import com.yooksi.betterarchery.common.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BowItemPartBody extends Item
{
	/** Lets Forge know where to find body part models. */
	public static final String modelDir = ":bow_item_parts/";
	
	public BowItemPartBody()
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
        return "item." + BodyType.getTypeNameByMeta(stack.getMetadata());
    }
	
	 /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
    	subItems.add(new ItemStack(itemIn));           // BODY_TYPE_SIMPLE_PLAIN
        subItems.add(new ItemStack(itemIn, 1, 1));     // BODY_TYPE_SIMPLE_LEATHER_GRIP
    } 
	
	public static enum BodyType
    {
    	BODY_TYPE_SIMPLE_PLAIN(0, "simple_bow_body_plain"),
    	BODY_TYPE_SIMPLE_LEATHER_GRIP(1, "simple_bow_body_leather_grip");
    	
    	private final int meta;
    	private final String typeName;
    	
    	private BodyType(int meta, String name)
    	{
    		this.meta = meta;
    		typeName = name;
    	}
    	
    	/** 
    	 * Get bow body type name from metadata value. <br>
    	 * 
    	 * @param meta used to distinguish different item subtypes.  
    	 * @return name of <code>BODY_TYPE_SIMPLE_PLAIN</code> if <code>meta</code> was an unregistered value.
    	 */
    	public static String getTypeNameByMeta(int meta)
    	{
    		for (BodyType type : BodyType.values())
    		{
    			if (type.meta == meta)
    				return type.typeName;
    		}
    		
    		Logger.warn("Tried to get type name with an unregistered metadata value, "
    				+ "returning default value instead.", new IllegalArgumentException());
    		
    		return BODY_TYPE_SIMPLE_PLAIN.typeName;
    	}
    }
}
