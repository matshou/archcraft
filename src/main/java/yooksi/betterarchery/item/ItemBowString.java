package yooksi.betterarchery.item;

import net.minecraft.item.Item;
import yooksi.betterarchery.common.BetterArchery;

public class ItemBowString extends Item
{
	public ItemBowString()
	{
		this.setCreativeTab(BetterArchery.creativeTab);
		this.setMaxDamage(400);
	}
}
