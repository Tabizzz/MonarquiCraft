package me.tabizzz.monarquicraft.Classes;

public enum Class
{
	NONE("Humano"),
	MELEE("Asesino"),
	MAGE("Mago"),
	RANGER("Arquero"),
	TANK("Tanque"),
	HEALER("Curador");

	private final String name;

	Class(String Name) {
		name = Name;
	}

	public String getName() {
		return name;
	}
}
