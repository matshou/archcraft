package com.yooksi.betterarchery.item;

import javax.annotation.Nullable;

import com.yooksi.betterarchery.common.BetterArchery;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RegularBow extends ItemBow
{
	public static RegularBow localInstance;
	
	public RegularBow()
	{
		// setMaxStackSize(1);
		// setMaxDamage(384);
		
        setCreativeTab(BetterArchery.tabBetterArchery);
        localInstance = this;
	
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
        	@SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                    return 0.0F;
                
                else
                {
                    ItemStack itemstack = entityIn.getActiveItemStack();
                    return itemstack != null && itemstack.getItem() == localInstance ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F : 0.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
        
        	@SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
	}
}