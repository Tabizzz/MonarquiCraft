package me.tabizzz.monarquicraft.Items;

import me.tabizzz.monarquicraft.Config.ConfigAccessor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
	private final Map<String, ItemStack> loadedItems;
	private final JavaPlugin plugin;

	public ItemRegistry(JavaPlugin plugin) {
		this.plugin = plugin;
		loadedItems = new HashMap<>();
		var files = new File(plugin.getDataFolder(), "Items");
		if(!files.exists()){
			try {
				Files.createDirectory(files.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ItemStack getItem(String id) {
		if(loadedItems.containsKey(id)) {
			return loadedItems.get(id);
		}
		return null;
	}

	public void clearAll() {
		loadedItems.clear();
	}

	public void loadFromFiles() {
		plugin.getLogger().info("Loading Custom Items");
		var files = new File(plugin.getDataFolder(), "Items");
		if(files.exists() && files.isDirectory()) {
			var itemFiles = files.listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));
			for (var item: itemFiles != null ? itemFiles : new File[0]) {
				var config = new ConfigAccessor(plugin, "Items/" + item.getName());
				var id = item.getName().substring(0, item.getName().lastIndexOf('.'));
				var newItem = ItemLoader.load(config.getConfig(), id);
				loadedItems.put(id, newItem);
			}
			plugin.getLogger().info("Loaded " + (itemFiles != null ? itemFiles.length : 0) + " items");
		}
	}

	public Collection<String> idSet() {
		return loadedItems.keySet();
	}
}
