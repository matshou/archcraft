package com.yooksi.betterarchery.init;

import com.yooksi.betterarchery.item.*;
import com.yooksi.betterarchery.item.ArchersBow.BowItemVariant;

/** 
 *  All custom items are initialized and stored here. <br>
 *  <i>Except bow parts, which are initialized and stored in item.BowParts.<i> 
 */
public class ModItems 
{
	public static final SimpleBow SIMPLE_BOW_PLAIN;
	public static final SimpleBow SIMPLE_BOW_LEATHER_GRIP;
	
	public static final RecurveBow RECURVE_BOW_PLAIN;
	public static final RecurveBow RECURVE_BOW_LEATHER_GRIP;

	public static final BowItemPartBody BOW_ITEM_PART_BODY;
	
	static
	{
		SIMPLE_BOW_PLAIN = ArchersBow.initNewBowType(new SimpleBow(BowItemVariant.BOW_PLAIN));
		SIMPLE_BOW_LEATHER_GRIP = ArchersBow.initNewBowType(new SimpleBow(BowItemVariant.BOW_LEATHER_GRIP));
		
		RECURVE_BOW_PLAIN = ArchersBow.initNewBowType(new RecurveBow(BowItemVariant.BOW_PLAIN));
		RECURVE_BOW_LEATHER_GRIP = ArchersBow.initNewBowType(new RecurveBow(BowItemVariant.BOW_LEATHER_GRIP));
		
		BOW_ITEM_PART_BODY = new BowItemPartBody();
	}
}