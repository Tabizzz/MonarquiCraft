package me.tabizzz.monarquicraft.Items;

import com.archyx.aureliumskills.skills.Skills;
import com.archyx.aureliumskills.stats.Stats;
import me.tabizzz.monarquicraft.Classes.Class;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MCItem
{
	int level;

	String id;

	boolean canlevel;

	boolean baseattributes;

	Class _class;

	String name;

	List<String> lore;

	ItemStack item;

	Map<Stats, Double> stats;

	Map<Attribute, Double> attributes;

	Map<Skills, Double> multipliers;

	Map<Skills, Integer> requirements;

	String ei;

	boolean hideEnchants;

	public MCItem(ItemStack item) {
		this.item = item;
		_class = Class.NONE;
		lore = new ArrayList<>();
		stats = new HashMap<>();
		attributes = new HashMap<>();
		multipliers = new HashMap<>();
		requirements = new HashMap<>();
		ItemNBT.readMetadata(this);
	}

	MCItem() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isCanlevel() {
		return canlevel;
	}

	public void setCanlevel(boolean canlevel) {
		this.canlevel = canlevel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Class getItemClass() {
		return _class;
	}

	public void setItemClass(Class _class) {
		this._class = _class;
	}

	public Map<Stats, Double> getStats() {
		return stats;
	}

	public void setStats(Map<Stats, Double> stats) {
		this.stats = stats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}

	public ItemStack getItem() {
		ItemNBT.writeMetadata(this);
		var itemlore = new ItemLore(this);
		itemlore.writeAll();
		return item;
	}

	public Map<Skills, Double> getMultipliers() {
		return multipliers;
	}

	public void setMultipliers(Map<Skills, Double> multipliers) {
		this.multipliers = multipliers;
	}

	public Map<Skills, Integer> getRequirements() {
		return requirements;
	}

	public void setRequirements(Map<Skills, Integer> requirements) {
		this.requirements = requirements;
	}

	public Map<Attribute, Double> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<Attribute, Double> attributes) {
		this.attributes = attributes;
	}

	public String getEi() {
		return ei;
	}

	public void setEi(String ei) {
		this.ei = ei;
	}

	public boolean isBaseattributes() {
		return baseattributes;
	}

	public void setBaseattributes(boolean baseattributes) {
		this.baseattributes = baseattributes;
	}
}
