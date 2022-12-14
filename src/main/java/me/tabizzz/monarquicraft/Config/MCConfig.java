package me.tabizzz.monarquicraft.Config;

import org.bukkit.plugin.java.JavaPlugin;

public class MCConfig {

	private final ConfigAccessor config;

	public MCConfig(JavaPlugin plugin) {
		config = new ConfigAccessor(plugin, "config.yml");
		config.saveDefaultConfig();
		config.reloadConfig();
		config.saveConfig();
	}

	public void reload() {
		config.reloadConfig();
		var file = config.getConfig();
	}
}
