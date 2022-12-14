package me.tabizzz.monarquicraft.Items;

import com.archyx.aureliumskills.skills.Skills;
import com.archyx.aureliumskills.stats.Stats;
import me.tabizzz.monarquicraft.Classes.Class;
import me.tabizzz.monarquicraft.MonarquiCraft;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemLoader {
	public static ItemStack load(FileConfiguration config, String id) {

		var name = config.getString("name");

		var _material = config.getString("material");
		_material = _material == null ? "STONE" : _material.toUpperCase();
		Material material;
		try {
			material = Material.valueOf(_material);
		} catch (IllegalArgumentException e) {
			MonarquiCraft.getPlugin().getLogger().severe("Unable to load item " + id + ": invalid material name (" + _material + ")");
			return null;
		}

		var canlevel = config.getBoolean("canlevel");
		var level = config.getInt("level");

		var hideEnchants = config.getBoolean("hideenchants");

		var baseattributes = config.getBoolean("baseattributes");

		var ei = config.getString("ei");

		var _class = config.getString("class");
		_class = _class == null ? "NONE" : _class.toUpperCase();
		Class clasS = Class.NONE;
		try {
			clasS = Class.valueOf(_class);
		} catch (IllegalArgumentException e) {
			MonarquiCraft.getPlugin().getLogger().warning("Unable to load item " + id + ": invalid class name (" + _class + ")");
		}

		var lore = config.getStringList("lore");

		var statsSection = config.getConfigurationSection("stats");
		Map<Stats, Double> stats = new HashMap<>();
		if(statsSection != null) {
			for (var key : statsSection.getKeys(false)) {
				try {
					var stat = Stats.valueOf(key.toUpperCase());
					var value = statsSection.getDouble(key);
					stats.put(stat, value);
				} catch (IllegalArgumentException ignored) {
					MonarquiCraft.getPlugin().getLogger().warning("Unable to load item " + id + ": invalid stat name (" + key + ")");
				}
			}
		}

		var multipliersSection = config.getConfigurationSection("multipliers");
		Map<Skills, Double> multipliers = new HashMap<>();
		if(multipliersSection != null) {
			for (var key : multipliersSection.getKeys(false)) {
				try {
					var skill = Skills.valueOf(key.toUpperCase());
					var value = multipliersSection.getDouble(key);
					multipliers.put(skill, value);
				} catch (IllegalArgumentException ignored) {
					MonarquiCraft.getPlugin().getLogger().warning("Unable to load item " + id + ": invalid multiplier name (" + key + ")");
				}
			}
		}

		var requirementsSection = config.getConfigurationSection("requirements");
		Map<Skills, Integer> requirements = new HashMap<>();
		if(requirementsSection != null) {
			for (var key : requirementsSection.getKeys(false)) {
				try {
					var skill = Skills.valueOf(key.toUpperCase());
					var value = requirementsSection.getInt(key);
					requirements.put(skill, value);
				} catch (IllegalArgumentException ignored) {
					MonarquiCraft.getPlugin().getLogger().warning("Unable to load item " + id + ": invalid requirement name (" + key + ")");
				}
			}
		}

		var attributesSection = config.getConfigurationSection("attributes");
		Map<Attribute, Double> attributes = new HashMap<>();
		if(attributesSection != null) {
			for (var key : attributesSection.getKeys(false)) {
				try {
					var att_name = key.toUpperCase();
					if(!att_name.startsWith("GENERIC_") && !att_name.equals("ZOMBIE_SPAWN_REINFORCEMENTS")) {
						att_name = "GENERIC_" + att_name;
					}
					var attribute = Attribute.valueOf(att_name);
					var value = attributesSection.getDouble(key);
					attributes.put(attribute, value);

				} catch (IllegalArgumentException ignored) {
					MonarquiCraft.getPlugin().getLogger().warning("Unable to load item " + id + ": invalid attribute name (" + key + ")");
				}
			}
		}

		var item =  new ItemStack(material);

		var enchantsSection = config.getConfigurationSection("enchants");
		if(enchantsSection != null) {
			for (var key : enchantsSection.getKeys(false)) {
				try {
					var enchant_name = key.toLowerCase();

					var enchant = new EnchantmentWrapper(enchant_name);
					var value = enchantsSection.getInt(key);
					item.addUnsafeEnchantment(enchant, value);

				} catch (IllegalArgumentException ignored) {
					MonarquiCraft.getPlugin().getLogger().warning("Unable to load item " + id + ": invalid attribute name (" + key + ")");
				}
			}
		}

		// write parsed data into a MCItem
		var mcitem = new MCItem();
		mcitem.name = name;
		mcitem.id = id;
		mcitem.canlevel = canlevel;
		mcitem.level = level;
		mcitem.baseattributes = baseattributes;
		mcitem.item = item;
		mcitem.ei = ei;
		mcitem._class = clasS;
		mcitem.lore = lore;
		mcitem.stats = stats;
		mcitem.multipliers = multipliers;
		mcitem.requirements = requirements;
		mcitem.attributes = attributes;
		mcitem.hideEnchants = hideEnchants;

		return mcitem.getItem();
	}
}
