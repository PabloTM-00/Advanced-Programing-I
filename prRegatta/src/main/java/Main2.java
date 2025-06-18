import regatta.Ship;
import regatta.Position;
import regatta.Regatta;
import regatta.SailingBoat;

public class Main2 {
	public static void main(String [] args) {
		Ship[] ships = {
				new Ship("alisa", new Position(30,  240), 80, 20),
				new SailingBoat("veraVela", new Position(-30, 290), 20, 14),
				new Ship("kamira", new Position(80, 182), 230, 33),
				new Ship("gamonal", new Position(0, -260), 0, 24),			
		};
		
		Regatta regatta = new Regatta();
		for(Ship ship : ships) {
			regatta.add(ship);
		}
		System.out.println(regatta.getParticipants());
		regatta.move(10);
		System.out.println(regatta.getParticipants());
		System.out.println(regatta.speedGreaterThan(28));
		System.out.println(regatta.insideTheCircle(new Position(0, 0), 300));		
		System.out.println(regatta.sortedByDistance());
		System.out.println(regatta.shipsBySpeed());
		
	}
}
