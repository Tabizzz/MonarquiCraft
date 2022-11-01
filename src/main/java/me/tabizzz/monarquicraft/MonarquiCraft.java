package me.tabizzz.monarquicraft;

import com.archyx.aureliumskills.acf.PaperCommandManager;
import me.tabizzz.monarquicraft.Commands.MonarquiCraftCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MonarquiCraft extends JavaPlugin {

	private PaperCommandManager commandManager;

	@Override
	public void onEnable() {
		// creamos el command manager
		commandManager = new PaperCommandManager(this);

		registerCommands();

	}

	private void registerCommands() {
		commandManager.enableUnstableAPI("help");

		// comandos de monarquicraft
		commandManager.registerCommand(new MonarquiCraftCommand());
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
