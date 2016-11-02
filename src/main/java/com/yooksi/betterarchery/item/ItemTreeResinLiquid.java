package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.Logger;
import com.yooksi.betterarchery.init.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTreeResinLiquid extends Item
{
	public ItemTreeResinLiquid()
	{
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setContainerItem(this);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
    {
		/*
		 *  Don't destroy the bottle when we're filtering or mixing the liquid.
		 */
		ResinLiquidType type = ResinLiquidType.getTypeByMeta(itemStack.getMetadata());
		return new ItemStack(type == ResinLiquidType.LIQUID_TYPE_MIXED_IN_BOWL ? Items.BOWL : Items.GLASS_BOTTLE);
    }
	
	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + ResinLiquidType.getTypeByMeta(stack.getMetadata()).unlocalizedName;
    }
 
	@SideOnly(Side.CLIENT)   
	public void getSubItems(net.minecraft.item.Item itemIn, CreativeTabs tab, java.util.List<ItemStack> subItems)    
	{	
		for (ResinLiquidType type : ResinLiquidType.values())
			subItems.add(new ItemStack(itemIn, 1, type.getTypeMetadata()));
	}
	
	public static enum ResinLiquidType implements ItemSubtype
    {
		LIQUID_TYPE_INPURE(0, "tree_resin_liquid_inpure"), 
		LIQUID_TYPE_PURE(1, "tree_resin_liquid_pure"),
		
		LIQUID_TYPE_MIXED_IN_BOTTLE(2, "tree_resin_liquid_mixed"),
		LIQUID_TYPE_MIXED_IN_BOWL(3, "tree_resin_mixed_bowl");

		private final int metadata;
		private final String unlocalizedName;
		
        private ResinLiquidType(int meta, String name)
        {
        	this.metadata = meta;
        	this.unlocalizedName = name;
        }
        
        public int getTypeMetadata()
    	{
    		return metadata;
    	} 
        
		public ModelResourceLocation getModelResourceLocation(boolean pseudo)
		{
			String registryName = ModItems.TREE_RESIN_LIQUID.getRegistryName().toString();
			return new ModelResourceLocation(registryName + "/" + unlocalizedName);
		}
		
		/** 
    	 * Get item variant type from metadata value. <br>
    	 * 
    	 * @param meta used to distinguish different item subtypes.  
    	 * @throws IllegalArgumentException if argument in an unregistered value.
    	 */
    	private static ResinLiquidType getTypeByMeta(int meta)
    	{
    		for (ResinLiquidType type : ResinLiquidType.values())
    		{
    			if (type.metadata == meta)
    				return type;
    		}
    		
    		Logger.error("Tried to get ResinLiquidType with an unregistered metadata value"); 
    		throw new IllegalArgumentException();
    	}
    }
}
