package battleship;

public enum ShipType {
	DESTROYER(2, "Destroyer"), SUBMARINE(3, "Submarine"), CRUISER(3, "Cruiser"), BATTLESHIP(4, "Battleship"), CARRIER(5,
			"Carrier");
	private int lifeSpan;
	private String name;

	ShipType(int lifeSpan, String name) {
		this.lifeSpan = lifeSpan;
		this.name = name;
	}

	public int getLifeSpan() {
		return lifeSpan;
	}

	public String getName() {
		return name;
	}

}
