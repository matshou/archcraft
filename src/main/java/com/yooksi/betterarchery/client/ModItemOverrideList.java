package com.yooksi.betterarchery.client;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class ModItemOverrideList extends ItemOverrideList
{
	public ModItemOverrideList(List<ItemOverride> overridesIn) 
	{
		super(overridesIn);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity)
    {
		net.minecraft.item.Item item = stack.getItem();
		if (item != null && item.hasCustomProperties())
		{
			ResourceLocation location = applyOverride(stack, world, entity);
			if (location != null)
			{
				/*
				 *  Create and return custom model here,
				 *  since the override model is inheriting elements from the parent model.
				 */
				
				ModelManager manager = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager();
				ArchersBowModel customModel = new ArchersBowModel(manager.getModel(ModelLoader.getInventoryVariant(location.toString())));
				return customModel;
			}
		}
		return originalModel;
    }
}
