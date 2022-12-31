package me.tabizzz.monarquicraft.Listeners;

import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;

public class EntityListener implements Listener {
	@EventHandler
	public void OnDrop(EntityDropItemEvent event) {
		if(event.isCancelled()) return;
		var res = event.getItemDrop().getItemStack();
		if(res == null) return;
		if(MCItemUtils.canBeMCItem(res))
			event.getItemDrop().setItemStack(MCItemUtils.createFromVanilla(res).getItem());
	}
}
