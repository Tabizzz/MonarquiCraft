package me.tabizzz.monarquicraft.Commands;

import com.archyx.aureliumskills.acf.BaseCommand;
import com.archyx.aureliumskills.acf.annotation.*;
import me.tabizzz.monarquicraft.Menus.LevelUpMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("mc|monarquicraft")
public class MonarquicraftMenus extends BaseCommand {

	@Subcommand("menu levelup")
	@CommandPermission("mc.menus")
	@CommandCompletion("@players")
	public void LevelUpMenu(CommandSender sender, @Flags("other")Player target) {
		if(target != null) {
			LevelUpMenu.Open(target);
		}
	}
}
