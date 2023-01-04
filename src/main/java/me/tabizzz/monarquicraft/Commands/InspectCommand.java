package me.tabizzz.monarquicraft.Commands;

import com.archyx.aureliumskills.acf.BaseCommand;
import com.archyx.aureliumskills.acf.annotation.*;
import me.tabizzz.monarquicraft.Menus.InspectMenu;
import org.bukkit.entity.Player;

@CommandAlias("inspect|inspeccionar")
public class InspectCommand extends BaseCommand {

	@Default
	@CommandCompletion("@players")
	@Description("Inspecciona el equipamiento de otro jugador.")
	public static void Inspect(Player sender,@Flags("other") Player target ){
		if(sender != null && target != null) {
			InspectMenu.Open(sender, target);
		}
	}
}
