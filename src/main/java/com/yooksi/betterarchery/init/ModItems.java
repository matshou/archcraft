package com.yooksi.betterarchery.init;

import com.yooksi.betterarchery.item.*;

/** 
 *  All custom items are initialized and stored here. <br>
 *  <i>Except bow parts, which are initialized and stored in item.BowParts.<i> 
 */
public class ModItems 
{
	public static final SimpleBow SIMPLE_BOW;
	public static final RecurveBow RECURVE_BOW;

	static
	{
		SIMPLE_BOW = ArchersBow.initNewBowType(new SimpleBow());
		RECURVE_BOW = ArchersBow.initNewBowType(new RecurveBow());
	}
}