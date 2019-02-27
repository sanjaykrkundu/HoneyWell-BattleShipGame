package battleship;

public class Ship {
	private Coordinate position;
	private Alignment alignment;
	private int health;
	private ShipType shipType;

	public Ship(Coordinate position, Alignment alignment, int health, ShipType shipType) {
		super();
		this.position = position;
		this.alignment = alignment;
		this.health = health;
		this.shipType = shipType;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Ship [position=" + position + ", alignment=" + alignment + ", health=" + health + ", shipType="
				+ shipType + "]";
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName(){
		return this.shipType.getName();
	}
	
	
}
