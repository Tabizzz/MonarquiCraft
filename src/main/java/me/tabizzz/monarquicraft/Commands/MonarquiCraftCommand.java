package me.tabizzz.monarquicraft.Commands;

import com.archyx.aureliumskills.acf.BaseCommand;
import com.archyx.aureliumskills.acf.annotation.*;
import me.tabizzz.monarquicraft.Items.StatRoller;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("mc|monarquicraft")
public class MonarquiCraftCommand extends BaseCommand {

	@Subcommand("items gen")
	@CommandCompletion("@players @material @range:1-100")
	@CommandPermission("mc.items.gen")
	@Description("Da un item con estadisticas generadas al azar.")
	public static void RandomStatsGive(@Flags("other") Player player, Material material, int level) {
		var item = new ItemStack(material);
		var res = StatRoller.Reroll(item, player, level);
		player.getInventory().addItem(res);
	}

	@Subcommand("items reroll")
	@CommandCompletion("@players @range:1-100")
	@CommandPermission("mc.items.reroll")
	@Description("Genera nuevamente stats para el item en tu mano.")
	public static void RandomStatsReroll(@Flags("other") Player player, int level) {
		var item = player.getInventory().getItemInMainHand();
		var res = StatRoller.Reroll(item, player, level);
		player.getInventory().setItemInMainHand(res);
	}

	@Subcommand("items statcap")
	@CommandCompletion("@range:1-100")
	@Description("Genera nuevamente stats para el item en tu mano.")
	public static void RandomStatcap(Player sender, int level) {
		sender.sendMessage("" + StatRoller.MaxStatPerItem(level));
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
