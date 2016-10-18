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
		info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "A swift, true-firing bow that almost aims itself.");
		
		switch(ArchersBow.getBowItemVariant(stack.getItem())) {

		case BOW_LEATHER_GRIP:
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "Equipped with a leather grip.");
			break;
			
		case BOW_WOOLEN_GRIP:	
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "Equipped with a woolen grip.");
		
		default: 
			break;   // BOW_PLAIN or null 	
		}	
	}
}
