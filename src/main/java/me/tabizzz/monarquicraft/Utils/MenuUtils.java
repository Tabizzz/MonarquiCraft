package me.tabizzz.monarquicraft.Utils;

import me.tabizzz.monarquicraft.Items.MCItem;
import me.tabizzz.monarquicraft.MonarquiCraft;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;
import java.util.function.Function;

public class MenuUtils {
	public static Consumer<InventoryClickEvent> editable(Function<MCItem, Boolean> condition, Consumer<Inventory> consumer) {
		return event -> {
			var toset = event.getCursor();
			Bukkit.getPlayer("LaKokopai").sendMessage("1");
			if(MCItemUtils.isMCItem(toset)) {
				Bukkit.getPlayer("LaKokopai").sendMessage("2");
				var mcitem = new MCItem(toset);
				if (toset.isSimilar(event.getCurrentItem()) || condition.apply(mcitem)) {
					Bukkit.getPlayer("LaKokopai").sendMessage("3");
					Bukkit.getScheduler().runTask(MonarquiCraft.getPlugin(), () -> consumer.accept(event.getInventory()));
					return;
				}
			} else if (toset == null || (toset != null && toset.getType() == Material.AIR)) {
				Bukkit.getPlayer("LaKokopai").sendMessage("4");
				Bukkit.getScheduler().runTask(MonarquiCraft.getPlugin(), () -> consumer.accept(event.getInventory()));
				return;
			}
			Bukkit.getPlayer("LaKokopai").sendMessage("5");
			event.setCancelled(true);
		};
	}
}
