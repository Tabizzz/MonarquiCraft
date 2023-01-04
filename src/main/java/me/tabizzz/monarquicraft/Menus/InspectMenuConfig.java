package me.tabizzz.monarquicraft.Menus;

import me.clip.placeholderapi.PlaceholderAPI;
import me.tabizzz.monarquicraft.Config.ConfigAccessor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class InspectMenuConfig {
	public static InspectMenuConfig Instance;
	private final ConfigAccessor config;

	private String SkullTitle;
	private List<String> SkullLore;

	private String BannerTitle;
	private List<String> BannerLore;
	private List<String> BannerLoreNoClan;

	private String TotemTitle;
	private List<String> TotemLore;

	private String HeartTitle;
	private List<String> HeartLore;

	public InspectMenuConfig(JavaPlugin plugin) {
		config = new ConfigAccessor(plugin, "Menus/InspectMenu.yml");
		config.saveDefaultConfig();
		config.reloadConfig();
		config.saveConfig();
	}

	public void reload() {
		config.reloadConfig();
		var file = config.getConfig();
		SkullTitle = file.getString("SkullTitle", "").replace("&", "§");
		BannerTitle = file.getString("BannerTitle", "").replace("&", "§");
		TotemTitle = file.getString("TotemTitle", "").replace("&", "§");
		HeartTitle = file.getString("HeartTitle", "").replace("&", "§");

		SkullLore = file.getStringList("SkullLore").stream().map(x->x.replace("&", "§")).toList();
		BannerLore = file.getStringList("BannerLore").stream().map(x->x.replace("&", "§")).toList();
		BannerLoreNoClan = file.getStringList("BannerLoreNoClan").stream().map(x->x.replace("&", "§")).toList();
		TotemLore = file.getStringList("TotemLore").stream().map(x->x.replace("&", "§")).toList();
		HeartLore = file.getStringList("HeartLore").stream().map(x->x.replace("&", "§")).toList();
	}

	public String getSkullTitle(Player player) {
		return PlaceholderAPI.setPlaceholders(player, SkullTitle);
	}

	public List<String> getSkullLore(Player player) {
		return PlaceholderAPI.setPlaceholders(player, SkullLore);
	}

	public String getBannerTitle(Player player) {
		return PlaceholderAPI.setPlaceholders(player, BannerTitle);
	}

	public List<String> getBannerLore(Player player) {
		return PlaceholderAPI.setPlaceholders(player, BannerLore);
	}

	public List<String> getBannerLoreNoClan(Player player) {
		return PlaceholderAPI.setPlaceholders(player, BannerLoreNoClan);
	}

	public String getTotemTitle(Player player) {
		return PlaceholderAPI.setPlaceholders(player, TotemTitle);
	}

	public List<String> getTotemLore(Player player) {
		return PlaceholderAPI.setPlaceholders(player, TotemLore);
	}

	public String getHeartTitle(Player player) {
		return PlaceholderAPI.setPlaceholders(player, HeartTitle);
	}

	public List<String> getHeartLore(Player player) {
		return PlaceholderAPI.setPlaceholders(player, HeartLore);
	}
}
