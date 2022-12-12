package me.tabizzz.monarquicraft.Config;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MCConfig {

	private final ConfigAccessor config;
	List<String> noDurabilityWorlds;

	public MCConfig(JavaPlugin plugin) {
		config = new ConfigAccessor(plugin, "config.yml");
		noDurabilityWorlds = new ArrayList<>();
	}

	public void reload() {
		config.reloadConfig();
		var file = config.getConfig();

		var nodurabilityworlds = file.getStringList("nodurabilityworlds");
		noDurabilityWorlds.clear();
		noDurabilityWorlds.addAll(nodurabilityworlds);
	}

	public boolean noDurabilityWorld(String name) {
		return noDurabilityWorlds.contains(name);
	}
}
