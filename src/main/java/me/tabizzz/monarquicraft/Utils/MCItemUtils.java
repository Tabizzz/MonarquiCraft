package me.tabizzz.monarquicraft.Utils;

import com.archyx.aureliumskills.nbtapi.NBTItem;
import com.archyx.aureliumskills.util.item.ItemUtils;
import com.loohp.interactivechat.api.InteractiveChatAPI;
import com.loohp.interactivechat.utils.InteractiveChatComponentSerializer;
import me.tabizzz.monarquicraft.Config.Messages;
import me.tabizzz.monarquicraft.Data.MCPlayer;
import me.tabizzz.monarquicraft.Items.ItemLore;
import me.tabizzz.monarquicraft.Items.MCItem;
import me.tabizzz.monarquicraft.MonarquiCraft;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
		if(!io.lumine.mythic.bukkit.utils.items.ItemUtils.isValid(item)) return true;
		var res = ItemUtils.addItemToInventory(player, item);
		if(res != null) {
			player.sendMessage(MessageUtils.parseMessage(Messages.playerNoSpace, player, item));
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

	public static boolean isMCItem(ItemStack mcitem, String id) {
		if(mcitem == null || mcitem.getType() == Material.AIR) return false;
		var nbt = new NBTItem(mcitem);
		var mc = nbt.hasKey("MonarquiCraft");
		return mc && nbt.getCompound("MonarquiCraft").getString("id").equals(id);
	}

	public static boolean isMCItem(ItemStack mcitem) {
		if(mcitem == null || mcitem.getType() == Material.AIR) return false;
		var nbt = new NBTItem(mcitem);
		return nbt.hasKey("MonarquiCraft");
	}

	public static boolean canBeMCItem(ItemStack mcitem) {
		var mat = mcitem.getType();
		return ItemUtils.isArmor(mat) || ItemUtils.isTool(mat) || ItemUtils.isWeapon(mat);
	}
}
