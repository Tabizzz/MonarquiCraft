package me.tabizzz.monarquicraft.Executables;

import com.archyx.aureliumskills.nbtapi.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import me.tabizzz.monarquicraft.Config.Messages;
import me.tabizzz.monarquicraft.Items.MCItem;
import me.tabizzz.monarquicraft.MonarquiCraft;
import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import me.tabizzz.monarquicraft.Utils.MessageUtils;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

public class BrujulaMagica implements Listener
{
	public static void updateLocation(Player sender, ItemStack compass) {
		if(MCItemUtils.isMCItem(compass) && compass.getType() == Material.COMPASS && compass.getItemMeta() instanceof CompassMeta meta) {
			var mcitem = new MCItem(compass);
			if(mcitem.getId().equals("brujulamagica")) {
				var nbt = new NBTItem(compass, true);
				var mc = nbt.getOrCreateCompound("MonarquiCraft");
				if(mc.hasKey("bmTarget")) {
					var target = mc.getUUID("bmTarget");
					var player = MonarquiCraft.getPlugin().getServer().getPlayer(target);
					if(player != null) {
						var hasBounty = PlaceholderAPI.setPlaceholders(player, "%deluxecombat_has_bounty%").equals("true");
						if(hasBounty) {
							meta.setLodestone(player.getLocation());
							meta.setLodestoneTracked(false);
							compass.setItemMeta(meta);
							if(!player.getWorld().getName().equals(sender.getWorld().getName())) {
								sender.sendMessage(MessageUtils.parseMessage(Messages.bmTargetDimension, player));
							}
							sender.playSound(Sound.sound(org.bukkit.Sound.ITEM_LODESTONE_COMPASS_LOCK.key(), Sound.Source.PLAYER, 1f, 1f));
						} else {
							sender.sendMessage(MessageUtils.parseMessage(Messages.bmExpiredTarget, player));
						}
					} else {
						sender.sendMessage(MessageUtils.parseMessage(Messages.bmOfflineTarget));
					}
				} else {
					sender.sendMessage(MessageUtils.parseMessage(Messages.bmNoTarget));
				}
			}
		}
	}

	public static void setTarget(Player sender, Player target) {
		var hasBounty = PlaceholderAPI.setPlaceholders(target, "%deluxecombat_has_bounty%").equals("true");
		if(hasBounty) {
			var item = sender.getInventory().getItemInMainHand();
			if(MCItemUtils.isMCItem(item)) {
				var mcitem = new MCItem(item);
				if(mcitem.getId().equals("brujulamagica")) {
					mcitem.getLore().set(5, MonarquiCraft.getPlugin().getMessagesConfig().
							getMessage(Messages.bmLoreReplacement).replace("<player>", target.getName()));
					item = mcitem.getItem();
					var nbt = new NBTItem(item, true);
					var mc = nbt.getOrCreateCompound("MonarquiCraft");
					mc.setUUID("bmTarget", target.getUniqueId());
					item = nbt.getItem();
					updateLocation(sender, item);
					sender.getInventory().setItemInMainHand(item);
				} else {
					sender.sendMessage(MessageUtils.parseMessage(Messages.bmRequired));
				}
			} else {
				sender.sendMessage(MessageUtils.parseMessage(Messages.bmRequired));
			}
		}
		else {
			sender.sendMessage(MessageUtils.parseMessage(Messages.bmPLayerNoBounty, target));
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR) {
			var item = event.getPlayer().getInventory().getItemInMainHand();
			if (item != null && item.getType() == Material.COMPASS) {
				updateLocation(event.getPlayer(), item);
				event.getPlayer().getInventory().setItemInMainHand(item);
			}
		}
	}
}
