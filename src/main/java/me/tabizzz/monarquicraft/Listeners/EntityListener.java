package me.tabizzz.monarquicraft.Listeners;

import me.tabizzz.monarquicraft.Menus.InspectMenu;
import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityListener implements Listener {
	@EventHandler
	public void OnDrop(EntityDropItemEvent event) {
		if(event.isCancelled()) return;
		var res = event.getItemDrop().getItemStack();
		if(res == null) return;
		if(MCItemUtils.canBeMCItem(res))
			event.getItemDrop().setItemStack(MCItemUtils.createFromVanilla(res).getItem());
	}

	@EventHandler
	public void OnInteract(PlayerInteractEntityEvent event) {
		if(event.isCancelled()) return;
		var entity = event.getRightClicked();
		if(entity instanceof Player target) {
			if (target == null || !Bukkit.getOnlinePlayers().contains(target)) return;

			var sneak = event.getPlayer().isSneaking();
			if (sneak && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
				InspectMenu.Open(event.getPlayer(), target);
			}
		}
	}
}
