package com.yooksi.betterarchery.common;

import java.io.IOException;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = BetterArchery.MODID, version = BetterArchery.VERSION, name= BetterArchery.NAME, acceptedMinecraftVersions = "[1.10,1.10.2]")

public class BetterArchery 
{
	public static final String MODID = "betterarchery";
	public static final String NAME = "Better Archery Reborn";
    public static final String VERSION = "0.3.0";

	// This is where all our custom items should be listed in-game
	public static final CreativeTabs creativeTab = new CreativeTabs("BetterArchery") 
	{
		@Override
		@SideOnly(Side.CLIENT)
	    public net.minecraft.item.Item getTabIconItem()
		{ 
			return net.minecraft.init.Items.BOW; 
		}
	};
	
	@Mod.Instance(MODID)
	public static BetterArchery instance;
	
	@SidedProxy(clientSide = "com.yooksi.betterarchery.client.ClientProxy")
	
    public static CommonProxy proxy = new CommonProxy();
	
	/** 
	 *  Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry. 
	 *  @throws IOException if a serious I/O error has occurred.
	 */
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) throws IOException 
	{
	    Logger.setLogger(event.getModLog());
		Logger.createModLogFile();
		
		proxy.preInit(event);   
	}
	
	/** Do your mod setup. Build whatever data structures you care about. Register recipes. */
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.init(event);
	}
	
	/** Handle interaction with other mods, register event handlers, complete your setup based on this. */
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		//net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}