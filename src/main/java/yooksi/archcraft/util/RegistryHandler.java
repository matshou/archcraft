package yooksi.archcraft.util;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yooksi.archcraft.ArchCraft;
import yooksi.archcraft.init.ModItems;

@SuppressWarnings("unused")
public class RegistryHandler {

	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, modid="archcraft")
	public static class Events {

		@SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {

			ArchCraft.LOGGER.info("Registering mod items...");
			event.getRegistry().registerAll(ModItems.getAll());
		}
	}
}
