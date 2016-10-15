package com.yooksi.betterarchery.network;

import com.yooksi.betterarchery.common.BetterArchery;
import com.yooksi.betterarchery.common.CommonProxy;
import com.yooksi.betterarchery.init.ModItems;
import com.yooksi.betterarchery.item.ArchersBow;
import com.yooksi.betterarchery.item.BowItemPartBody;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);

		ModelLoader.setCustomModelResourceLocation(ModItems.SIMPLE_BOW_PLAIN, 0, new ModelResourceLocation(ModItems.SIMPLE_BOW_PLAIN.getRegistryName().toString()));
		ModelLoader.setCustomModelResourceLocation(ModItems.SIMPLE_BOW_LEATHER_GRIP, 0, new ModelResourceLocation(ModItems.SIMPLE_BOW_LEATHER_GRIP.getRegistryName().toString()));
		
		ModelLoader.setCustomModelResourceLocation(ModItems.RECURVE_BOW_PLAIN, 0, new ModelResourceLocation(ModItems.RECURVE_BOW_PLAIN.getRegistryName().toString()));
		ModelLoader.setCustomModelResourceLocation(ModItems.RECURVE_BOW_LEATHER_GRIP, 0, new ModelResourceLocation(ModItems.RECURVE_BOW_LEATHER_GRIP.getRegistryName().toString()));
		
		ModelLoader.setCustomModelResourceLocation(ModItems.BOW_ITEM_PART_BODY, 0, new ModelResourceLocation(BetterArchery.MODID + BowItemPartBody.modelDir + BowItemPartBody.BodyType.getTypeNameByMeta(0)));
		ModelLoader.setCustomModelResourceLocation(ModItems.BOW_ITEM_PART_BODY, 1, new ModelResourceLocation(BetterArchery.MODID + BowItemPartBody.modelDir + BowItemPartBody.BodyType.getTypeNameByMeta(1)));
	}
}