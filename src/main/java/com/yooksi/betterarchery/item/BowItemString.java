package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.BetterArchery;

import net.minecraft.item.Item;

public class BowItemString extends Item 
{
	public BowItemString()
	{
		this.setCreativeTab(BetterArchery.creativeTab);
		this.setMaxDamage(350);
	}
}
