package com.yooksi.betterarchery.item;

import java.awt.Color;

import javax.annotation.Nullable;

import com.yooksi.betterarchery.common.BetterArchery;
import com.yooksi.betterarchery.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** 
 * An abstract parent class for all custom bows in this mod. <br>
 * Every bow should extend this class and be initialized with {@link #initNewBowType}.
 */
public abstract class ArchersBow extends ItemBow
{	
	/** This multiplier modifies the duration of the bows pulling animation. */
	protected float pullingSpeedMult = 1.0F;
	
	/** This multiplier modifies the speed and damage of an arrow being shot from this bow. */
	protected float arrowSpeedMult = 1.0F;
	
	private final BowItemVariant variant;
	
	protected ArchersBow(BowItemVariant variant)
	{
		this.variant = variant;
	}
	
	public enum BowItemVariant
	{
		BOW_PLAIN(null), 
		BOW_CLOTH_GRIP(null),
		BOW_LEATHER_GRIP(new Color(107, 46, 22));

		/**
		 *  A list of items that are considered color variation, and require to be registered with ColorHandler.
		 */
		public static final Item[] colorVariants = new Item[] 
		{
				ModItems.SIMPLE_BOW_LEATHER_GRIP, 
				ModItems.RECURVE_BOW_LEATHER_GRIP 
		};
		
		private final Color color;
		
		BowItemVariant(@Nullable Color color)
		{
			this.color = color;
		}
		
		/**
		 *  Returns a decimal color value <i>(accepted by Minecraft)</i> of the variant, or <b>-1</b> if no color.  
		 */
		public int getColorRGB()
		{
			return color != null ? color.getRGB() : -1;
		}
	}		
	
	/**
	 *  This handler will take care of all bow item variants that require different texture colors. <br>
	 *  Register it with Minecraft using {@link #registerColorHandler()}.
	 */
	@SideOnly(Side.CLIENT)
	public static class ColorHandler implements IItemColor 
	{
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) 
		{
			BowItemVariant variant = ((ArchersBow) stack.getItem()).variant;
			return tintIndex == 1 ? variant.getColorRGB() : -1;
		}
		
		/**
		 *  Register this color handler with Minecraft for all mod bow items.
		 */
		public static void registerColorHandler()
		{
			ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
			itemColors.registerItemColorHandler(new ColorHandler(), BowItemVariant.colorVariants);
		}
	}
	
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
		customBow.setCreativeTab(BetterArchery.creativeTab);
		return customBow;
	}
	
	/** 
	 * This is what enables the bow to play the pulling animation.
	 * 
	 * @param <T> has to be a valid custom bow type
	 * @param item your custom bow instance <i>(not cast)</i>.  
	 */
	protected static <T extends ArchersBow> void addPropertyOverrides(final T item)
	{
		item.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
        	@SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn != null)
                {
                    /* Change the speed of the pulling animation here. The animation is divided into three stages,
                     * and the speed of every stage is exponentially increased as they progress.
                     * That's why we use a multiplier, instead of directly increasing or decreasing the value.
                     */
                	ItemStack itemstack = entityIn.getActiveItemStack();
                    return itemstack != null && itemstack.getItem() == item ? item.getPullingAnimationProgress(stack, entityIn) : 0.0F;
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
	
	/**
	 * Try to find ammunition this bow can fire in player's possesion. <br>
	 * First check <b>OFF_HAND</b> and <b>MAIN_HAND</b> then go through the inventory.
	 * 
	 * @return ItemStack of the ammo or <code>null</code> if no ammo was found
	 */
	private ItemStack findAmmo(EntityPlayer player)   
	{
		if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
	    {
			return player.getHeldItem(EnumHand.OFF_HAND);
	    }
	    else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
	    {
	        return player.getHeldItem(EnumHand.MAIN_HAND);
	    }
	    else
	    {
	        for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
	        {
	        	ItemStack itemstack = player.inventory.getStackInSlot(i);

	            if (this.isArrow(itemstack))
	                return itemstack;
	        }
	         
	        return null;
	    }
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer)entityLiving, i, itemstack != null || flag);
            if (i < 0) return;

            if (itemstack != null || flag)
            {
                if (itemstack == null)
                {
                    itemstack = new ItemStack(Items.ARROW);
                }

                /*  Vanilla arrow velocity value ranges from 0.0F to 1.0F.
                 *  Increasing arrow velocity will proportionally increase it's damage,
                 *  as well as the pitch of the sound played upon releasing the arrow.
                 */
                float f = getArrowVelocity(i) * arrowSpeedMult;
                
                if ((double)f >= 0.1D * arrowSpeedMult)
                {
                    boolean itemStackInfinite = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow ? ((ItemArrow)itemstack.getItem()).isInfinite(itemstack, stack, entityplayer) : false);

                    if (!worldIn.isRemote)
                    {
                        ItemArrow itemarrow = (ItemArrow)((ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
                        EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
                        entityarrow.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f >= 1.0F * arrowSpeedMult)
                            entityarrow.setIsCritical(true);

                        int enchLvlPower = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        int enchLvlPunch = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        int enchLvlFlame  = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack);
                        
                        if (enchLvlPower > 0)
                            entityarrow.setDamage(entityarrow.getDamage() + (double)enchLvlPower * 0.5D + 0.5D);

                        if (enchLvlPunch > 0)
                            entityarrow.setKnockbackStrength(enchLvlPunch);

                        if (enchLvlFlame > 0)
                            entityarrow.setFire(100);

                        stack.damageItem(1, entityplayer);

                        if (itemStackInfinite)
                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;

                        worldIn.spawnEntityInWorld(entityarrow);
                    }
                    
                    final float soundPitch = 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F;
                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, soundPitch);

                    if (!itemStackInfinite && --itemstack.stackSize == 0)  
                    	entityplayer.inventory.deleteStack(itemstack);

                    entityplayer.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
    }
	
	/**
	 * Get the current progress of the bow's pulling animation for entity. <br>
	 * <i>When pulling is in progress, the return will increase by <b>0.05</b> every tick.</i> <p>
	 * 
	 * The progress can be divided into three stages; check the method comments for more info. <br>
	 * These stages and associated values are important for updating entity FOV.
	 * 
	 * @return a value between 0 <i>(did not start pulling)</i> and 1 <i>(fully pulled)</i>.  
	 */
	public float getPullingAnimationProgress(ItemStack bow, EntityLivingBase archer)
	{
		/*
	     * pulling_0: 0.00 - 0.60 - Slightly pulled
	     * pulling_1: 0.65 - 0.85 - Moderately pulled
	     * pulling_2: 0.90 - 1.00 - Fully pulled
		 */
		
		float animationProgress = (bow != null && archer != null) ? (float)(bow.getMaxItemUseDuration() - archer.getItemInUseCount()) / 20.0F : 0.0F;
		return animationProgress > 1.0F ? 1.0F : animationProgress * pullingSpeedMult;
	}
}