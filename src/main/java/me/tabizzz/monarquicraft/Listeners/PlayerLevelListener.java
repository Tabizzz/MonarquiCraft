package me.tabizzz.monarquicraft.Listeners;

import com.archyx.aureliumskills.api.event.SkillLevelUpEvent;
import com.archyx.aureliumskills.lang.Lang;
import me.tabizzz.monarquicraft.MonarquiCraft;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerLevelListener implements Listener {
	@EventHandler
	public void OnMaxSkill(SkillLevelUpEvent event) {
		var player = event.getPlayer();
		if(event.getLevel() == 100) {
			var name = event.getSkill().getDisplayName(Lang.getDefaultLanguage());
			MonarquiCraft.getPlugin().getServer().broadcast(Component.text(ChatColor.WHITE + player.getName() +
					" ha subido la skill " + ChatColor.DARK_PURPLE + "[" + name + "]" + ChatColor.WHITE +
					" al nivel maximo!"));

			for (var players: MonarquiCraft.getPlugin().getServer().getOnlinePlayers()) {
				players.playSound(Sound.sound(org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE.key(), Sound.Source.MASTER, 1f, 1f));
			}
		}
	}
}
