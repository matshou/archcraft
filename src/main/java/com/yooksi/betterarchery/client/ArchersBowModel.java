package com.yooksi.betterarchery.client;

import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;

@SuppressWarnings("deprecation")
public class ArchersBowModel implements IPerspectiveAwareModel
{
	private final ImmutableList<BakedQuad> itemLayers;
	private final IBakedModel oldBakedModel;
	
	/**
	 *  The model will constructed as a complete copy of the oldModel, <br>
	 *  except the list of texture layers, which will be modified in the constructor.
	 *  
	 * @param oldModel the model from which to inherit properties
	 */
	public ArchersBowModel(IBakedModel oldModel)
	{	
		ImmutableList<BakedQuad> layers = (ImmutableList<BakedQuad>) oldModel.getQuads(null, null, 0);
		List<BakedQuad> tempList = new java.util.ArrayList<BakedQuad>();
		
		UnmodifiableIterator<BakedQuad> iter;
		for (iter = layers.iterator(); iter.hasNext();)
		{
			BakedQuad layer = iter.next();
			
			if (layer.getTintIndex() != 1)   // The bow grip
				tempList.add(layer);
		}
		
		this.itemLayers = ImmutableList.copyOf(tempList);
		this.oldBakedModel = oldModel;
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) 
	{
		return itemLayers;
	}

	@Override
	public boolean isAmbientOcclusion() 
	{
		return oldBakedModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() 
	{
		return oldBakedModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() 
	{
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() 
	{
		return oldBakedModel.getParticleTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() 
	{
		return oldBakedModel.getItemCameraTransforms();
	}

	@Override
	public ItemOverrideList getOverrides() 
	{
		return oldBakedModel.getOverrides();
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) 
	{
		if (oldBakedModel instanceof IPerspectiveAwareModel) 
		{
			Matrix4f matrix4f = ((IPerspectiveAwareModel)oldBakedModel).handlePerspective(cameraTransformType).getRight();
			return Pair.of(this, matrix4f);
		} 
		else 
		{
			// If the base model isn't an IPerspectiveAware, we'll need to generate the 
			// correct matrix ourselves using the ItemCameraTransforms.

			ItemCameraTransforms itemCameraTransforms = oldBakedModel.getItemCameraTransforms();
			ItemTransformVec3f itemTransformVec3f = itemCameraTransforms.getTransform(cameraTransformType);
			TRSRTransformation tr = new TRSRTransformation(itemTransformVec3f);
			
			Matrix4f mat = tr != null ? tr.getMatrix() : null;
			
			// The TRSRTransformation for vanilla items have blockCenterToCorner() applied, however handlePerspective
			// reverses it back again with blockCornerToCenter(), so we don't need to apply it here.

			return Pair.of(this, mat);
		}
	}
}
