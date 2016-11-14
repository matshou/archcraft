package com.yooksi.betterarchery.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public interface ItemSubtype 
{
	/** 
	 *  Create and return a new instance of the texture model file resource location.
	 *  @param pseudo generate a non-existing location for parent subtype.  
	 */
	ModelResourceLocation getModelResourceLocation(boolean pseudo);

	/**
	 *  Retrieve the metadata value of this subtype.
	 */
	int getTypeMetadata();
}
