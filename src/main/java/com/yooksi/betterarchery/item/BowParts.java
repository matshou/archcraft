package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.BetterArchery;

import net.minecraft.item.Item;

public class BowParts extends Item
{
	/*
	 *  Should only be initialized privately, once.
	 */
	private static final BowParts bowParts;
	private final BowPartLimb bowPartLimb;
	
	public static final BowPartLimb.SimpleBowLimb SIMPLE_BOW_LIMB;
	
	private BowParts()
	{
		setCreativeTab(BetterArchery.tabBetterArchery);
		bowPartLimb = new BowPartLimb();
	}
	
	public class BowPartLimb extends BowParts
	{
		private BowPartLimb()
		{
			this.maxStackSize = 5;
		}
		
		public class SimpleBowLimb extends BowParts {}
	}
	
	static
	{
		bowParts = new BowParts();
		SIMPLE_BOW_LIMB = bowParts.bowPartLimb.new SimpleBowLimb();
	}
}
