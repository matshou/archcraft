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
		info.add("An effective weapon made from a single piece of wood.");
		
		switch(ArchersBow.getBowItemVariant(stack.getItem())) {

		case SIMPLE_BOW_PLAIN:
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "Such simple, so right.");
			break;

		case SIMPLE_BOW_LEATHER_GRIP:
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "It has a leather grip.");
			break;
			
		case SIMPLE_BOW_WOOLEN_GRIP:
		{
			String colorName = ItemColorHandler.getDyeColorNameForStack(stack);		
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + (colorName == "pink" ? 
					"It holds the power of the pink sheep clan." : "It has a " + (colorName != "unknown" ? 
							colorName + " colored woolen grip." : "woolen grip")));
		}
		default:    // null 
			break;   	
		}	
	}
}