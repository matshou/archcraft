package yooksi.archcraft.item;

import net.minecraft.item.Item;
import yooksi.archcraft.init.ModItemGroups;

public class ModItem extends Item {

	public ModItem() {
		super(new Item.Properties().group(ModItemGroups.MAIN));
	}
}
