package com.yooksi.betterarchery.item;

import com.yooksi.betterarchery.common.BetterArchery;

import net.minecraft.item.Item;

public class BowParts extends Item
{
	/*
	 *  Should only be initialized privately, once.
	 */
	private static final BowParts bowParts;
	private static final BowPartBody bowPartLimb;
	
	public static final BowPartBody.SimpleBowBody SIMPLE_BOW_BODY;
	
	private BowParts()
	{
		setCreativeTab(BetterArchery.tabBetterArchery);
	}
	
	public class BowPartBody extends BowParts
	{
		private BowPartBody()
		{
			this.maxStackSize = 5;
		}
		
		public class SimpleBowBody extends BowParts {}
	}
	
	static
	{
		bowParts = new BowParts();
		bowPartLimb = bowParts.new BowPartBody();
		SIMPLE_BOW_BODY = bowPartLimb.new SimpleBowBody();
	}
}
