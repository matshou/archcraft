package com.yooksi.betterarchery.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RecurveBow extends ArchersBow
{
	public RecurveBow(BowItemVariant variant)
	{
		super(variant);
		
		this.setMaxDamage(250);
		
		pullingSpeedMult = 0.85F;
		arrowSpeedMult = 1.20F;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, java.util.List<String> info, boolean par4) 
	{		
		info.add("A swift, true-firing bow that almost aims itself.");
		
		switch(ArchersBow.getBowItemVariant(stack.getItem())) {

		case RECURVE_BOW_LEATHER_GRIP:
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "It has a leather grip.");
			break;
			
		case RECURVE_BOW_WOOLEN_GRIP:
		{
			String colorName = ItemColorHandler.getDyeColorNameForStack(stack);		
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + (colorName == "pink" ? 
					"It holds the power of the pink sheep clan." : "It has a " + (colorName != "unknown" ? 
							colorName + " colored woolen grip." : "woolen grip")));
		}
		default:   // BOW_PLAIN or null 
			break;   	
		}	
	}
}
