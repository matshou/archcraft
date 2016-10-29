package com.yooksi.betterarchery.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLongBow extends ArchersBow
{
	public ItemLongBow(BowItemVariant variant)
	{
		super(variant, 0.60F, 1.35F);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, java.util.List<String> info, boolean par4) 
	{		
		info.add("In the right hands it's reach can touch the coulds.");
		
		switch(ArchersBow.getBowItemVariant(stack.getItem())) {

		case LONG_BOW_LEATHER_GRIP:
			info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "It has a leather grip.");
			break;
			
		case LONG_BOW_WOOLEN_GRIP:
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
