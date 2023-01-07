package me.tabizzz.monarquicraft.Items;

import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemLeveler {
	public static ItemStack levelUp(ItemStack item, Player player) {
		if(!MCItemUtils.isMCItem(item)) return item;
		var mcitem = new MCItem(item);
		if(mcitem.canlevel && mcitem.level < 100) {
			return setLevel(item, player, mcitem.level + 1);
		}
		return item;
	}

	public static ItemStack setLevel(ItemStack item, Player player, int level) {
		var mcItem = new MCItem(item.clone());
		mcItem.level ++;
		return mcItem.getItem();
	}
}
