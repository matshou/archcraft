package com.yooksi.betterarchery.item;

public class RecurveBow extends ArchersBow
{
	public RecurveBow()
	{
		pullingSpeedMult = 0.85F;
		arrowSpeedMult = 1.20F;
		
		this.setMaxDamage(256);
	}
	
	public class LeatherGripVariant extends RecurveBow {}
}
