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

import java.util.HashMap;
import java.util.Optional;

@SuppressWarnings("deprecation")
public class InspectMenu implements InventoryProvider {

	private static final String menuId = "inspect";
	public static void Open(Player player, Player target) {
		var map = new HashMap<String, Object>();
		map.put("target", target);

		Optional<RyseInventory> inventory = MonarquiCraft.getPlugin().getInventoryManager().getInventory(menuId);

		if (inventory.isPresent()) {
			inventory.get().open(map, player);
			return;
		}

		RyseInventory.builder()
				.rows(6)
				.identifier(menuId)
				.provider(new InspectMenu())
				.title("Inspeccionando")
				.permanentCache()
				.build(MonarquiCraft.getPlugin())
				.open(map, player);

	}

	public static void close(Player player) {
		Optional<RyseInventory> inventory = MonarquiCraft.getPlugin().getInventoryManager().getInventory(menuId);

		inventory.ifPresent(ryseInventory -> ryseInventory.close(player));
	}

	@Override
	public void init(Player player, InventoryContents contents) {
		var target = contents.<Player>getProperty("target");
		if(target == null) {
			close(player);
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

		var stats = new ItemStack(Material.HEART_OF_THE_SEA);
		writeStats(stats, target);
		contents.set(5, 8, stats);
	}

	private void writeStats(ItemStack stats, Player target) {
		var meta = stats.getItemMeta();
		meta.setDisplayName(InspectMenuConfig.Instance.getHeartTitle(target));
		meta.setLore(InspectMenuConfig.Instance.getHeartLore(target));
		stats.setItemMeta(meta);
	}

	private void writePvpInfo(ItemStack pvp, Player target) {
		var meta = pvp.getItemMeta();
		meta.setDisplayName(InspectMenuConfig.Instance.getTotemTitle(target));
		meta.setLore(InspectMenuConfig.Instance.getTotemLore(target));
		pvp.setItemMeta(meta);
	}

	private void writeClanInfo(ItemStack clan, Player target) {
		var meta = clan.getItemMeta();
		meta.setDisplayName(InspectMenuConfig.Instance.getBannerTitle(target));

		var inclan = PlaceholderAPI.setPlaceholders(target, "%simpleclans_in_clan%").equals("yes");

		if(inclan) {
			meta.setLore(InspectMenuConfig.Instance.getBannerLore(target));
		} else {
			meta.setLore(InspectMenuConfig.Instance.getBannerLoreNoClan(target));
		}

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
		meta.setDisplayName(InspectMenuConfig.Instance.getSkullTitle(target));
		meta.setLore(InspectMenuConfig.Instance.getSkullLore(target));
		skull.setItemMeta(meta);
	}
}
