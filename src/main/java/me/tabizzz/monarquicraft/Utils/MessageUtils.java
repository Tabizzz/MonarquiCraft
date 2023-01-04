package me.tabizzz.monarquicraft.Utils;

import me.tabizzz.monarquicraft.Config.Messages;
import me.tabizzz.monarquicraft.MonarquiCraft;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MessageUtils {

	public static Component parseMessage(Messages message, Player player, ItemStack item, String replace, String value) {
		var raw = MonarquiCraft.getPlugin().getMessagesConfig().getMessage(message);
		raw = raw.replace("&", "§").replace(replace, value);
		if(player != null) raw = raw.replace("<player>", player.getName());
		if(item != null && player!= null) {
			var split = Component.text(raw);
			return split.replaceText(TextReplacementConfig.builder().matchLiteral("<item>").replacement(MCItemUtils.createDisplay(player, item)).build());
		} else {
			return Component.text(raw);
		}
	}

	public static Component parseMessage(Messages message, Player player, ItemStack item) {
		var raw = MonarquiCraft.getPlugin().getMessagesConfig().getMessage(message);
		raw = raw.replace("&", "§");
		if(player != null) raw = raw.replace("<player>", player.getName());
		if(item != null && player!= null) {
			var split = Component.text(raw);
			return split.replaceText(TextReplacementConfig.builder().matchLiteral("<item>").replacement(MCItemUtils.createDisplay(player, item)).build());
		} else {
			return Component.text(raw);
		}
	}

	public static Component parseMessage(Messages message, Player player) {
		var raw = MonarquiCraft.getPlugin().getMessagesConfig().getMessage(message);
		raw = raw.replace("&", "§");
		if(player != null) raw = raw.replace("<player>", player.getName());
		return Component.text(raw);
	}

	public static Component parseMessage(Messages message, Player player, String replace, String value) {
		var raw = MonarquiCraft.getPlugin().getMessagesConfig().getMessage(message);
		raw = raw.replace("&", "§").replace(replace, value);
		if(player != null) raw = raw.replace("<player>", player.getName());
		return Component.text(raw);
	}

	public static Component parseMessage(Messages message) {
		var raw = MonarquiCraft.getPlugin().getMessagesConfig().getMessage(message);
		raw = raw.replace("&", "§");
		return Component.text(raw);
	}

}
