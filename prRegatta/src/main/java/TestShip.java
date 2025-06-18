import java.util.Arrays;

import regatta.Ship;
import regatta.Position;

public class TestShip {
	public static void main(String [] args) {
		Ship[] ships = {
				new Ship("alisa", new Position(340,  -120), 80,  20),
				new Ship("vera", new Position(-30,  290), 20,  25),
				new Ship("kamira", new Position(400,  182), 230,  33),
				new Ship("gamonal", new Position(-390,  -190), 0,  24),			
		};

		Arrays.sort(ships);
		System.out.println(ships[0]);
		System.out.println(ships[ships.length-1]);
		
	}
}
