package me.tabizzz.monarquicraft.Commands;

import com.archyx.aureliumskills.acf.BaseCommand;
import com.archyx.aureliumskills.acf.annotation.*;
import com.archyx.aureliumskills.util.item.ItemUtils;
import com.loohp.interactivechat.api.InteractiveChatAPI;
import com.loohp.interactivechat.libs.net.kyori.adventure.text.Component;
import com.loohp.interactivechat.utils.InteractiveChatComponentSerializer;
import me.tabizzz.monarquicraft.Data.MCPlayer;
import me.tabizzz.monarquicraft.Items.StatRoller;
import me.tabizzz.monarquicraft.MonarquiCraft;
import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("mc|monarquicraft")
public class MonarquiCraftCommand extends BaseCommand {

	@Subcommand("reload")
	@CommandPermission("mc.reload")
	@Description("Recarga el plugin.")
	public static void reload() {
		MonarquiCraft.getPlugin().reload();
	}

	@Subcommand("items give")
	@CommandCompletion("@players @items")
	@CommandPermission("mc.items.give")
	@Description("Da un item custom.")
	public static void ItemGive(@Flags("other") Player player, String id) {
		var item = MonarquiCraft.getPlugin().getItemRegistry().getItem(id);
		MCItemUtils.give(player, item);
	}

	@Subcommand("claim")
	@Description("Reclama tus items pendientes")
	public static void ItemClaim(Player sender) {
		MCPlayer.get(sender).claim();
	}

	@Subcommand("items lore")
	@CommandPermission("mc.items.lore")
	@Description("Regenera el lore de un item.")
	public static void ItemLore(Player sender) {
		var item = sender.getInventory().getItemInMainHand();
		MCItemUtils.updateLore(item);
		sender.getInventory().setItemInMainHand(item);
	}

	@Subcommand("items gen")
	@CommandCompletion("@players @material @range:0-100")
	@CommandPermission("mc.items.gen")
	@Description("Da un item con estadisticas generadas al azar.")
	public static void RandomStatsGive(@Flags("other") Player player, Material material, int level) {
		var item = new ItemStack(material);
		var res = StatRoller.Reroll(item, player, level);
		player.getInventory().addItem(res);
	}

	@Subcommand("items reroll")
	@CommandCompletion("@players @range:0-100")
	@CommandPermission("mc.items.reroll")
	@Description("Genera nuevamente stats para el item en tu mano.")
	public static void RandomStatsReroll(@Flags("other") Player player, int level) {
		var item = player.getInventory().getItemInMainHand();
		var res = StatRoller.Reroll(item, player, level);
		player.getInventory().setItemInMainHand(res);
	}


	@Subcommand("offhand")
	@Description("Intercambia el objeto de tu mano principal con la mano secundaria")
	public static void Offhand(Player sender) {
		var main = sender.getInventory().getItemInMainHand();
		var off = sender.getInventory().getItemInOffHand();

		sender.getInventory().setItemInMainHand(off);
		sender.getInventory().setItemInOffHand(main);
	}
}
