package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.BetterArchery;
import com.yooksi.betterarchery.common.Logger;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TreeResinLiquid extends net.minecraft.item.ItemGlassBottle
{
	public TreeResinLiquid()
	{
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setContainerItem(this);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
    {
		/*
		 *  Don't destroy the bottle when we're filtering the liquid.
		 */
		return new ItemStack(ResinLiquidType.isLiquidInpure(itemStack) ? Items.GLASS_BOTTLE : null);
    }
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		/*
		 *  Prevent the item from being used in the world.
		 */ 
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
    }
	
	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + ResinLiquidType.getTypeByMeta(stack.getMetadata()).unlocalizedName;
    }
 
	@SideOnly(Side.CLIENT)   
	public void getSubItems(net.minecraft.item.Item itemIn, CreativeTabs tab, java.util.List<ItemStack> subItems)    
	{	
		subItems.add(new ItemStack(itemIn, 1, 0));     // LIQUID_TYPE_INPURE
	    subItems.add(new ItemStack(itemIn, 1, 1));     // LIQUID_TYPE_PURE
	    //subItems.add(new ItemStack(itemIn, 1, 2));     // LIQUID_TYPE_MIXED
	}
	
	public static enum ResinLiquidType implements ItemSubtype
    {
		LIQUID_TYPE_INPURE(0, "tree_resin_liquid_inpure"), 
		LIQUID_TYPE_PURE(1, "tree_resin_liquid_pure"); 
		//LIQUID_TYPE_MIXED(2, "tree_resin_liquid_mixed");

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
        
        
		public ModelResourceLocation getModelResourceLocation()
		{
			return new ModelResourceLocation(BetterArchery.MODID + ":" + unlocalizedName);
		}
		
		private static boolean isLiquidInpure(ItemStack liquid)
		{
			return getTypeByMeta(liquid.getMetadata()) == LIQUID_TYPE_INPURE;
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
