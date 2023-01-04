package me.tabizzz.monarquicraft.Listeners;

import com.destroystokyo.paper.event.inventory.PrepareGrindstoneEvent;
import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;

@SuppressWarnings("deprecation")
public class InventoryListener implements Listener {

	@EventHandler
	public void OnAnvil(PrepareAnvilEvent event) {
		var res = event.getResult();
		if(res == null || res.getType() == Material.AIR) return;
		if(MCItemUtils.isMCItem(res)) {
			MCItemUtils.updateLore(res);
		}
		else if(MCItemUtils.canBeMCItem(res)){
			res = MCItemUtils.createFromVanilla(res).getItem();
		}
		event.setResult(res);
	}

	@EventHandler
	public void OnEnchant(EnchantItemEvent event) {
		if(event.isCancelled()) return;
		var res = event.getItem().clone();
		if(res == null || res.getType() == Material.AIR) return;
		if(!MCItemUtils.isMCItem(res)) return;

		var meta = res.getItemMeta();
		if (meta == null) return;
		for (var enchant: event.getEnchantsToAdd().entrySet()) {
			meta.addEnchant(enchant.getKey(), enchant.getValue(), false);
		}
		res.setItemMeta(meta);
		MCItemUtils.updateLore(res);
		var realmeta = event.getItem().getItemMeta();
		realmeta.setLore(res.getItemMeta().getLore());
		event.getItem().setItemMeta(realmeta);
	}

	@EventHandler
	public void OnNetherite(PrepareSmithingEvent event) {
		var res = event.getResult();
		if(res == null || res.getType() == Material.AIR) return;
		if(MCItemUtils.canBeMCItem(res) || MCItemUtils.isMCItem(res))
			event.setResult(MCItemUtils.createFromVanilla(res).getItem());
	}

	@EventHandler
	public void OnGrindstone(PrepareGrindstoneEvent event)
	{
		var res = event.getResult();
		if(res == null || res.getType() == Material.AIR) return;
		if(MCItemUtils.isMCItem(res)) {
			MCItemUtils.updateLore(res);
		}
		else if(MCItemUtils.canBeMCItem(res)){
			res = MCItemUtils.createFromVanilla(res).getItem();
		}
		event.setResult(res);
	}

	@EventHandler
	public void OnCraft(PrepareItemCraftEvent event) {
		var res = event.getInventory().getResult();
		if(res == null || res.getType() == Material.AIR) return;
		if (MCItemUtils.canBeMCItem(res))
			event.getInventory().setResult(MCItemUtils.createFromVanilla(res).getItem());
	}

	@EventHandler
	public void OnCreative(InventoryCreativeEvent event) {
		var item = event.getCursor();
		if (item == null || item.getType() == Material.AIR) return;
		if (MCItemUtils.canBeMCItem(item) && !MCItemUtils.isMCItem(item))
			event.setCursor(MCItemUtils.createFromVanilla(item).getItem());
	}
}
