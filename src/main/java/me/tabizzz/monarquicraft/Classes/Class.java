package me.tabizzz.monarquicraft.Classes;

public enum Class
{
	NONE("Humano"), // armas vanilla
	MELEE("Asesino"), // dagas (espada)
	MAGE("Mago"),	//
	RANGER("Arquero"), // arco
	TANK("Tanque"), // martillo (hacha)
	HEALER("Sacerdote"), //
	BRUISER("Luchador"), // guadaña (hoz)
	DRUID("Druida"),	//
	WIZARD("Brujo"), //
	HUNTER("Cazador"), // ballesta
	JUGGERNAUT("Juggernaut"); // hacha

	private final String name;

	Class(String Name) {
		name = Name;
	}

	public String getName() {
		return name;
	}
}
