package me.tabizzz.monarquicraft;

import com.archyx.aureliumskills.acf.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import me.tabizzz.monarquicraft.Commands.MonarquiCraftCommand;
import me.tabizzz.monarquicraft.Config.MCConfig;
import me.tabizzz.monarquicraft.Data.PlayerManager;
import me.tabizzz.monarquicraft.Items.ItemRegistry;
import me.tabizzz.monarquicraft.Listeners.*;
import me.tabizzz.monarquicraft.Support.PlaceholderSupport;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class MonarquiCraft extends JavaPlugin {

	static MonarquiCraft plugin;

	private PaperCommandManager commandManager;

	private ItemRegistry itemRegistry;

	private PlayerManager playerManager;

	private MCConfig mcConfig;
	private InventoryManager inventoryManager;

	@Override
	public void onEnable() {
		plugin = this;

		loadConfig();

		// creamos el command manager
		commandManager = new PaperCommandManager(this);
		itemRegistry = new ItemRegistry(this);
		playerManager = new PlayerManager(this);
		mcConfig = new MCConfig(this);
		inventoryManager = new InventoryManager(this);

		registerCommands();
		registerListeners();
		addSupports();

		reload();
	}

	private void addSupports() {
		// Checks for PlaceholderAPI
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			new PlaceholderSupport().register();
			getLogger().info("PlaceholderAPI Support Enabled!");
		}

	}

	private void registerListeners() {
		inventoryManager.invoke();

		var manager = getServer().getPluginManager();
		manager.registerEvents(new InventoryListener(), this);
		manager.registerEvents(new PlayerDataListener(), this);
		manager.registerEvents(new PlayerFigthListener(), this);
		manager.registerEvents(new ItemLevelListener(), this);
		manager.registerEvents(new EntityListener(), this);
	}

	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
	}

	public void reload() {
		getLogger().info("Loading from files");
		mcConfig.reload();

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
		playerManager.saveAll();
	}

	public static MonarquiCraft getPlugin() {
		return plugin;
	}

	public ItemRegistry getItemRegistry() {
		return itemRegistry;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public MCConfig getMcConfig() {
		return mcConfig;
	}

	public InventoryManager getInventoryManager() { return inventoryManager; }
}
