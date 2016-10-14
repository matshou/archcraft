package com.yooksi.betterarchery.init;

import com.yooksi.betterarchery.item.*;

/** 
 *  All custom items are initialized and stored here. <br>
 *  <i>Except bow parts, which are initialized and stored in item.BowParts.<i> 
 */
public class ModItems 
{
	public static final SimpleBow SIMPLE_BOW_PLAIN;
	public static final SimpleBow.LeatherGripVariant SIMPLE_BOW_LG;
	
	public static final RecurveBow RECURVE_BOW_PLAIN;
	public static final RecurveBow.LeatherGripVariant RECURVE_BOW_LG;

	public static final BowItemPartBody BOW_ITEM_PART_BODY;
	
	static
	{
		SIMPLE_BOW_PLAIN = ArchersBow.initNewBowType(new SimpleBow());
		SIMPLE_BOW_LG = ArchersBow.initNewBowType(SIMPLE_BOW_PLAIN.new LeatherGripVariant());
		
		RECURVE_BOW_PLAIN = ArchersBow.initNewBowType(new RecurveBow());
		RECURVE_BOW_LG = ArchersBow.initNewBowType(RECURVE_BOW_PLAIN.new LeatherGripVariant());
		
		BOW_ITEM_PART_BODY = new BowItemPartBody();
	}
}