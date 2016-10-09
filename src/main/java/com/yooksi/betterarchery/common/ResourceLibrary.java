package com.yooksi.betterarchery.common;

import com.yooksi.betterarchery.item.*;

/** This class is a storage of unique block and item instances placed here for ease of access. */
public class ResourceLibrary
{
	public static final RegularBow REGULAR_BOW;
	
	static 
	{
		REGULAR_BOW = RegularBow.localInstance;
	}
}