package com.yooksi.betterarchery.common;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BetterArcheryReborn .MODID, version = BetterArcheryReborn.VERSION, name= BetterArcheryReborn.NAME, acceptedMinecraftVersions = "[1.10,1.10.2]")

public class BetterArcheryReborn 
{
	public static final String MODID = "betterarchery";
	public static final String NAME = "Better Archery Reborn";
    public static final String VERSION = "0.1.0";
    
    /** The instance of our mod that Forge uses. */
	@Mod.Instance(MODID)
	public static BetterArcheryReborn  instance;

    public static CommonProxy proxy = new CommonProxy();
	
	/** 
	 *  Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry. 
	 *  @throws IOException if a serious I/O error has occurred.
	 */
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) throws java.io.IOException 
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
		// Register event handler here.
    }
}