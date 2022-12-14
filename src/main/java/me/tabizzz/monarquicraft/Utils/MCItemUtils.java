package me.tabizzz.monarquicraft.Utils;

import com.archyx.aureliumskills.nbtapi.NBTItem;
import com.archyx.aureliumskills.util.item.ItemUtils;
import com.loohp.interactivechat.api.InteractiveChatAPI;
import com.loohp.interactivechat.utils.InteractiveChatComponentSerializer;
import me.tabizzz.monarquicraft.Data.MCPlayer;
import me.tabizzz.monarquicraft.Items.ItemLore;
import me.tabizzz.monarquicraft.Items.MCItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MCItemUtils {

	public static Component createDisplay(Player player, ItemStack item) {
		try {
			var display = InteractiveChatAPI.createItemDisplayComponent(player, item);
			return GsonComponentSerializer.gson().deserialize(InteractiveChatComponentSerializer.gson().serialize(display));
		} catch (Exception e) {
			return net.kyori.adventure.text.Component.text(ChatColor.GRAY + "[").append(item.displayName()).append(net.kyori.adventure.text.Component.text(ChatColor.GRAY+ "]"));
		}
	}

	public static boolean give(Player player, ItemStack item) {
		var res = ItemUtils.addItemToInventory(player, item);
		if(res != null) {
			try {
				var t1 = Component.text(ChatColor.RED + "El item ");
				var ditem = createDisplay(player, item);
				var t2 =
						Component.text(ChatColor.RED + " no ha podido ser agregado a tu inventario, usa "+ ChatColor.YELLOW + "/mc claim" + ChatColor.RED + " para reclamarlo cuando tengas el inventario libre");
				var component = t1.append(ditem).append(t2);
				player.sendMessage(component);
			} catch (Exception ignored) {

			}

			MCPlayer.get(player).addItem(res);
			return false;
		}
		return true;
	}

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
