package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.Logger;
import com.yooksi.betterarchery.init.ModItems;
import com.yooksi.betterarchery.item.ItemColorHandler.ArcheryColor;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBowPartBody extends Item
{
	public ItemBowPartBody()
	{
		this.setHasSubtypes(true);
		this.setMaxStackSize(5);
		this.setMaxDamage(0);
	}

	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + BodyPartType.getTypeByMeta(stack.getMetadata()).unlocalizedName;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, java.util.List<ItemStack> subItems)
    {
    	for (BodyPartType type : BodyPartType.values())
    	{
    		ItemStack stack = new ItemStack(itemIn, 1, type.metadata);
    		stack.setTagCompound(new NBTTagCompound());
    		subItems.add(stack);
    	}
    } 
	
	public static enum BodyPartType implements ItemSubtype
    {
    	TYPE_BODY_SIMPLE_PLAIN(0, "simple_bow_body", ArcheryColor.COLOR_NULL),
    	TYPE_BODY_RECURVE_PLAIN(1, "recurve_bow_body", ArcheryColor.COLOR_NULL),
    	TYPE_BODY_LONG_PLAIN(2, "longbow_body", ArcheryColor.COLOR_NULL),
    	
    	TYPE_BODY_SIMPLE_WITH_LEATHER_GRIP(3, TYPE_BODY_SIMPLE_PLAIN, ArcheryColor.COLOR_LEATHER),
    	TYPE_BODY_SIMPLE_WITH_WOOLEN_GRIP(4, TYPE_BODY_SIMPLE_PLAIN, ArcheryColor.COLOR_WOOL),
    	
    	TYPE_BODY_RECURVE_WITH_LEATHER_GRIP(5, TYPE_BODY_RECURVE_PLAIN, ArcheryColor.COLOR_LEATHER),
    	TYPE_BODY_RECURVE_WITH_WOOLEN_GRIP(6, TYPE_BODY_RECURVE_PLAIN, ArcheryColor.COLOR_WOOL),
		
		TYPE_BODY_LONG_WITH_LEATHER_GRIP(7, TYPE_BODY_LONG_PLAIN, ArcheryColor.COLOR_LEATHER),
		TYPE_BODY_LONG_WITH_WOOLEN_GRIP(8, TYPE_BODY_LONG_PLAIN, ArcheryColor.COLOR_WOOL);
		
		private final int metadata;
    	private final String unlocalizedName;
    	private final ArcheryColor subtypeColor;
    	
    	private static final BodyPartType[] META_LOOKUP = new BodyPartType[values().length];

		BodyPartType(int meta, String name, ArcheryColor color)
    	{
    		this.metadata = meta;
    		this.unlocalizedName = name;    // used as model file name as well
    		this.subtypeColor = color;
    	}
    	
		BodyPartType(int meta, BodyPartType parent, ArcheryColor color)
    	{
			this(meta, parent.unlocalizedName, color);
    	}
    	
    	public int getTypeMetadata()
    	{
    		return metadata;
    	}
    	
    	public ModelResourceLocation getModelResourceLocation()
		{
    		String registryName = ModItems.BOW_ITEM_PART_BODY.getRegistryName().toString();
			return new ModelResourceLocation(registryName + "/" + unlocalizedName);
		}
       	
    	/**
		 *  Returns a decimal color value <i>(accepted by Minecraft)</i> of the variant, 
		 *  or <b>-1</b> if no color.  
		 */
		protected int getColorRGB()
		{
			return subtypeColor.getColor();
		}
    	
    	/** 
    	 * Get bow item part type from metadata value. <br>
    	 * 
    	 * @param meta used to distinguish different item subtypes.  
    	 * @throws IllegalArgumentException if argument in an unregistered value.
    	 */
    	protected static BodyPartType getTypeByMeta(int meta)
    	{
    		if (meta < 0 || meta >= META_LOOKUP.length)
    		{
    			Logger.error("Tried to get ItemPartType with an unregistered metadata value");
    			throw new IllegalArgumentException();
    		}
    		else return META_LOOKUP[meta];
    	}
    	
    	static
    	{
    		for (BodyPartType type : BodyPartType.values())
    			META_LOOKUP[type.metadata] = type;
    	}
    }

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		/*
		 *  This method is called several times BEFORE we had a chance to create a new NBTTagCompound.
		 */
		return stack.hasTagCompound() ? stack.getTagCompound().getInteger("item_damage") > 0 : false;
	}
	
	@Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
		ArchersBow craftingOutput = ArchersBow.getCraftingOutputFor(BodyPartType.getTypeByMeta(stack.getMetadata()));
		double maxDamage = craftingOutput != null ? craftingOutput.getMaxDamage() : 0;
		
		return (double)stack.getTagCompound().getInteger("item_damage") / maxDamage;
    }
}
