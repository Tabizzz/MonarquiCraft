package me.tabizzz.monarquicraft.Data;

import me.tabizzz.monarquicraft.Config.ConfigAccessor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PlayerManager {
	private final JavaPlugin plugin;
	private final Map<UUID, MCPlayer> playerData;

	public PlayerManager(JavaPlugin plugin) {
		this.plugin = plugin;
		playerData = new HashMap<>();
	}

	public MCPlayer get(Player player) {
		if(playerData.containsKey(player.getUniqueId())) {
			return playerData.get(player.getUniqueId());
		}
		return null;
	}

	public void load(Player player){
		new BukkitRunnable() {
			@Override
			public void run() {
				var file = new ConfigAccessor(plugin, "data/players/" + player.getUniqueId());
				var config = file.getConfig();
				var mcplayer = new MCPlayer();
				mcplayer.player = player;

				var items = config.getList("reservedItems", new ArrayList<ItemStack>());

				for (var item: items) {
					if(item instanceof ItemStack) {
						mcplayer.reservedItems.add((ItemStack) item);
					}
				}

				if(mcplayer.reservedItems.size() > 0) {
					player.sendMessage(ChatColor.GREEN + "Tienes items sin reclamar en tu buzón, usa " + ChatColor.YELLOW + "/mc claim " + ChatColor.GREEN +
							"para reclamarlos");
				}

				playerData.put(player.getUniqueId(), mcplayer);
			}
		}.runTaskAsynchronously(plugin);
	}

	public void unload(Player player) {
		var mcplayer = get(player);
		if(mcplayer == null) return;
		new BukkitRunnable() {
			@Override
			public void run() {
				var file = new ConfigAccessor(plugin, "data/players/" + player.getUniqueId());
				var config = file.getConfig();

				//clear old data
				for (var old : config.getKeys(false)) {
					config.set(old, null);
				}

				config.set("reservedItems", mcplayer.reservedItems);

				file.saveConfig();
			}
		}.runTaskAsynchronously(plugin);
	}

	public void saveAll() {
		for (var player: playerData.entrySet()) {
			unload(player.getValue().player);
		}
	}
}
