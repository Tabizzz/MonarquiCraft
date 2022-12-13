package me.tabizzz.monarquicraft.Listeners;

import me.tabizzz.monarquicraft.Data.MCPlayer;
import me.tabizzz.monarquicraft.Items.MCItem;
import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemsListener implements Listener {
	@EventHandler
	public void OnUse(PlayerInteractEvent event) {
		if(event.isCancelled()) return;
		var player = MCPlayer.get(event.getPlayer());
		var item = event.getItem();
		if(item != null && MCItemUtils.isMCItem(item)) {
			var mcitem = new MCItem(item);
			if(mcitem.isCanlevel()) {
				if(player.getLevel() < mcitem.getLevel()) {
					event.setCancelled(true);
					event.setUseInteractedBlock(Event.Result.DENY);
					event.setUseItemInHand(Event.Result.DENY);
					player.getPlayer().sendMessage("§cEste item requiere nivel §f" + mcitem.getLevel() + "§c para ser usado");
				}

			}
		}
	}
}
