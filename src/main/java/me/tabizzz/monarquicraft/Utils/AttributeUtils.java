package me.tabizzz.monarquicraft.Utils;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AttributeUtils {

	public static void setAttributesToDisplay(Map<Attribute, Double> attributes){
		for (var attribute: attributes.entrySet()) {
			switch (attribute.getKey()) {
				case GENERIC_ATTACK_SPEED -> attributes.put(attribute.getKey(), attribute.getValue() + 4);
				case GENERIC_KNOCKBACK_RESISTANCE -> attributes.put(attribute.getKey(), attribute.getValue() * 10);
				case GENERIC_ATTACK_DAMAGE -> attributes.put(attribute.getKey(), attribute.getValue() + 1);
				case GENERIC_MOVEMENT_SPEED -> attributes.put(attribute.getKey(), attribute.getValue() * 1000);
			}
		}
	}

	public static Map<Attribute, AttributeModifier> getDefaultAttributeModifiers(Material material, EquipmentSlot slot){
		switch (slot) {

			case HAND -> {
				return getDefaultAttributeModifiersForHand(material);
			}
			case OFF_HAND -> {
				return getDefaultAttributeModifiersForOffHand(material);
			}
			case FEET -> {
				return getDefaultAttributeModifiersForFeet(material);
			}
			case LEGS -> {
				return getDefaultAttributeModifiersForLegs(material);
			}
			case CHEST -> {
				return getDefaultAttributeModifiersForChest(material);
			}
			case HEAD -> {
				return getDefaultAttributeModifiersForHead(material);
			}
		}
		return null;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForHead(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();

		switch (material) {
			case CHAINMAIL_HELMET, TURTLE_HELMET, IRON_HELMET, GOLDEN_HELMET ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 2,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			case LEATHER_HELMET ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 1,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			case DIAMOND_HELMET -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "mcbase.armor_toughness", 2,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			}
			case NETHERITE_HELMET -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "mcbase.armor_toughness", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				dev.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "mcbase.knockback_resistance", 1/10f,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForChest(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();

		switch (material) {
			case LEATHER_CHESTPLATE ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			case CHAINMAIL_CHESTPLATE, GOLDEN_CHESTPLATE ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			case IRON_CHESTPLATE ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 6,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			case DIAMOND_CHESTPLATE -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "mcbase.armor_toughness", 2,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			}
			case NETHERITE_CHESTPLATE -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "mcbase.armor_toughness", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				dev.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "mcbase.knockback_resistance", 1/10f,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForLegs(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();

		switch (material) {
			case LEATHER_LEGGINGS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 2,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			case GOLDEN_LEGGINGS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			case CHAINMAIL_LEGGINGS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			case IRON_LEGGINGS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			case DIAMOND_LEGGINGS -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 6,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "mcbase.armor_toughness", 2,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			}
			case NETHERITE_LEGGINGS -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 6,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "mcbase.armor_toughness", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				dev.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "mcbase.knockback_resistance", 1/10f,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForFeet(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();

		switch (material) {
			case LEATHER_BOOTS, GOLDEN_BOOTS, CHAINMAIL_BOOTS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 1,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
			case IRON_BOOTS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 2,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
			case DIAMOND_BOOTS -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "mcbase.armor_toughness", 2,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
			}
			case NETHERITE_BOOTS -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "mcbase.armor", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "mcbase.armor_toughness", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
				dev.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "mcbase.knockback_resistance", 1/10f,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForOffHand(Material ignoredMaterial) {
		return new HashMap<>();
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForHand(Material material) {
		if(material.name().contains("_SWORD")) {
			return getDefaultAttributeModifiersForSword(material);
		}
		if(material.name().contains("_SHOVEL")) {
			return getDefaultAttributeModifiersForShovel(material);
		}
		if(material.name().contains("_PICKAXE")) {
			return getDefaultAttributeModifiersForPickaxe(material);
		}
		if(material.name().contains("_AXE")) {
			return getDefaultAttributeModifiersForAxe(material);
		}
		if(material.name().contains("_HOE")) {
			return getDefaultAttributeModifiersForHoe(material);
		}
		// special case: trident
		if(material == Material.TRIDENT) {
			var dev = new HashMap<Attribute, AttributeModifier>();
			dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 1.1 - 4,
					AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 8,
					AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			return dev;
		}

		return new HashMap<>();
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForHoe(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();

		switch (material) {
			case WOODEN_HOE, GOLDEN_HOE ->
					dev.put(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 1-4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case STONE_HOE ->
					dev.put(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 2-4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case IRON_HOE ->
					dev.put(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 3-4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForAxe(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();

		switch (material) {
			case WOODEN_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 6,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 0.8 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case GOLDEN_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 6,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 1 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case STONE_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 0.8 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case IRON_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 0.9 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case DIAMOND_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 1 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case NETHERITE_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 9,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 1 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForPickaxe(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 1.2 - 4,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

		switch (material) {
			case WOODEN_PICKAXE, GOLDEN_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 1,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case STONE_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 2,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case IRON_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case DIAMOND_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case NETHERITE_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForShovel(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 1 - 4,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

		switch (material) {
			case WOODEN_SHOVEL, GOLDEN_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 1.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case STONE_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 2.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case IRON_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 3.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case DIAMOND_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 4.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case NETHERITE_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 5.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForSword(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.randomUUID(), "mcbase.attack_speed", 1.6 - 4,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

		switch (material) {
			case WOODEN_SWORD, GOLDEN_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case STONE_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case IRON_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case DIAMOND_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 6,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case NETHERITE_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "mcbase.attack_damage", 7,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		}
		return dev;
	}
}
