package com.yooksi.betterarchery.init;

import com.yooksi.betterarchery.item.*;
import com.yooksi.betterarchery.item.ArchersBow.BowItemVariant;

/** 
 *  All custom items are initialized and stored here. <br>
 *  <i>Except bow parts, which are initialized and stored in item.BowParts.<i> 
 */
public class ModItems 
{
	public static final ItemSimpleBow SIMPLE_BOW_PLAIN;
	public static final ItemSimpleBow SIMPLE_BOW_LEATHER_GRIP;
	public static final ItemSimpleBow SIMPLE_BOW_WOOLEN_GRIP;
	
	public static final ItemRecurveBow RECURVE_BOW_PLAIN;
	public static final ItemRecurveBow RECURVE_BOW_LEATHER_GRIP;
    public static final ItemRecurveBow RECURVE_BOW_WOOLEN_GRIP;
    
    public static final ItemLongBow LONGBOW_PLAIN;
    public static final ItemLongBow LONGBOW_LEATHER_GRIP;
    public static final ItemLongBow LONGBOW_WOOLEN_GRIP;
	
	public static final ItemBowPartBody BOW_ITEM_PART_BODY;
	public static final ItemBowString BOW_STRING_ITEM;
	
	public static final ItemTreeResinLiquid TREE_RESIN_LIQUID;
	public static final ItemTreeResinGlue TREE_RESIN_GLUE;
	
	static
	{
		SIMPLE_BOW_PLAIN = ArchersBow.initNewBowType(new ItemSimpleBow(BowItemVariant.SIMPLE_BOW_PLAIN));
		SIMPLE_BOW_LEATHER_GRIP = ArchersBow.initNewBowType(new ItemSimpleBow(BowItemVariant.SIMPLE_BOW_LEATHER_GRIP));
		SIMPLE_BOW_WOOLEN_GRIP = ArchersBow.initNewBowType(new ItemSimpleBow(BowItemVariant.SIMPLE_BOW_WOOLEN_GRIP));
		
		RECURVE_BOW_PLAIN = ArchersBow.initNewBowType(new ItemRecurveBow(BowItemVariant.RECURVE_BOW_PLAIN));
		RECURVE_BOW_LEATHER_GRIP = ArchersBow.initNewBowType(new ItemRecurveBow(BowItemVariant.RECURVE_BOW_LEATHER_GRIP));
		RECURVE_BOW_WOOLEN_GRIP = ArchersBow.initNewBowType(new ItemRecurveBow(BowItemVariant.RECURVE_BOW_WOOLEN_GRIP));
		
		LONGBOW_PLAIN = ArchersBow.initNewBowType(new ItemLongBow(BowItemVariant.LONGBOW_PLAIN));
		LONGBOW_LEATHER_GRIP = ArchersBow.initNewBowType(new ItemLongBow(BowItemVariant.LONGBOW_LEATHER_GRIP));
		LONGBOW_WOOLEN_GRIP = ArchersBow.initNewBowType(new ItemLongBow(BowItemVariant.LONGBOW_WOOLEN_GRIP));
		
		BOW_ITEM_PART_BODY = new ItemBowPartBody();  // initialize after all bow items
		BOW_STRING_ITEM = new ItemBowString();
		
		TREE_RESIN_LIQUID = new ItemTreeResinLiquid();
		TREE_RESIN_GLUE = new ItemTreeResinGlue();
	}
}