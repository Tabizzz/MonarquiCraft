package me.tabizzz.monarquicraft.Listeners;

import com.archyx.aureliumskills.util.armor.ArmorEquipEvent;
import me.tabizzz.monarquicraft.Config.Messages;
import me.tabizzz.monarquicraft.Data.MCPlayer;
import me.tabizzz.monarquicraft.Items.MCItem;
import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import me.tabizzz.monarquicraft.Utils.MessageUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemLevelListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEquip(ArmorEquipEvent event) {
		var player = MCPlayer.get(event.getPlayer());
		var item = event.getNewArmorPiece();
		checkItem(player, item, event);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		var player = MCPlayer.get(event.getPlayer());
		var item = player.getPlayer().getInventory().getItemInMainHand();
		checkItem(player, item, event);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlace(BlockPlaceEvent event) {
		var player = MCPlayer.get(event.getPlayer());
		var item = event.getItemInHand();
		checkItem(player, item, event);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onAttack(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			var player = MCPlayer.get((Player) event.getDamager());
			var item = player.getPlayer().getInventory().getItemInMainHand();
			checkItem(player, item, event);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onShoot(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			var player = MCPlayer.get((Player) event.getEntity());
			var item = event.getBow();
			checkItem(player, item, event);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInteract(PlayerInteractEvent event) {
		if (event.useItemInHand() == Event.Result.DENY) return;
		var player = MCPlayer.get(event.getPlayer());
		var item = player.getPlayer().getInventory().getItemInMainHand();
		if (checkItem(player, item, event)) {
			event.setUseItemInHand(Event.Result.DENY);
		}
	}

	private boolean checkItem(MCPlayer player, ItemStack item, Cancellable event) {
		if (item != null && item.getType() != Material.AIR && MCItemUtils.isMCItem(item)) {
			var mcitem = new MCItem(item);
			if (mcitem.isCanlevel() && player.getLevel() < mcitem.getLevel()) {
				event.setCancelled(true);
				player.getPlayer().sendMessage(MessageUtils.parseMessage(Messages.itemRequireLevel, player.getPlayer(), item, "level", mcitem.getLevel() + ""));
				return true;
			}
		}
		return false;
	}
}
