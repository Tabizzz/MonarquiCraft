package me.tabizzz.monarquicraft.Support;

import com.archyx.aureliumskills.api.AureliumAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.tabizzz.monarquicraft.Classes.ClassHelper;
import me.tabizzz.monarquicraft.MonarquiCraft;
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

		if(identifier.equals("level")) {
			return Math.round(AureliumAPI.getTotalLevel(player) / 15f) + "";
		}
		if(identifier.equals("pve_level")) {
			return Math.round((AureliumAPI.getTotalLevel(player) + player.getLevel()) / 15f) + "";
		}
		if(identifier.equals("pve_power")) {
			return (AureliumAPI.getTotalLevel(player) + player.getLevel()) + "";
		}
		if (identifier.equals("class_name")) {
			return ClassHelper.getPlayerClass(player).getName();
		}


		return null;
	}
}
