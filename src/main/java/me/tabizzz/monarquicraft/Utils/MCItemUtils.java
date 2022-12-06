package me.tabizzz.monarquicraft.Utils;

import com.archyx.aureliumskills.nbtapi.NBTItem;
import com.archyx.aureliumskills.util.item.ItemUtils;
import me.tabizzz.monarquicraft.Items.ItemLore;
import me.tabizzz.monarquicraft.Items.MCItem;
import org.bukkit.inventory.ItemStack;

public class MCItemUtils {

	public static MCItem createFromVanilla(ItemStack item) {
		if(isMCItem(item)) return new MCItem(item);
		var mcitem = new MCItem(item);
		mcitem.setBaseattributes(true);
		mcitem.setId(item.getType().getKey().toString());
		return mcitem;
	}

	public static void updateLore(ItemStack item) {
		var mcitem = createFromVanilla(item);
		var lore = new ItemLore(mcitem);
		lore.writeAll();
	}

	public static boolean isMCItem(ItemStack mcitem) {
		var nbt = new NBTItem(mcitem);
		return nbt.hasKey("MonarquiCraft");
	}

	public static boolean canBeMCItem(ItemStack mcitem) {
		var mat = mcitem.getType();
		return ItemUtils.isArmor(mat) || ItemUtils.isTool(mat) || ItemUtils.isWeapon(mat);
	}
}
