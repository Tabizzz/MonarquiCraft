package me.tabizzz.monarquicraft.Items;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.modifier.ModifierType;
import com.archyx.aureliumskills.modifier.Modifiers;
import com.archyx.aureliumskills.stats.Stat;
import com.archyx.aureliumskills.stats.StatRegistry;
import com.archyx.aureliumskills.stats.Stats;
import com.archyx.aureliumskills.util.item.ItemUtils;
import me.tabizzz.monarquicraft.MonarquiCraft;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class StatRoller
{

	/**
	 *
	 */
	public static ItemStack Reroll(ItemStack item, Player player, int level)
	{
		var luck = (int) AureliumAPI.getStatLevel(player, Stats.LUCK);
		luck = Math.max(-200, Math.min(1000, luck));
		var baseStat = MaxStatPerItem(level);
		var stats = getStats(luck);
		var newItem = item;
		var meta = newItem.getItemMeta();
		Objects.requireNonNull(meta).setLore(new ArrayList<>());
		newItem.setItemMeta(meta);

		var modifiertype = ModifierType.ITEM;
		if (ItemUtils.isArmor(newItem.getType()))
			modifiertype = ModifierType.ARMOR;
		var modifier = new Modifiers(AureliumAPI.getPlugin());
		newItem = modifier.removeAllModifiers(modifiertype, newItem);

		for (Stat stat : stats)
		{
			var gen = getGen(baseStat, luck);
			if (gen != 0)
				if (ItemUtils.isArmor(newItem.getType()))
					newItem = AureliumAPI.addArmorModifier(newItem, stat, gen, true);
				else
					newItem = AureliumAPI.addItemModifier(newItem, stat, gen, true);
		}


		return newItem;
	}

	private static int getGen(int max, int luck)
	{
		Random random = new Random();
		float maxLuck = luck < 0 ? max / ((-luck / 75f) + 1) : max + (luck / 5f);
		float minLuck = luck < 0 ? -max + luck : (-max) / ((luck / 150f) + 1);
		float gen = random.nextFloat(minLuck, maxLuck);
		float luckH = 0;
		float predev;
		if(luck < 0) {
			predev = gen < 0 ? (gen * max) / maxLuck : gen;
		} else {
			predev = gen < 0 ? gen : (gen * max) / maxLuck;
		}

		return Math.min(max, Math.max(-max, (int) (predev + luckH)));
	}

	private static Stat[] getStats(int luck)
	{
		var random = new Random();
		var gen = random.nextInt(0,25);
		var count = 1;
		for (int i = 0; i < 6; i++) {
			gen -= i+1;
			if(gen < 0) {
				count = 6 - i;
				break;
			}
		}
		Stat[] stats = Stats.values();
		Stat[] dev = new Stat[count];

		List<Stat> list = Arrays.asList(stats);
		Collections.shuffle(list);
		list.subList(0, count).toArray(dev);
		return dev;
	}

	/**
	 * Obtiene la cantidad maxima de estadisticas que puede tener un item a cierto nivel.
	 */
	public static int MaxStatPerItem(int level)
	{
		var temp = (0.0483 * level * level) + (1.1165 * level) + 5.2533;
		temp /= 10;
		return Math.max(1, Math.min(60, (int)Math.round(temp)));
	}
}
