package yooksi.archcraft.item;

import net.minecraft.item.Item;
import yooksi.archcraft.init.ModItemGroup;

public class ModItem extends Item {

	public ModItem() {
		super(new Item.Properties().group(ModItemGroup.MAIN));
	}
}
