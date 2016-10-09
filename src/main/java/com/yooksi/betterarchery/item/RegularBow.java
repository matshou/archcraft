package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.BetterArchery;

import net.minecraft.item.ItemBow;

public class RegularBow extends ItemBow
{
	public static RegularBow localInstance;
	
	public RegularBow()
	{
		// setMaxStackSize(1);
		// setMaxDamage(384);
		
        setCreativeTab(BetterArchery.tabBetterArchery);
        localInstance = this;
	}
}