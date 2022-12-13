package me.tabizzz.monarquicraft.Menus;

import dev.dbassett.skullcreator.SkullCreator;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import me.clip.placeholderapi.PlaceholderAPI;
import me.tabizzz.monarquicraft.MonarquiCraft;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class InspectMenu implements InventoryProvider {

	private static final RyseInventory Menu = RyseInventory.builder()
			.rows(6)
			.size(54)
			.provider(new InspectMenu())
			.title("Inspeccionando")
			.build(MonarquiCraft.getPlugin());

	public static void Open(Player viewer, Player target) {
		var map = new HashMap<String, Object>();
		map.put("target", target);
		Menu.open(map, viewer);
	}

	@Override
	public void init(Player player, InventoryContents contents) {
		var target = contents.<Player>getProperty("target");
		if(target == null) {
			Menu.close(player);
			return;
		}
		var fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		var fm = fill.getItemMeta();
		fm.setDisplayName(" ");
		fill.setItemMeta(fm);
		contents.fill(fill);

		var skull = SkullCreator.itemFromUuid(target.getUniqueId());
		writePlayerInfo(skull, target);
		contents.set(0, skull);

		setEquipment(contents, target);

		var clan = new ItemStack(Material.WHITE_BANNER);
		writeClanInfo(clan, target);
		contents.set(5, 0, clan);

		var pvp = new ItemStack(Material.TOTEM_OF_UNDYING);
		writePvpInfo(pvp, target);
		contents.set(0, 8, pvp);
	}

	private void writePvpInfo(ItemStack pvp, Player target) {
		var meta = pvp.getItemMeta();
		meta.setDisplayName("Información de pvp");
		var lore = meta.hasLore() ? meta.getLore() : new ArrayList<String>();

		lore.add(PlaceholderAPI.setPlaceholders(target, "§fKDR: §a%simpleclans_kdr%"));
		lore.add(PlaceholderAPI.setPlaceholders(target, "§fKills: §b%simpleclans_total_kills%"));
		lore.add(PlaceholderAPI.setPlaceholders(target, "§fMuertes: §c%simpleclans_deaths%"));

		meta.setLore(lore);
		pvp.setItemMeta(meta);
	}

	private void writeClanInfo(ItemStack clan, Player target) {
		var meta = clan.getItemMeta();
		meta.setDisplayName("Información de clan");
		var lore = meta.hasLore() ? meta.getLore() : new ArrayList<String>();

		var inclan = PlaceholderAPI.setPlaceholders(target, "%simpleclans_in_clan%").equals("yes");

		if(inclan) {
			lore.add(PlaceholderAPI.setPlaceholders(target, "§fClan: §a%simpleclans_clan_name%"));
			lore.add(PlaceholderAPI.setPlaceholders(target, "§fClan Tag: §b%simpleclans_tag_label%"));
			lore.add(PlaceholderAPI.setPlaceholders(target, "§fRango: §c%simpleclans_rank_displayname%"));
			lore.add(PlaceholderAPI.setPlaceholders(target, "§fFecha ingreso: §d%simpleclans_join_date%"));
		} else {
			lore.add("§c" + target.getName() + " no se encuentra en un clan");
		}

		meta.setLore(lore);
		clan.setItemMeta(meta);
	}

	private void setEquipment(InventoryContents contents, Player target) {
		var inv = target.getInventory();
		contents.set(2, 5, inv.getItemInMainHand());
		contents.set(3, 5, inv.getItemInOffHand());

		var armor = inv.getArmorContents();

		if(armor[3] != null)
			contents.set(1, 3, armor[3]);
		else
			contents.removeItemWithConsumer(1, 3);
		if(armor[2] != null)
			contents.set(2, 3, armor[2]);
		else
			contents.removeItemWithConsumer(2, 3);
		if(armor[1] != null)
			contents.set(3, 3, armor[1]);
		else
			contents.removeItemWithConsumer(3, 3);
		if(armor[0] != null)
			contents.set(4, 3, armor[0]);
		else
			contents.removeItemWithConsumer(4, 3);
	}

	private void writePlayerInfo(ItemStack skull, Player target) {
		var meta = skull.getItemMeta();
		meta.setDisplayName(target.getName());
		var lore = meta.hasLore() ? meta.getLore() : new ArrayList<String>();

		lore.add(PlaceholderAPI.setPlaceholders(target, "§fNivel:   §a%monarquicraft_level%"));
		lore.add(PlaceholderAPI.setPlaceholders(target, "§fPoder: §b%aureliumskills_power%"));
		lore.add(PlaceholderAPI.setPlaceholders(target, "§fRango: §c%aureliumskills_rank%"));
		lore.add(PlaceholderAPI.setPlaceholders(target, "§fTiempo Jugado: §d%monarquicraft_playtime%"));

		meta.setLore(lore);
		skull.setItemMeta(meta);
	}
}
