package yooksi.archcraft.init;

import java.util.function.Supplier;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import yooksi.archcraft.ArchCraft;

/**
 * ModItemGroup is an ItemGroup (previously called {@code CreativeTab} child for our mod
 * that gives us a dedicated tab in the creative menu that contains all our mod items.
 * @see
 * <a href="https://cadiboo.github.io/tutorials/1.15.1/forge/1.7-itemgroup/#setup--util-code">
 *  ItemGroup - Setup & Util code (Cadiboo)</a>
 */
@mcp.MethodsReturnNonnullByDefault
public class ModItemGroup extends ItemGroup {

	/** Main item group for this mod used for all items */
	public static final ItemGroup MAIN =
			new ModItemGroup(ArchCraft.MODID, () -> new ItemStack(Items.BOW));

	/**
	 * Here we use a {@link java.util.function.Supplier Supplier} because need to delay
	 * creation of icon {@link ItemStack} because ItemGroups are created before any Items are
	 * registered. The icon is not needed before the first time the {@code ItemGroup} is rendered
	 * (by which time Items will have been registered). If we try to make a new ItemStack with an
	 * Item when we create our ItemGroup, we will get an error because all Items are {@code null}
	 * at this time and trying to use them will cause a {@code NullPointerException}.
	 */
	private final Supplier<ItemStack> iconSupplier;

	public ModItemGroup(final String label, Supplier<ItemStack> iconSupplier) {

		super(label);
		this.iconSupplier = iconSupplier;
	}

	@Override
	public ItemStack createIcon() {
		return iconSupplier.get();
	}
}
