package me.tabizzz.monarquicraft.Listeners;

import com.archyx.aureliumskills.util.item.ItemUtils;
import com.destroystokyo.paper.event.inventory.PrepareGrindstoneEvent;
import me.tabizzz.monarquicraft.Items.ItemLore;
import me.tabizzz.monarquicraft.Items.MCItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;

public class InventoryListener implements Listener {

	@EventHandler
	public void OnAnvil(PrepareAnvilEvent event) {
		var res = event.getResult();
		if(res == null) return;
		var mcitem = new MCItem(res);
		var lore = new ItemLore(mcitem);
		lore.writeAll();
		event.setResult(lore.getItem());
	}

	@EventHandler
	public void OnEnchant(EnchantItemEvent event) {
		if(event.isCancelled()) return;
		var res = event.getItem().clone();
		if(res == null) return;
		var meta = res.getItemMeta();
		if (meta == null) return;
		for (var enchant: event.getEnchantsToAdd().entrySet()) {
			meta.addEnchant(enchant.getKey(), enchant.getValue(), false);
		}
		res.setItemMeta(meta);
		var mcitem = new MCItem(res);
		var lore = new ItemLore(mcitem);
		lore.writeAll();
		var realmeta = event.getItem().getItemMeta();
		realmeta.setLore(lore.getItem().getItemMeta().getLore());
		event.getItem().setItemMeta(realmeta);
	}

	@EventHandler
	public void OnNetherite(PrepareSmithingEvent event) {
		var res = event.getResult();
		if(res == null) return;
		var mcitem = new MCItem(res);
		var lore = new ItemLore(mcitem);
		lore.writeAll();
		event.setResult(lore.getItem());
	}

	@EventHandler
	public void OnGrindstone(PrepareGrindstoneEvent event)
	{
		var res = event.getResult();
		if(res == null) return;
		var mcitem = new MCItem(res);
		var lore = new ItemLore(mcitem);
		lore.writeAll();
		event.setResult(lore.getItem());
	}

	@EventHandler
	public void OnCraft(PrepareItemCraftEvent event) {
		var res = event.getInventory().getResult();
		if(res == null) return;
		var mat = res.getType();
		if (!ItemUtils.isArmor(mat) && !ItemUtils.isTool(mat) && !ItemUtils.isWeapon(mat)) return;

		var mcitem = new MCItem(res);
		mcitem.setBaseattributes(true);
		mcitem.setId(res.getType().getKey().toString());
		event.getInventory().setResult(mcitem.getItem());
	}
}
