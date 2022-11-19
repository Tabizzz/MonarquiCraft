package me.tabizzz.monarquicraft.Classes;

import org.bukkit.entity.Player;

public class ClassHelper {

	public static Class getPlayerClass(Player player) {
		if(player.hasPermission("mc.class.tank")) {
			return Class.TANK;
		}
		if(player.hasPermission("mc.class.mage")) {
			return Class.MAGE;
		}
		if(player.hasPermission("mc.class.ranger")) {
			return Class.RANGER;
		}
		if(player.hasPermission("mc.class.healer")) {
			return Class.HEALER;
		}
		if(player.hasPermission("mc.class.melee")) {
			return Class.MELEE;
		}
		return Class.NONE;
	}

}
