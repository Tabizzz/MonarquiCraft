package me.tabizzz.monarquicraft.Listeners;

import me.tabizzz.monarquicraft.MonarquiCraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemMendEvent;

public class PlayerFigthListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void ItemDamage(PlayerItemDamageEvent event) {
		var worldname = event.getPlayer().getWorld().getName();
		if(MonarquiCraft.getPlugin().getMcConfig().noDurabilityWorld(worldname)) {
			event.setCancelled(true);
			event.setDamage(0);
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void ItemMend(PlayerItemMendEvent event) {
		var worldname = event.getPlayer().getWorld().getName();
		if(MonarquiCraft.getPlugin().getMcConfig().noDurabilityWorld(worldname)) {
			event.setCancelled(true);
			event.setRepairAmount(0);
		}
	}
}
