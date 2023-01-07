package me.tabizzz.monarquicraft.Menus;

import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.enums.DisabledInventoryClick;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import io.lumine.mythic.bukkit.utils.items.ItemUtils;
import me.tabizzz.monarquicraft.Items.ItemLeveler;
import me.tabizzz.monarquicraft.Items.MCItem;
import me.tabizzz.monarquicraft.MonarquiCraft;
import me.tabizzz.monarquicraft.Utils.MCItemUtils;
import me.tabizzz.monarquicraft.Utils.MenuUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@SuppressWarnings("deprecation")
public class LevelUpMenu implements InventoryProvider {
	private static final String menuId = "levelup";

	public static void Open(Player player) {
		Optional<RyseInventory> inventory = MonarquiCraft.getPlugin().getInventoryManager().getInventory(menuId);

		if (inventory.isPresent()) {
			inventory.get().open(player);
			return;
		}

		RyseInventory.builder()
				.rows(3)
				.identifier(menuId)
				.provider(new LevelUpMenu())
				.title("Herrero")
				.disableUpdateTask()
				.ignoreClickEvent(DisabledInventoryClick.BOTTOM)
				.ignoredSlots(11, 13, 15)
				.permanentCache()
				.build(MonarquiCraft.getPlugin())
				.open(player);
	}

	@Override
	public void init(Player player, InventoryContents contents) {
		var fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		var fm = fill.getItemMeta();
		fm.setDisplayName(" ");
		fill.setItemMeta(fm);
		contents.fillBorders(fill);
		contents.fillColumn(1, fill);
		contents.fillColumn(3, fill);
		contents.fillColumn(5, fill);
		contents.fillColumn(7, fill);

		contents.addAdvancedSlot(11,  MenuUtils.editable(MCItem::isCanlevel, inventory -> setResult(inventory, player)));
		contents.addAdvancedSlot(13,  MenuUtils.editable(mcItem -> mcItem.getId().equals("meteorito"), inventory -> setResult(inventory, player)));

		contents.addAdvancedSlot(15,  inventoryClickEvent -> {
			switch (inventoryClickEvent.getAction()) {
				case PICKUP_ALL, PICKUP_ONE, MOVE_TO_OTHER_INVENTORY -> {
					if(MCItemUtils.isMCItem(inventoryClickEvent.getCurrentItem())) {
						var item = new MCItem(inventoryClickEvent.getCurrentItem());
						inventoryClickEvent.getInventory().setItem(11, null);
						inventoryClickEvent.getInventory().setItem(13, inventoryClickEvent.getInventory().getItem(13).subtract(item.getLevel()));
						return;
					}
				}
			}
			inventoryClickEvent.setCancelled(true);
		});

	}

	public static void setResult(Inventory inventory, Player player) {
		var input = inventory.getItem(11);
		var meteor = inventory.getItem(13);
		if(!MCItemUtils.isMCItem(input) || !MCItemUtils.isMCItem(meteor, "meteorito")) {
			inventory.setItem(15, null);
			return;
		}
		var mcinput = new MCItem(input);
		if(!mcinput.isCanlevel() || meteor.getAmount() < mcinput.getLevel() + 1 || mcinput.getLevel() >= 64) {
			inventory.setItem(15, null);
			return;
		}
		var out = ItemLeveler.levelUp(input, player);
		inventory.setItem(15, out);
	}

	@Override
	public void close(Player player, RyseInventory inventory) {
		var inv = inventory.getInventory();
		if (inv == null) return;
		if(ItemUtils.isValid(inv.getItem(11))) {
			MCItemUtils.give(player, inv.getItem(11));
		}
		if(ItemUtils.isValid(inv.getItem(13))) {
			MCItemUtils.give(player, inv.getItem(13));
		}
	}
}
