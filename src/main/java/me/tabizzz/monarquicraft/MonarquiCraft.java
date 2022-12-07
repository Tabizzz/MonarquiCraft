package me.tabizzz.monarquicraft;

import com.archyx.aureliumskills.acf.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import me.tabizzz.monarquicraft.Commands.MonarquiCraftCommand;
import me.tabizzz.monarquicraft.Items.ItemRegistry;
import me.tabizzz.monarquicraft.Listeners.InventoryListener;
import me.tabizzz.monarquicraft.Support.PlaceholderSupport;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class MonarquiCraft extends JavaPlugin {

	static MonarquiCraft plugin;

	private PaperCommandManager commandManager;

	private ItemRegistry itemRegistry;
	private boolean placeholderAPIEnabled;

	@Override
	public void onEnable() {
		plugin = this;

		loadConfig();

		// creamos el command manager
		commandManager = new PaperCommandManager(this);
		itemRegistry = new ItemRegistry(this);

		registerCommands();
		registerListeners();
		addSupports();

		reload();
	}

	private void addSupports() {
		// Checks for PlaceholderAPI
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			new PlaceholderSupport().register();
			placeholderAPIEnabled = true;
			getLogger().info("PlaceholderAPI Support Enabled!");
		}
		else {
			placeholderAPIEnabled = false;
		}
	}

	private void registerListeners() {
		var manager = getServer().getPluginManager();
		manager.registerEvents(new InventoryListener(), this);
	}

	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

	}

	public void reload() {
		getLogger().info("Loading from files");
		itemRegistry.clearAll();
		itemRegistry.loadFromFiles();
	}

	private void registerCommands() {
		commandManager.enableUnstableAPI("help");

		// material completion
		commandManager.getCommandCompletions().registerAsyncCompletion("material",
			c -> ImmutableList.copyOf(Arrays.stream(Material.class.getEnumConstants()).map(Enum::name).toArray(String[]::new))
		);

		commandManager.getCommandCompletions().registerAsyncCompletion("items", c -> itemRegistry.idSet());

		// comandos de monarquicraft
		commandManager.registerCommand(new MonarquiCraftCommand());
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static MonarquiCraft getPlugin() {
		return plugin;
	}

	public ItemRegistry getItemRegistry() {
		return itemRegistry;
	}
}
