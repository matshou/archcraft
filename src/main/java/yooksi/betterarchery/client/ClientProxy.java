package yooksi.betterarchery.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yooksi.betterarchery.common.CommonProxy;
import yooksi.betterarchery.init.ModItems;
import yooksi.betterarchery.item.ArchersBow;
import yooksi.betterarchery.item.ItemBowPartBody;
import yooksi.betterarchery.item.ItemColorHandler;
import yooksi.betterarchery.item.ItemSubtype;
import yooksi.betterarchery.item.ItemTreeResinLiquid;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);

		for (ItemBowPartBody.BodyPartType type : ItemBowPartBody.BodyPartType.values())
		{
			ArchersBow item = ArchersBow.getCraftingOutputFor(type);
			ModelLoader.setCustomModelResourceLocation(item, 0, item.getModelResourceLocation());
		}

		setCustomModelResourceLocationForItemSubtypes(ModItems.BOW_ITEM_PART_BODY, ItemBowPartBody.BodyPartType.values());
		setCustomModelResourceLocationForItemSubtypes(ModItems.TREE_RESIN_LIQUID, ItemTreeResinLiquid.ResinLiquidType.values());	

		ModelLoader.setCustomModelResourceLocation(ModItems.TREE_RESIN_GLUE, 0, new ModelResourceLocation(ModItems.TREE_RESIN_GLUE.getRegistryName().toString()));
		ModelLoader.setCustomModelResourceLocation(ModItems.BOW_STRING_ITEM, 0, new ModelResourceLocation(ModItems.BOW_STRING_ITEM.getRegistryName().toString()));
	}

	private void setCustomModelResourceLocationForItemSubtypes(net.minecraft.item.Item item, final Enum<? extends ItemSubtype>[] subtypes)
	{
		for (int i = 0; i < subtypes.length; i++)
		{
			ItemSubtype type = (ItemSubtype) subtypes[i];
			ModelLoader.setCustomModelResourceLocation(item, type.getTypeMetadata(), type.getModelResourceLocation(true));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		ItemColorHandler.registerItemColorHandlers();
	}
}