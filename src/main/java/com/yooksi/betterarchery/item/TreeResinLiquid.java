package com.yooksi.betterarchery.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class TreeResinLiquid extends ItemGlassBottle
{
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		/*
		 *  Prevent the item from being used in the world.
		 */ 
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
    }
}
