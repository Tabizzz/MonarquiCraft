package me.tabizzz.monarquicraft.Listeners;

import me.tabizzz.monarquicraft.MonarquiCraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDataListener implements Listener {
	@EventHandler
	public void OnJoin(PlayerJoinEvent event) {
		MonarquiCraft.getPlugin().getPlayerManager().load(event.getPlayer());
	}

	@EventHandler
	public void OnQuit(PlayerQuitEvent event) {
		MonarquiCraft.getPlugin().getPlayerManager().unload(event.getPlayer());
	}
}
