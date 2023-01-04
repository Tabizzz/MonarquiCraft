package me.tabizzz.monarquicraft.Listeners;

import com.archyx.aureliumskills.api.event.SkillLevelUpEvent;
import com.archyx.aureliumskills.lang.Lang;
import me.tabizzz.monarquicraft.Config.Messages;
import me.tabizzz.monarquicraft.MonarquiCraft;
import me.tabizzz.monarquicraft.Utils.MessageUtils;
import net.kyori.adventure.sound.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerLevelListener implements Listener {
	@EventHandler
	public void OnMaxSkill(SkillLevelUpEvent event) {
		var player = event.getPlayer();
		if(event.getLevel() == 100) {
			var name = event.getSkill().getDisplayName(Lang.getDefaultLanguage());
			MonarquiCraft.getPlugin().getServer().broadcast(MessageUtils.parseMessage(Messages.playerMaxSkill, player, "skill", name));

			for (var players: MonarquiCraft.getPlugin().getServer().getOnlinePlayers()) {
				players.playSound(Sound.sound(org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE.key(), Sound.Source.MASTER, 1f, 1f));
			}
		}
	}
}
