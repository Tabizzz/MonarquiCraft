package me.tabizzz.monarquicraft.Config;

import me.tabizzz.monarquicraft.MonarquiCraft;
import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;

public class MessagesConfig {
	private final ConfigAccessor config;

	private final ConcurrentHashMap<Messages, String> messages;

	public MessagesConfig(JavaPlugin plugin) {
		config = new ConfigAccessor(plugin, "messages.yml");
		config.saveDefaultConfig();
		messages = new ConcurrentHashMap<>();
	}

	public void reload() {
		config.reloadConfig();
		var file = config.getConfig();
		var keys = file.getKeys(false);
		messages.clear();
		MonarquiCraft.getPlugin().getLogger().info("Reloading text messages.");
		for (var key : keys) {
			try {
				var message = Messages.valueOf(key);
				messages.put(message, file.getString(key));
			} catch (Exception ignored) {

			}
		}
	}
	public String getMessage(Messages message) {
		return messages.getOrDefault(message, "");
	}

}
