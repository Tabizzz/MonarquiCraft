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
		var uuidarmor = UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB151");
		var uuidtoughness = UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150");
		var uuidknockback = UUID.fromString("59616656-7844-48C5-A3D8-31C8FAD89814");

		switch (material) {
			case CHAINMAIL_HELMET, TURTLE_HELMET, IRON_HELMET, GOLDEN_HELMET ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 2,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			case LEATHER_HELMET ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 1,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			case DIAMOND_HELMET -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(uuidtoughness, "mcbase.armor_toughness", 2,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			}
			case NETHERITE_HELMET -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(uuidtoughness, "mcbase.armor_toughness", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				dev.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(uuidknockback, "mcbase.knockback_resistance", 1/10f,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForChest(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		var uuidarmor = UUID.fromString("9F3D476D-C118-4544-8365-64846904B48F");
		var uuidtoughness = UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E");
		var uuidknockback = UUID.fromString("59616656-7844-48C5-A3D8-31C8FAD89814");

		switch (material) {
			case LEATHER_CHESTPLATE ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			case CHAINMAIL_CHESTPLATE, GOLDEN_CHESTPLATE ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			case IRON_CHESTPLATE ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 6,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			case DIAMOND_CHESTPLATE -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(uuidtoughness, "mcbase.armor_toughness", 2,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			}
			case NETHERITE_CHESTPLATE -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(uuidtoughness, "mcbase.armor_toughness", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				dev.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(uuidknockback, "mcbase.knockback_resistance", 1/10f,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForLegs(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		var uuidarmor = UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0E");
		var uuidtoughness = UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D");
		var uuidknockback = UUID.fromString("59616656-7844-48C5-A3D8-31C8FAD89814");

		switch (material) {
			case LEATHER_LEGGINGS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 2,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			case GOLDEN_LEGGINGS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			case CHAINMAIL_LEGGINGS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			case IRON_LEGGINGS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			case DIAMOND_LEGGINGS -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 6,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(uuidtoughness, "mcbase.armor_toughness", 2,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			}
			case NETHERITE_LEGGINGS -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 6,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(uuidtoughness, "mcbase.armor_toughness", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				dev.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(uuidknockback, "mcbase.knockback_resistance", 1/10f,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForFeet(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		var uuidarmor = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6C");
		var uuidtoughness = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
		var uuidknockback = UUID.fromString("59616656-7844-48C5-A3D8-31C8FAD89814");

		switch (material) {
			case LEATHER_BOOTS, GOLDEN_BOOTS, CHAINMAIL_BOOTS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 1,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
			case IRON_BOOTS ->
					dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 2,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
			case DIAMOND_BOOTS -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(uuidtoughness, "mcbase.armor_toughness", 2,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
			}
			case NETHERITE_BOOTS -> {
				dev.put(Attribute.GENERIC_ARMOR, new AttributeModifier(uuidarmor, "mcbase.armor", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
				dev.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(uuidtoughness, "mcbase.armor_toughness", 3,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
				dev.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(uuidknockback, "mcbase.knockback_resistance", 1/10f,
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
			dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "mcbase.attack_speed", 1.1 - 4,
					AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF"), "mcbase.attack_damage", 8,
					AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			return dev;
		}

		return new HashMap<>();
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForHoe(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		var uuid = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

		switch (material) {
			case WOODEN_HOE, GOLDEN_HOE ->
					dev.put(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(uuid, "mcbase.attack_speed", 1-4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case STONE_HOE ->
					dev.put(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(uuid, "mcbase.attack_speed", 2-4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case IRON_HOE ->
					dev.put(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(uuid, "mcbase.attack_speed", 3-4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForAxe(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		var uuiddamage = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
		var uuidspeed = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

		switch (material) {
			case WOODEN_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuiddamage, "mcbase.attack_damage", 6,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(uuidspeed, "mcbase.attack_speed", 0.8 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case GOLDEN_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuiddamage, "mcbase.attack_damage", 6,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(uuidspeed, "mcbase.attack_speed", 1 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case STONE_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuiddamage, "mcbase.attack_damage", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(uuidspeed, "mcbase.attack_speed", 0.8 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case IRON_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuiddamage, "mcbase.attack_damage", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(uuidspeed, "mcbase.attack_speed", 0.9 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case DIAMOND_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuiddamage, "mcbase.attack_damage", 8,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(uuidspeed, "mcbase.attack_speed", 1 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
			case NETHERITE_AXE -> {
				dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuiddamage, "mcbase.attack_damage", 9,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
				dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(uuiddamage, "mcbase.attack_speed", 1 - 4,
						AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			}
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForPickaxe(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		var uuid = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
		dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "mcbase.attack_speed", 1.2 - 4,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

		switch (material) {
			case WOODEN_PICKAXE, GOLDEN_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 1,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case STONE_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 2,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case IRON_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case DIAMOND_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case NETHERITE_PICKAXE ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForShovel(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		var uuid = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
		dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "mcbase.attack_speed", 1 - 4,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

		switch (material) {
			case WOODEN_SHOVEL, GOLDEN_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 1.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case STONE_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 2.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case IRON_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 3.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case DIAMOND_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 4.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case NETHERITE_SHOVEL ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 5.5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		}

		return dev;
	}

	private static Map<Attribute, AttributeModifier> getDefaultAttributeModifiersForSword(Material material) {
		var dev = new HashMap<Attribute, AttributeModifier>();
		var uuid = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

		dev.put(Attribute.GENERIC_ATTACK_SPEED,  new AttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "mcbase.attack_speed", 1.6 - 4,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

		switch (material) {
			case WOODEN_SWORD, GOLDEN_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 3,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case STONE_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 4,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case IRON_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 5,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case DIAMOND_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 6,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
			case NETHERITE_SWORD ->
					dev.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(uuid, "mcbase.attack_damage", 7,
							AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		}
		return dev;
	}

	public static UUID UUIDForAttribute(Attribute key) {
		switch (key) {

			case GENERIC_MAX_HEALTH -> {
				return UUID.fromString("2a769ea0-6477-4f20-828b-c8d37c702b15");
			}
			case GENERIC_FOLLOW_RANGE -> {
				return UUID.fromString("fdf7994e-c9e8-4a78-8b70-d7ab373d0b96");
			}
			case GENERIC_KNOCKBACK_RESISTANCE -> {
				return UUID.fromString("f50d0061-9a4c-4fe6-8c8e-300f60ce2c45");
			}
			case GENERIC_MOVEMENT_SPEED -> {
				return UUID.fromString("511774f5-6d68-4f28-88c8-7ab8e5ebf55b");
			}
			case GENERIC_FLYING_SPEED -> {
				return UUID.fromString("1c5659d8-84de-4104-84f5-d29b28b70b3b");
			}
			case GENERIC_ATTACK_DAMAGE -> {
				return UUID.fromString("05e71246-62a4-4516-9092-c7051962b0c1");
			}
			case GENERIC_ATTACK_KNOCKBACK -> {
				return UUID.fromString("b56e0d06-aeb4-4dbd-ba95-6e1d7b851a49");
			}
			case GENERIC_ATTACK_SPEED -> {
				return UUID.fromString("19b323ef-dea0-4061-9275-5fe6ed9ed0f9");
			}
			case GENERIC_ARMOR -> {
				return UUID.fromString("eb95a7cc-02f0-48e2-9ff2-6a490196c663");
			}
			case GENERIC_ARMOR_TOUGHNESS -> {
				return UUID.fromString("acec9d6d-3c62-4d81-8a41-4980f30d0928");
			}
			case GENERIC_LUCK -> {
				return UUID.fromString("35f4d817-3155-4cd1-bb4a-3e1aab05c1b2");
			}
			case HORSE_JUMP_STRENGTH -> {
				return UUID.fromString("8d0c9bcb-4c09-4980-9d3d-5c85b0e7b73d");
			}
			case ZOMBIE_SPAWN_REINFORCEMENTS -> {
				return UUID.fromString("c0510c2b-0482-4dcd-85f6-c83a3dd2d7a3");
			}
		}
		return UUID.fromString("17d66b22-b19a-4e24-9a5c-5039c14ef039");
	}
}
