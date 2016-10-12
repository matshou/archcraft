package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.BetterArchery;

import net.minecraft.item.Item;

public class BowParts extends Item
{
	/*
	 *  Should only be initialized privately, once.
	 */
	private static final BowParts bowParts;
	
	public static final SimpleBowLimb SIMPLE_BOW_LIMB;
	
	private BowParts()
	{
		setCreativeTab(BetterArchery.tabBetterArchery);
	}
	
	public class SimpleBowLimb extends BowParts
	{
		private SimpleBowLimb()
		{
			this.maxStackSize = 5;
		}
	}
	
	static
	{
		bowParts = new BowParts();
		SIMPLE_BOW_LIMB = bowParts.new SimpleBowLimb();
	}
}
