package me.tabizzz.monarquicraft.Executables;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ExecutableManager {

	public static void registerEvents(PluginManager manager, JavaPlugin plugin) {
		manager.registerEvents(new BrujulaMagica(), plugin);
	}
}
