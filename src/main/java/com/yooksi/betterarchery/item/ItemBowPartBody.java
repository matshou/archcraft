package com.yooksi.betterarchery.item;

import java.awt.Color;

import com.yooksi.betterarchery.common.Logger;
import com.yooksi.betterarchery.init.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBowPartBody extends Item
{
	/**
	 *  List of bows crafted from these parts.
	 */
	private final ArchersBow[] craftingProducts;
	
	public ItemBowPartBody()
	{
		this.setHasSubtypes(true);
		this.setMaxStackSize(5);
		this.setMaxDamage(0);

		/*
		 *  These items are being used to properly display durability damage,
		 *  check #getDurabilityForDisplay to see how they are used.
		 */
		craftingProducts = new ArchersBow[]
		{ 	
			ModItems.SIMPLE_BOW_PLAIN,
			ModItems.RECURVE_BOW_PLAIN, 
			ModItems.SIMPLE_BOW_LEATHER_GRIP, 
			ModItems.SIMPLE_BOW_WOOLEN_GRIP,
			ModItems.RECURVE_BOW_LEATHER_GRIP, 
			ModItems.RECURVE_BOW_WOOLEN_GRIP
		};
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
    	TYPE_BODY_SIMPLE_PLAIN(0, "simple_bow_body_plain", null),
    	TYPE_BODY_RECURVE_PLAIN(1, "recurve_bow_body_plain", null),
    	
    	TYPE_BODY_SIMPLE_WITH_LEATHER_GRIP(3, "simple_bow_body_with_grip", new Color(107, 46, 22)),
    	TYPE_BODY_SIMPLE_WITH_WOOLEN_GRIP(4, "simple_bow_body_with_grip", new Color(255, 255, 255)),
    	
    	TYPE_BODY_RECURVE_WITH_LEATHER_GRIP(5, "recurve_bow_body_with_grip", new Color(107, 46, 22)),
    	TYPE_BODY_RECURVE_WITH_WOOLEN_GRIP(6, "recurve_bow_body_with_grip", new Color(255, 255, 255));
		
		private final int metadata;
    	private final String unlocalizedName;
    	private final Color subtypeColor;
    	
    	private BodyPartType(int meta, String name, Color color)
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
    		String registryName = ModItems.BOW_ITEM_PART_BODY.getRegistryName().toString();
			return new ModelResourceLocation(registryName + "/" + unlocalizedName);
		}
       	
    	/**
		 *  Returns a decimal color value <i>(accepted by Minecraft)</i> of the variant, 
		 *  or <b>-1</b> if no color.  
		 */
		public int getColorRGB()
		{
			return subtypeColor.getRGB();
		}
    	
    	/** 
    	 * Get bow item part type from metadata value. <br>
    	 * 
    	 * @param meta used to distinguish different item subtypes.  
    	 * @throws IllegalArgumentException if argument in an unregistered value.
    	 */
    	public static BodyPartType getTypeByMeta(int meta)
    	{
    		for (BodyPartType type : BodyPartType.values())
    		{
    			if (type.metadata == meta)
    				return type;
    		}
    		
    		Logger.error("Tried to get ItemPartType with an unregistered metadata value"); 
    		throw new IllegalArgumentException();
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
		double maxDamage = craftingProducts[stack.getMetadata()].getMaxDamage();
		return (double)stack.getTagCompound().getInteger("item_damage") / maxDamage;
    }
}
