package me.tabizzz.monarquicraft.Data;

import me.tabizzz.monarquicraft.Config.ConfigAccessor;
import me.tabizzz.monarquicraft.Config.Messages;
import me.tabizzz.monarquicraft.MonarquiCraft;
import me.tabizzz.monarquicraft.Utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {
	private final JavaPlugin plugin;
	private final Map<UUID, MCPlayer> playerData;

	public PlayerManager(JavaPlugin plugin) {
		this.plugin = plugin;
		playerData = new ConcurrentHashMap<>();
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
				MonarquiCraft.getPlugin().getLogger().info("Loading saved data for player " + player.getName());
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
					player.sendMessage(MessageUtils.parseMessage(Messages.playerItemsForClaim, player));
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
				MonarquiCraft.getPlugin().getLogger().info("Saving data for player " + player.getName());
				var file = new ConfigAccessor(plugin, "data/players/" + player.getUniqueId());
				var config = file.getConfig();

				//clear old data
				for (var old : config.getKeys(false)) {
					config.set(old, null);
				}

				config.set("reservedItems", mcplayer.reservedItems);

				file.saveConfig();
				playerData.remove(player.getUniqueId());
			}
		}.runTaskAsynchronously(plugin);
	}

	public void saveAll() {
		for (var player: playerData.entrySet()) {
			unload(player.getValue().player);
		}
	}
}
