package com.yooksi.betterarchery.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SimpleBow extends ArchersBow
{
	public SimpleBow(BowItemVariant variant)
	{
		super(variant);
		this.setMaxDamage(150);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, java.util.List<String> info, boolean par4) 
	{		
		info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "Such simple, so right.");
		
		switch(ArchersBow.getBowItemVariant(stack.getItem())) {

		case BOW_LEATHER_GRIP:
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "With a leather grip.");
			break;
			
		case BOW_WOOLEN_GRIP:	
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "With a woolen grip.");
		
		default: 
			break;   // BOW_PLAIN or null 	
		}	
	}
}