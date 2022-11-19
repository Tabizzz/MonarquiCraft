package me.tabizzz.monarquicraft;

import com.archyx.aureliumskills.acf.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import me.tabizzz.monarquicraft.Commands.MonarquiCraftCommand;
import me.tabizzz.monarquicraft.Items.ItemRegistry;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class MonarquiCraft extends JavaPlugin {

	static MonarquiCraft plugin;

	private PaperCommandManager commandManager;

	private ItemRegistry itemRegistry;

	@Override
	public void onEnable() {
		plugin = this;

		loadConfig();

		// creamos el command manager
		commandManager = new PaperCommandManager(this);
		itemRegistry = new ItemRegistry(this);

		registerCommands();

		reload();
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
