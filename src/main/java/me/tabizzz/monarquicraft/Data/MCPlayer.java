package me.tabizzz.monarquicraft.Data;

import com.archyx.aureliumskills.api.AureliumAPI;
import me.tabizzz.monarquicraft.Classes.Class;
import me.tabizzz.monarquicraft.MonarquiCraft;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MCPlayer {
	Player player;

	List<ItemStack> reservedItems;

	MCPlayer() {
		reservedItems = new ArrayList<>();
	}

	public static MCPlayer get(Player player) {
		return MonarquiCraft.getPlugin().getPlayerManager().get(player);
	}

	public void addItem(ItemStack item) {
		reservedItems.add(item);
	}

	public int getLevel() {
		return Math.round(AureliumAPI.getTotalLevel(player) / 15f);
	}

	public Class getPlayerClass() {
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
