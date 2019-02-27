package battleship;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private List<Ship> ships = null;
	private Cell cells[][] = null;
	
	
	
	public Board() {
		ships = new ArrayList<Ship>(IConstants.NO_OF_SHIPS);
	}

	public Board(List<Ship> ships) {
		this.ships = ships;
		initCell();
	}

	private void initCell() {
		cells  = new Cell[IConstants.GRID_ROW][IConstants.GRID_COL];
		
		for (int i = 0; i < IConstants.GRID_ROW; i++) {
			for (int j = 0; j < IConstants.GRID_COL; j++) {
				cells[i][j] = new Cell(false, null);
			}
		}
		
		for (Ship ship : ships) {
			placeShip(ship);
		}
		
	}

	private void placeShip(Ship ship) {
		
		if (ship.getAlignment().equals(Alignment.HORIZONTAL)) {
			for (int i = ship.getPosition().getY()-1; i < ship.getPosition().getY() -1 + ship.getShipType().getLifeSpan(); i++) {
				cells[ship.getPosition().getX()-1][i] = new Cell(true, ship.getName());
			}
		} else {
			for (int i = ship.getPosition().getX()-1; i < ship.getPosition().getX() -1 + ship.getShipType().getLifeSpan(); i++) {
				cells[i][ship.getPosition().getY()-1] = new Cell(true, ship.getName());
			}
		}
	}

	public void showGrid() {
		for (int i = 0; i < IConstants.GRID_ROW; i++) {
			for (int j = 0; j < IConstants.GRID_COL; j++) {
				if (cells[i][j]!= null && cells[i][j].isPresent())
					System.out.print(" 1");
				else
					System.out.print(" 0");
			}
			System.out.println("");
		}
	}
	
	public String play(int x,int y){
		
		if(cells[x-1][y-1].isPresent()){
			cells[x-1][y-1].setPresent(false);
			return "HIT " + cells[x-1][y-1].getShipName();
		}
		return "MISS";
	}
	

}
