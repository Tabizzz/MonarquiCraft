package me.tabizzz.monarquicraft.Support;

import com.archyx.aureliumskills.api.AureliumAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.tabizzz.monarquicraft.Data.MCPlayer;
import me.tabizzz.monarquicraft.MonarquiCraft;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderSupport extends PlaceholderExpansion {

	@Override
	public boolean persist() {
		return true;
	}

	@Override
	public boolean canRegister(){
		return true;
	}

	@Override
	public @NotNull String getIdentifier() {
		return "monarquicraft";
	}

	@Override
	public @NotNull String getAuthor() {
		return MonarquiCraft.getPlugin().getDescription().getAuthors().toString();
	}

	@Override
	public @NotNull String getVersion() {
		return MonarquiCraft.getPlugin().getDescription().getVersion();
	}

	@Override
	public @Nullable String onPlaceholderRequest(Player player, @NotNull String identifier) {

		if (player == null) {
			return "";
		}

		var data = MCPlayer.get(player);

		if(identifier.equals("level")) {
			return data.getLevel() + "";
		}
		if(identifier.equals("pve_level")) {
			return Math.round((AureliumAPI.getTotalLevel(player) + player.getLevel()) / 15f) + "";
		}
		if(identifier.equals("pve_power")) {
			return (AureliumAPI.getTotalLevel(player) + player.getLevel()) + "";
		}
		if (identifier.equals("class_name")) {
			return data.getPlayerClass().getName();
		}
		if(identifier.equals("playtime")) {
			var ticks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
			var hours = ticks / 20 / 60 / 60;
			return hours + "h";
		}

		return null;
	}
}
