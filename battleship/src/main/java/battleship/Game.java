package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Board myBoard;
		CommunicationI communication = new Communication();
		int kill = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Ship details per line seperated with space\n {x y alignment shiptype}");
		List<Ship> ships = new ArrayList<>(IConstants.NO_OF_SHIPS);

		for (int i = 0; i < IConstants.NO_OF_SHIPS; i++) {
			String line = scanner.nextLine();
			ships.add(getShip(line));
		}

		myBoard = new Board(ships);
		myBoard.showGrid();
		
		if (communication.isFirstPlayer()) {
			while (kill != IConstants.TOTAL_KILL_TO_WIN) {
				System.out.print("Play player1 {x y} : ");
				String position = scanner.nextLine();
				communication.speak("Player1 played : " + position);
				// TODO
				//server wont be able to listen. its not yet implemented.
				System.out.println("waiting for player2 ...");
				String response = communication.listen();
				System.out.println("player2 played " + response);
			}

		} else {
			while (kill != IConstants.TOTAL_KILL_TO_WIN) {
				System.out.println("waiting for player1 ...");
				String response = communication.listen();
				System.out.println("player1 played " + response);
				//server wont be able to listen. its not yet implemented.
				
				if(!"".equals(response.trim())){
					String ar[] = response.split(" ");
					response = myBoard.play(Integer.parseInt(ar[0]), Integer.parseInt(ar[1]));
				}
				communication.speak(response);
				System.out.print("Play Player2 {x y} : ");
				String position = scanner.nextLine();
				communication.speak("Player2 played : " + position);
			}
		}
		if(kill== IConstants.TOTAL_KILL_TO_WIN){
			System.out.println("You Win");
		}
		scanner.close();
		communication.close();
	}

	private static Ship getShip(String str) {
		int x, y;
		ShipType shipType = null;
		Alignment alignment = Alignment.HORIZONTAL;
		String arr[] = str.split(" ");
		x = Integer.parseInt(arr[0]);
		y = Integer.parseInt(arr[1]);
		if ("v".equalsIgnoreCase(arr[2])) {
			alignment = alignment.VERTICLE;
		}

		switch (arr[3].toLowerCase()) {
		case "destroyer":
			shipType = shipType.DESTROYER;
			break;
		case "submarine":
			shipType = shipType.SUBMARINE;
			break;
		case "cruiser":
			shipType = shipType.CRUISER;
			break;
		case "battleship":
			shipType = shipType.BATTLESHIP;
			break;
		case "carrier":
			shipType = shipType.CARRIER;
			break;
		}

		Ship ship = new Ship(new Coordinate(x, y), alignment, shipType.getLifeSpan(), shipType);
		return ship;
	}

}
