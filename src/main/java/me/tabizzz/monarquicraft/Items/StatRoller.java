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

	private static int getGen(float maxVal, float statLevel)
	{
		Random random = new Random();
		float maxLuck = maxVal + (statLevel / 5);
		float minLuck = (-maxVal*2)/((statLevel/150)+1);
		float gen = random.nextFloat(minLuck, maxLuck + 0.1f);
		float luckH = ((maxLuck- gen)/5)*(statLevel / 1000);
		float predev = gen > 0 ? gen / (maxLuck/maxVal) : gen;
		int dev = (int) (predev + luckH);
		if(dev > maxVal) dev = (int)maxVal;
		return dev;
	}

	private static Stat[] getStats(int luck)
	{
		var random = new Random();
		var gen = random.nextInt(0,25);
		MonarquiCraft.getPlugin().getLogger().info("generated stat with value of " + gen);
		var count = 0;
		for (int i = 0; i < 6; i++) {
			gen -= i+1;
			if(gen < 0) {
				count = 6 - i;
				break;
			}
		}
		MonarquiCraft.getPlugin().getLogger().info("generated stat with count of " + count);
		Stat[] stats = Stats.values();
		Stat[] dev = new Stat[count];

		if(count == 0) return dev;

		List<Stat> list = Arrays.asList(stats);
		Collections.shuffle(list);
		list.subList(0, count).toArray(dev);
		return dev;
	}

	/**
	 * Obtiene la cantidad maxima dde estadisticas que puede tener un item a cierto nivel.
	 */
	public static int MaxStatPerItem(int level)
	{
		var temp = (0.0572 * level * level) + (0.2713 * level) + 0.0912;
		temp /= 6;
		return Math.max(0, Math.min(100, (int)Math.round(temp)));
	}
}