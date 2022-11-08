package me.tabizzz.monarquicraft;

import com.archyx.aureliumskills.acf.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import me.tabizzz.monarquicraft.Commands.MonarquiCraftCommand;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class MonarquiCraft extends JavaPlugin {

	static Plugin plugin;

	public static Plugin getPlugin() {
		return plugin;
	}

	private PaperCommandManager commandManager;

	@Override
	public void onEnable() {
		plugin = this;

		// creamos el command manager
		commandManager = new PaperCommandManager(this);

		registerCommands();

	}

	private void registerCommands() {
		commandManager.enableUnstableAPI("help");

		// material completion
		commandManager.getCommandCompletions().registerAsyncCompletion("material",
			c -> ImmutableList.copyOf(Arrays.stream(Material.class.getEnumConstants()).map(Enum::name).toArray(String[]::new))
		);

		// comandos de monarquicraft
		commandManager.registerCommand(new MonarquiCraftCommand());
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
