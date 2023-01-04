package me.tabizzz.monarquicraft.Items;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.lang.Lang;
import com.archyx.aureliumskills.modifier.ModifierType;
import com.archyx.aureliumskills.modifier.Modifiers;
import com.archyx.aureliumskills.modifier.Multipliers;
import com.archyx.aureliumskills.requirement.Requirements;
import com.archyx.aureliumskills.util.item.ItemUtils;
import me.tabizzz.monarquicraft.Classes.Class;
import me.tabizzz.monarquicraft.Utils.AttributeUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemLore {

	private final MCItem mcitem;
	private final ItemStack item;
	private final ItemMeta meta;
	private final List<String> lore;
	private final ModifierType type;

	public ItemStack getItem() {
		return item;
	}

	public ItemLore(MCItem mcItem) {
		mcitem = mcItem;
		item = mcItem.item;
		meta = item.getItemMeta();
		lore = new ArrayList<>();
		type = ItemUtils.isArmor(item.getType()) ? ModifierType.ARMOR : ModifierType.ITEM;
	}

	public void writeAll() {
		lore.clear();
		writeInfo();
		writeAtributes();
		writeEnchants();
		writeStats();
		writeMultipliers();
		writeRequirements();

		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, lore.get(i).replace('&', '§'));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	private void writeAtributes() {
		var src = meta.getAttributeModifiers();
		if(src == null) return;
		var attributes = new HashMap<Attribute, Double>();

		for (var atr: Attribute.values()) {
			if(src.containsKey(atr)) {
				var value = 0d;
				for (var attribute: src.get(atr)) {
					if(attribute.getOperation() == AttributeModifier.Operation.ADD_NUMBER) {
						value += attribute.getAmount();
					}
				}
				if(value != 0) {
					attributes.put(atr, value);
				}
			}
		}
		AttributeUtils.setAttributesToDisplay(attributes);
		if(attributes.size() > 0) {
			lore.add("&7&l&m----------&7<&e&lAtributos&7>&7&l&m----------");
			var df = new DecimalFormat("0.##");
			for (var attribute: attributes.entrySet()) {
				lore.add((type == ModifierType.ARMOR ? "&9" : "&2") + getAttributeName(attribute.getKey()) + ": " + df.format(attribute.getValue()));
			}
		}
	}

	private String getAttributeName(Attribute attribute) {
		switch (attribute) {
			case GENERIC_MAX_HEALTH -> {
				return "Salud Máxima";
			}
			case GENERIC_FOLLOW_RANGE, ZOMBIE_SPAWN_REINFORCEMENTS, HORSE_JUMP_STRENGTH, GENERIC_FLYING_SPEED -> {
				return "...";
			}
			case GENERIC_KNOCKBACK_RESISTANCE -> {
				return "Resistencia al empuje";
			}
			case GENERIC_MOVEMENT_SPEED -> {
				return "Velocidad";
			}
			case GENERIC_ATTACK_DAMAGE -> {
				return "Daño por golpe";
			}
			case GENERIC_ATTACK_KNOCKBACK -> {
				return "Empuje de ataque";
			}
			case GENERIC_ATTACK_SPEED -> {
				return "Velocidad de ataque";
			}
			case GENERIC_ARMOR -> {
				return "Armadura";
			}
			case GENERIC_ARMOR_TOUGHNESS -> {
				return "Resistencia de armadura";
			}
			case GENERIC_LUCK -> {
				return "Suerte";
			}
		}
		return  null;
	}

	private void writeRequirements() {
		if(mcitem.requirements.size() > 0) {
			lore.add("&7&l&m---------&7<&c&lRequisitos&7>&7&l&m----------");
			var requirements = new Requirements(AureliumAPI.getPlugin());
			var temp = new ItemStack(Material.EGG);
			for (var stat: mcitem.requirements.entrySet()) {
				if(stat.getValue() > 0) {
					requirements.addLore(type, temp, stat.getKey(), stat.getValue(), Lang.getDefaultLanguage());
				}
			}
			var tempItemMeta = temp.getItemMeta();
			if(tempItemMeta != null) {
				List<String> templore = tempItemMeta.hasLore() ? tempItemMeta.getLore() : new ArrayList<>();

				for (int i = 0; i < templore.size(); i++) {
					templore.set(i, templore.get(i).replace("Requiere ", ""));
				}

				lore.addAll(templore);
			}
		}
	}

	private void writeMultipliers() {
		if(mcitem.multipliers.size() > 0) {
			lore.add("&7&l&m-------&7<&b&lBonus de Skills&7>&7&l&m-------");
			var multiplier = new Multipliers(AureliumAPI.getPlugin());
			var temp = new ItemStack(Material.EGG);
			for (var stat: mcitem.multipliers.entrySet()) {
				if(stat.getValue() > 0) {
					multiplier.addLore(type, temp, stat.getKey(), stat.getValue(), Lang.getDefaultLanguage());
				}
			}
			var tempItemMeta = temp.getItemMeta();
			if(tempItemMeta != null) {
				List<String> templore = tempItemMeta.hasLore() ? tempItemMeta.getLore() : new ArrayList<>();

				for (int i = 0; i < templore.size(); i++) {
					templore.set(i, templore.get(i).replace("Al sostener: ", ""));
				}

				templore.removeIf(s -> s.equals(" "));
				lore.addAll(templore);
			}
		}
	}

	private void writeStats() {
		if(mcitem.stats.size() > 0) {
			lore.add("&7&l&m--------&7<&a&lEstadisticas&7>&7&l&m---------");
			var modifier = new Modifiers(AureliumAPI.getPlugin());
			var temp = new ItemStack(Material.EGG);
			for (var stat: mcitem.stats.entrySet()) {
				if(stat.getValue() != 0) {
					modifier.addLore(type, temp, stat.getKey(), stat.getValue(), Lang.getDefaultLanguage());
				}
			}
			var tempItemMeta = temp.getItemMeta();
			if(tempItemMeta != null && tempItemMeta.hasLore())
				lore.addAll(tempItemMeta.getLore());
		}
	}

	private void writeEnchants() {
		var enchants = meta.getEnchants();
		if(enchants.size() > 0) {
			lore.add("&7&l&m-------&7<&5&lEncantamientos&7>&7&l&m--------");
			for (var enchant : enchants.entrySet()) {
				var str = "";
				if (enchant.getKey().isCursed() || enchant.getKey().getKey().getKey().contains("curse")) {
					str = "&c☠";
				} else if (enchant.getKey().isTreasure()) {
					str = "&a☆";
				} else {
					str = "&7◇";
				}

				str += getEnchatName(enchant.getKey()) + " ";
				if (enchant.getKey().getMaxLevel() > 1) {
					str += enchant.getValue().toString();
				}

				lore.add(str);
			}
		}
	}

	private String getEnchatName(Enchantment key) {
		if(key.equals(Enchantment.PROTECTION_ENVIRONMENTAL)) { return "Protección"; }
		if(key.equals(Enchantment.PROTECTION_FIRE)) { return "Protección al Fuego"; }
		if(key.equals(Enchantment.PROTECTION_FALL)) { return "Caída de Pluma"; }
		if(key.equals(Enchantment.PROTECTION_EXPLOSIONS)) { return "Protección contra Explosiones"; }
		if(key.equals(Enchantment.PROTECTION_PROJECTILE)) { return "Protección contra Proyectiles"; }
		if(key.equals(Enchantment.OXYGEN)) { return "Respiración"; }
		if(key.equals(Enchantment.WATER_WORKER)) { return "Afinidad Acuática"; }
		if(key.equals(Enchantment.THORNS)) { return "Espinas"; }
		if(key.equals(Enchantment.DEPTH_STRIDER)) { return "Agilidad Acuática"; }
		if(key.equals(Enchantment.FROST_WALKER)) { return "Paso Helado"; }
		if(key.equals(Enchantment.BINDING_CURSE)) { return "Maldición de Ligamiento"; }
		if(key.equals(Enchantment.DAMAGE_ALL)) { return "Filo"; }
		if(key.equals(Enchantment.DAMAGE_UNDEAD)) { return "Castigo"; }
		if(key.equals(Enchantment.DAMAGE_ARTHROPODS)) { return "Perdición de los Artrópodos"; }
		if(key.equals(Enchantment.KNOCKBACK)) { return "Empuje"; }
		if(key.equals(Enchantment.FIRE_ASPECT)) { return "Aspecto Ígneo"; }
		if(key.equals(Enchantment.LOOT_BONUS_MOBS)) { return "Saqueo"; }
		if(key.equals(Enchantment.SWEEPING_EDGE)) { return "Filo Arrasador"; }
		if(key.equals(Enchantment.DIG_SPEED)) { return "Eficiencia"; }
		if(key.equals(Enchantment.SILK_TOUCH)) { return "Toque de Seda"; }
		if(key.equals(Enchantment.DURABILITY)) { return "Irrompibilidad"; }
		if(key.equals(Enchantment.LOOT_BONUS_BLOCKS)) { return "Fortuna"; }
		if(key.equals(Enchantment.ARROW_DAMAGE)) { return "Poder"; }
		if(key.equals(Enchantment.ARROW_KNOCKBACK)) { return "Retroceso"; }
		if(key.equals(Enchantment.ARROW_FIRE)) { return "Llama"; }
		if(key.equals(Enchantment.ARROW_INFINITE)) { return "Infinidad"; }
		if(key.equals(Enchantment.LUCK)) { return "Suerte"; }
		if(key.equals(Enchantment.LURE)) { return "Atracción"; }
		if(key.equals(Enchantment.LOYALTY)) { return "Lealtad"; }
		if(key.equals(Enchantment.IMPALING)) { return "Empalamiento"; }
		if(key.equals(Enchantment.RIPTIDE)) { return "Impulso"; }
		if(key.equals(Enchantment.CHANNELING)) { return "Canalización"; }
		if(key.equals(Enchantment.MULTISHOT)) { return "Multidisparo"; }
		if(key.equals(Enchantment.QUICK_CHARGE)) { return "Carga Rápida"; }
		if(key.equals(Enchantment.PIERCING)) { return "Perforación"; }
		if(key.equals(Enchantment.MENDING)) { return "Reparación"; }
		if(key.equals(Enchantment.VANISHING_CURSE)) { return "Maldición de Desaparición"; }
		if(key.equals(Enchantment.SOUL_SPEED)) { return "Velocidad del Alma"; }

		return key.getName();
	}

	private void writeInfo() {
		if(mcitem.canlevel || mcitem._class != Class.NONE || !mcitem.lore.isEmpty()) {
			lore.add("&7&l&m---------&7<&7&lInformación&7>&7&l&m---------");
			var line1 = "&7";
			if (mcitem.canlevel) {
				line1 += "Nivel: &f" + mcitem.level + " &7";
			}
			if (mcitem._class != Class.NONE) {
				line1 += "Clase: &6" + mcitem._class.getName();
			}
			if (line1.length() > 2) {
				lore.add(line1);
				lore.add(" ");
			}

			for (var line : mcitem.lore) {
				lore.add("&7" + line);
			}
		}
	}
}
