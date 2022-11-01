package me.tabizzz.monarquicraft.Commands;

import com.archyx.aureliumskills.acf.BaseCommand;
import com.archyx.aureliumskills.acf.annotation.*;
import org.bukkit.entity.Player;

@CommandAlias("mc|monarquicraft")
public class MonarquiCraftCommand extends BaseCommand {

	@Subcommand("offhand")
	@Description("Intercambia el objeto dde tu mano principal con la mano secundaria")
	public static void Offhand(Player sender) {
		var main = sender.getInventory().getItemInMainHand();
		var off = sender.getInventory().getItemInOffHand();

		sender.getInventory().setItemInMainHand(off);
		sender.getInventory().setItemInOffHand(main);
	}
}
