package com.yooksi.betterarchery.item;

import javax.annotation.Nullable;

import com.yooksi.betterarchery.common.BetterArchery;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** 
 * An abstract parent class for all custom bows in this mod. <br>
 * Every bow should extend this class and be initialized with {@link #initNewBowType}.
 */
public abstract class ArchersBow<I extends ArchersBow> extends ItemBow
{	
	/** This multiplier modifies the duration of the bows pulling animation.  */
	protected float pullBackMult = 1.0F;
	
	/** 
	 * Perform custom bow initialization after it's been created. <br>  
	 * This initialization protocol should be followed by all custom bows.
	 * 
	 * @param <T> has to be a valid custom bow type
	 * @param customBow newly created instance of the custom bow
	 */
	public static <T extends ArchersBow> T initNewBowType(T customBow)
	{
		addPropertyOverrides(customBow);
		customBow.setCreativeTab(BetterArchery.tabBetterArchery);
		return customBow;
	}
	
	/** 
	 * This is what enables the bow to play the pull-back animation.
	 * 
	 * @param <T> has to be a valid custom bow type
	 * @param item your custom bow instance <i>(not cast)</i>.  
	 */
	protected static <T extends ArchersBow> void addPropertyOverrides(T item)
	{
		item.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
        	@SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn != null)
                {
                    ItemStack itemstack = entityIn.getActiveItemStack();
                    int useCount = entityIn.getItemInUseCount();
                    final int maxUseDuration = stack.getMaxItemUseDuration();
                    
                    /* 
                     * Change the speed of the pulling animation here. The animation is divided into three stages,
                     * and the speed of every stage is exponentially increased as they progress.
                     * That's why we use a multiplier, instead of directly increasing or decreasing the value.
                     */
                    
                    float pullBackSpeed = itemstack != null && itemstack.getItem() == item ? (float)(maxUseDuration - useCount) / 20.0F : 0.0F;
                    return pullBackSpeed *= ((ArchersBow)item).pullBackMult;
                }
                else return 0.0F;
            }
        });
        item.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
        	@SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
	}
}