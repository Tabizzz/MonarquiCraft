package me.tabizzz.monarquicraft.Items;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.modifier.ModifierType;
import com.archyx.aureliumskills.modifier.Modifiers;
import com.archyx.aureliumskills.modifier.Multipliers;
import com.archyx.aureliumskills.nbtapi.NBTItem;
import com.archyx.aureliumskills.requirement.Requirements;
import com.archyx.aureliumskills.skills.Skills;
import com.archyx.aureliumskills.stats.Stats;
import com.archyx.aureliumskills.util.item.ItemUtils;
import me.tabizzz.monarquicraft.Classes.Class;
import org.bukkit.attribute.Attribute;

import static org.bukkit.inventory.ItemFlag.*;

public class ItemNBT {
	public static void writeMetadata(MCItem mcitem) {
		saveData(mcitem);
		setMeta(mcitem);

	}

	private static void setMeta(MCItem mcitem) {
		var item = mcitem.item;
		var meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName(mcitem.name.replace('&', '§'));

			// hide all
			meta.addItemFlags(
					HIDE_ENCHANTS,
					//HIDE_ATTRIBUTES,
					HIDE_UNBREAKABLE,
					HIDE_DESTROYS,
					HIDE_PLACED_ON,
					HIDE_POTION_EFFECTS,
					HIDE_DYE);

			//todo: attributes
		}
		item.setItemMeta(meta);

		var type = ItemUtils.isArmor(item.getType()) ? ModifierType.ARMOR : ModifierType.ITEM;

		// stats
		var modifier = new Modifiers(AureliumAPI.getPlugin());
		item = modifier.removeAllModifiers(type, item);
		for (var stat : mcitem.stats.entrySet()) {
			item = modifier.addModifier(type, item, stat.getKey(), stat.getValue());
		}

		// multipliers
		var multiplier = new Multipliers(AureliumAPI.getPlugin());
		item = multiplier.removeAllMultipliers(type, item);
		for (var skill : mcitem.multipliers.entrySet()) {
			item = multiplier.addMultiplier(type, item, skill.getKey(), skill.getValue());
		}

		// requirements
		var requirement = new Requirements(AureliumAPI.getPlugin());
		item = requirement.removeAllRequirements(type, item);
		for (var skill : mcitem.requirements.entrySet()) {
			item = requirement.addRequirement(type, item, skill.getKey(), skill.getValue());
		}


		mcitem.item = item;
	}

	private static void saveData(MCItem mcitem) {
		var nbtitem = new NBTItem(mcitem.item, true);
		nbtitem.removeKey("MonarquiCraft");
		var nbt = nbtitem.getOrCreateCompound("MonarquiCraft");
		nbt.setString("id", mcitem.id);

		nbt.setString("name", mcitem.name);

		if (mcitem.canlevel) {
			nbt.setInteger("level", mcitem.level);
		} else {
			nbt.setBoolean("nolevel", true);
		}

		nbt.setString("class", mcitem._class.name());

		nbt.getStringList("lore").addAll(mcitem.lore);

		var stats = nbt.getOrCreateCompound("stats");
		for (var stat : mcitem.stats.entrySet()) {
			if (stat.getValue() != 0) {
				stats.setDouble(stat.getKey().name(), stat.getValue());
			}
		}

		var multipliers = nbt.getOrCreateCompound("multipliers");
		for (var stat : mcitem.multipliers.entrySet()) {
			if (stat.getValue() > 0) {
				multipliers.setDouble(stat.getKey().name(), stat.getValue());
			}
		}

		var requirements = nbt.getOrCreateCompound("requirements");
		for (var stat : mcitem.requirements.entrySet()) {
			if (stat.getValue() > 0) {
				requirements.setInteger(stat.getKey().name(), stat.getValue());
			}
		}

		var attributes = nbt.getOrCreateCompound("attributes");
		for (var stat : mcitem.attributes.entrySet()) {
			if (stat.getValue() != 0) {
				attributes.setDouble(stat.getKey().name(), stat.getValue());
			}
		}

		if (mcitem.ei != null) {
			var publicBukkit = nbtitem.getOrCreateCompound("PublicBukkitValues");
			publicBukkit.setInteger("score:usage", 1);
			publicBukkit.setString("executableitems:ei-id", mcitem.ei);
		}

		mcitem.item = nbtitem.getItem();
	}

	public static void readMetadata(MCItem mcItem) {
		var nbtitem = new NBTItem(mcItem.item, false);
		if (nbtitem.hasKey("MonarquiCraft")) {
			var nbt = nbtitem.getCompound("MonarquiCraft");
			mcItem.id = nbt.getString("id");

			mcItem.name = nbt.getString("name");

			if (nbt.hasKey("nolevel")) {
				mcItem.canlevel = false;
				mcItem.level = 0;
			} else {
				mcItem.canlevel = true;
				mcItem.level = nbt.getInteger("level");
			}

			var classname = nbt.getString("class");
			mcItem._class = Class.valueOf(classname);

			var lore = nbt.getStringList("lore");
			mcItem.lore.addAll(lore);

			var stats = nbt.getCompound("stats");
			for (var reg : stats.getKeys()) {
				var stat = Stats.valueOf(reg);
				var value = stats.getDouble(reg);
				mcItem.stats.put(stat, value);
			}

			var multipliers = nbt.getCompound("multipliers");
			for (var reg : multipliers.getKeys()) {
				var stat = Skills.valueOf(reg);
				var value = multipliers.getDouble(reg);
				mcItem.multipliers.put(stat, value);
			}

			var requirements = nbt.getCompound("requirements");
			for (var reg : requirements.getKeys()) {
				var stat = Skills.valueOf(reg);
				var value = requirements.getInteger(reg);
				mcItem.requirements.put(stat, value);
			}

			var attributes = nbt.getCompound("attributes");
			for (var reg : attributes.getKeys()) {
				var stat = Attribute.valueOf(reg);
				var value = requirements.getDouble(reg);
				mcItem.attributes.put(stat, value);
			}


		}
		if (nbtitem.hasKey("PublicBukkitValues")) {
			var publicBukkit = nbtitem.getCompound("PublicBukkitValues");
			mcItem.ei = publicBukkit.getString("executableitems:ei-id");
		}
	}
}