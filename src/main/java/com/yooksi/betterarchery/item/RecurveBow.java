package com.yooksi.betterarchery.item;

public class RecurveBow extends ArchersBow
{
	public RecurveBow(BowItemVariant variant)
	{
		super(variant);
		
		this.setMaxDamage(256);
		
		pullingSpeedMult = 0.85F;
		arrowSpeedMult = 1.20F;
	}
}
