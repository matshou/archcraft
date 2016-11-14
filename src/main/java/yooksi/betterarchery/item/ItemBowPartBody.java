package yooksi.betterarchery.item;

import java.util.Collections;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yooksi.betterarchery.common.Logger;
import yooksi.betterarchery.init.ModItems;
import yooksi.betterarchery.item.ItemColorHandler.ArcheryColor;

public class ItemBowPartBody extends Item
{
	public ItemBowPartBody()
	{
		this.setHasSubtypes(true);
		this.setMaxStackSize(5);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return "item." + BodyPartType.getTypeByMeta(stack.getMetadata()).typeName;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, java.util.List<ItemStack> subItems)
	{
		for (BodyPartType type : BodyPartType.values())
		{
			ItemStack stack = new ItemStack(itemIn, 1, type.metadata);
			stack.setTagCompound(new NBTTagCompound());
			subItems.add(stack);
		}
	} 

	public static enum BodyPartType implements ItemSubtype
	{
		TYPE_BODY_SIMPLE_PLAIN(0, "simple_bow_body", ArcheryColor.COLOR_NULL),
		TYPE_BODY_RECURVE_PLAIN(1, "recurve_bow_body", ArcheryColor.COLOR_NULL),
		TYPE_BODY_LONG_PLAIN(2, "longbow_body", ArcheryColor.COLOR_NULL),

		TYPE_BODY_SIMPLE_WITH_LEATHER_GRIP(3, "simple_bow_body", TYPE_BODY_SIMPLE_PLAIN, ArcheryColor.COLOR_LEATHER),
		TYPE_BODY_SIMPLE_WITH_WOOLEN_GRIP(4, "simple_bow_body", TYPE_BODY_SIMPLE_PLAIN, ArcheryColor.COLOR_WOOL),

		TYPE_BODY_RECURVE_WITH_LEATHER_GRIP(5, "recurve_bow_body", TYPE_BODY_RECURVE_PLAIN, ArcheryColor.COLOR_LEATHER),
		TYPE_BODY_RECURVE_WITH_WOOLEN_GRIP(6, "recurve_bow_body", TYPE_BODY_RECURVE_PLAIN, ArcheryColor.COLOR_WOOL),

		TYPE_BODY_LONG_WITH_LEATHER_GRIP(7, "longbow_body", TYPE_BODY_LONG_PLAIN, ArcheryColor.COLOR_LEATHER),
		TYPE_BODY_LONG_WITH_WOOLEN_GRIP(8, "longbow_body", TYPE_BODY_LONG_PLAIN, ArcheryColor.COLOR_WOOL);


		private final int metadata;
		private final BodyPartType parent;
		private final String typeName;             // used as unlocalized name and model file name
		private final ArcheryColor subtypeColor;

		private static final BodyPartType[] META_LOOKUP = new BodyPartType[values().length];
		private static final java.util.List<BodyPartType> PARENT_TYPES = new java.util.ArrayList<BodyPartType>();

		BodyPartType(int meta, String name, BodyPartType parent, ArcheryColor color)
		{
			this.metadata = meta;
			this.parent = parent;
			this.typeName = name;
			this.subtypeColor = color;
		}

		BodyPartType(int meta, String name, ArcheryColor color)    // parents constructor
		{
			this(meta, name, null, color);
		}

		public int getTypeMetadata()
		{
			return metadata;
		}

		public ModelResourceLocation getModelResourceLocation(boolean pseudo)
		{
			String fileName = (pseudo && parent == null ? typeName + "_pseudo" : typeName);
			return new ModelResourceLocation(ModItems.BOW_ITEM_PART_BODY.getRegistryName().toString() + "/" + fileName);
		}

		public static java.util.List<BodyPartType> getParents()
		{
			return Collections.unmodifiableList(PARENT_TYPES);
		}

		/**
		 *  Returns a decimal color value <i>(accepted by Minecraft)</i> of the variant, 
		 *  or <b>-1</b> if no color.  
		 */
		protected int getColorRGB()
		{
			return subtypeColor.getColor();
		}

		/** 
		 * Get bow item part type from metadata value. <br>
		 * 
		 * @param meta used to distinguish different item subtypes.  
		 * @throws IllegalArgumentException if argument in an unregistered value.
		 */
		protected static BodyPartType getTypeByMeta(int meta)
		{
			if (meta < 0 || meta >= META_LOOKUP.length)
			{
				Logger.error("Tried to get ItemPartType with an unregistered metadata value");
				throw new IllegalArgumentException();
			}
			else return META_LOOKUP[meta];
		}

		static
		{
			for (BodyPartType type : BodyPartType.values())
			{
				META_LOOKUP[type.metadata] = type;

				if (type.parent == null)
					PARENT_TYPES.add(type);
			}
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return stack.getTagCompound().getInteger("item_damage") > 0;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		BodyPartType type = BodyPartType.getTypeByMeta(stack.getMetadata());
		ArchersBow craftingOutput = ArchersBow.getCraftingOutputFor(type);

		double maxDamage = craftingOutput != null ? craftingOutput.getMaxDamage() : 0;
		return (double)stack.getTagCompound().getInteger("item_damage") / maxDamage;
	}
}
