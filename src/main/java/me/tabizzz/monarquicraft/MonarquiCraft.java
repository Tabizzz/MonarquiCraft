package me.tabizzz.monarquicraft;

import com.archyx.aureliumskills.acf.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import me.tabizzz.monarquicraft.Commands.BountyCommand;
import me.tabizzz.monarquicraft.Commands.InspectCommand;
import me.tabizzz.monarquicraft.Commands.MonarquiCraftCommand;
import me.tabizzz.monarquicraft.Commands.MonarquicraftMenus;
import me.tabizzz.monarquicraft.Config.MCConfig;
import me.tabizzz.monarquicraft.Config.MessagesConfig;
import me.tabizzz.monarquicraft.Data.PlayerManager;
import me.tabizzz.monarquicraft.Executables.ExecutableManager;
import me.tabizzz.monarquicraft.Items.ItemRegistry;
import me.tabizzz.monarquicraft.Listeners.*;
import me.tabizzz.monarquicraft.Menus.InspectMenuConfig;
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
	private MessagesConfig messagesConfig;
	private InventoryManager inventoryManager;

	@Override
	public void onEnable() {
		plugin = this;

		// creamos el command manager
		mcConfig = new MCConfig(this);
		commandManager = new PaperCommandManager(this);
		itemRegistry = new ItemRegistry(this);
		playerManager = new PlayerManager(this);
		inventoryManager = new InventoryManager(this);
		messagesConfig = new MessagesConfig(this);

		//singletons
		InspectMenuConfig.Instance = new InspectMenuConfig(this);

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
		ExecutableManager.registerEvents(manager, this);
		manager.registerEvents(new InventoryListener(), this);
		manager.registerEvents(new PlayerDataListener(), this);
		manager.registerEvents(new ItemLevelListener(), this);
		manager.registerEvents(new EntityListener(), this);
		manager.registerEvents(new PlayerLevelListener(), this);
	}

	public void reload() {
		getLogger().info("Loading from files");
		mcConfig.reload();
		messagesConfig.reload();
		InspectMenuConfig.Instance.reload();

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
		commandManager.registerCommand(new InspectCommand());
		commandManager.registerCommand(new BountyCommand());
		commandManager.registerCommand(new MonarquicraftMenus());
	}

	@Override
	public void onDisable() {
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

	public MessagesConfig getMessagesConfig() {
		return messagesConfig;
	}
}
