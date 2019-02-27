package battleship;

public class Cell {
	private boolean present;
	private String shipName;

	public Cell(boolean present, String shipName) {
		this.present = present;
		this.shipName = shipName;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

}
