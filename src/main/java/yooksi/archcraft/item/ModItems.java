package yooksi.archcraft.init;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import yooksi.archcraft.ArchCraft;
import yooksi.archcraft.item.ModItem;

public enum ModItems {

	TREE_RESIN_GLUE(new ModItem(), "tree_resin_glue");

	private final Item item;

	ModItems(ModItem item, String name) {
		this.item = item.setRegistryName(new ResourceLocation(ArchCraft.MODID, name));
	}

	public static Item[] get() {

		final ModItems[] values = ModItems.values();
		Item[] items = new Item[values.length];

		for (int i = 0; i < values.length; i++) {
			items[i] = values[i].item;
		}
		return items;
	}
}
