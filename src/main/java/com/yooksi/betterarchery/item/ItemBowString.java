package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.BetterArchery;

import net.minecraft.item.Item;

public class ItemBowString extends Item
{
	public ItemBowString()
	{
		this.setCreativeTab(BetterArchery.creativeTab);
		this.setUnlocalizedName("archers_bow_string");
		this.setMaxDamage(400);
	}
}
