package com.yooksi.betterarchery.init;

import com.yooksi.betterarchery.common.BetterArchery;
import com.yooksi.betterarchery.item.*;

/** All custom items are initialized and stored here. */
public class Items 
{
	public static final RegularBow REGULAR_BOW;

	static
	{
		REGULAR_BOW = ArchersBow.initNewBowType(new RegularBow());
	}
}
