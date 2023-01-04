package me.tabizzz.monarquicraft.Commands;

import com.archyx.aureliumskills.acf.BaseCommand;
import com.archyx.aureliumskills.acf.annotation.*;
import me.tabizzz.monarquicraft.Executables.BrujulaMagica;
import org.bukkit.entity.Player;
@CommandAlias("recompensa")
public class BountyCommand extends BaseCommand {

	@Default
	@CommandCompletion("@players")
	@Description("Agrega una recompensa por un jugador.")
	public static void Bounty(Player sender, @Flags("other") Player target, String price){
		if(sender != null && target != null) {
			sender.chat("bounty " + target.getName() + " " + price);
		}
	}

	@Subcommand("menu")
	@CommandCompletion("@players")
	@Description("Abre el menu para crear una recompensa.")
	public static void Menu(Player sender) {
		if(sender != null) {
			sender.chat("/bounty create");
		}
	}

	@Subcommand("lista")
	@CommandCompletion("@players")
	@Description("Abre el menu para ver recompensas activas.")
	public static void List(Player sender) {
		if(sender != null) {
			sender.chat("/bounty list");
		}
	}

	@Subcommand("rastrear")
	@CommandCompletion("@players")
	@Description("Rastrea a un jugador si tiene una recompensa")
	public static void Rastrear(Player sender, @Flags("other") Player target ){
		if(sender != null && target != null) {
			BrujulaMagica.setTarget(sender, target);
		}
	}


}