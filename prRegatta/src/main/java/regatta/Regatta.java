package regatta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Regatta {

	SortedSet<Ship> participants;
	
	public Regatta() {
		participants = new TreeSet<>();
	}
	
	public void add(Ship b) {
		participants.add(b); // TreeSet already manages duplicates with equals/compareTo
	}
	
	public void move(int mnt) {
		for(Ship ship : participants) {
			ship.move(mnt);
		}
	}

	public Set<Ship> getParticipants() {
		return participants;
	}
	
	public boolean speedGreaterThan(int speed) {
		for(Ship ship : participants) {
			if(ship.getSpeed() >= speed) {
				return true;
			}
		}
		return false;
	}
	
	public Set<Ship> sortedByDistance(){
		Set<Ship> set = new TreeSet<>(new SatShip());
		
		set.addAll(participants);
		return set;
	}
	
	public List<Ship> insideTheCircle(Position p, int km){
		List<Ship> inside = new ArrayList<>();
		
		for(Ship ship : participants) {
			if(ship.getPosition().distance(p) < km) {
				inside.add(ship);
			}
		}
		return inside;
	}
	
	public Map<Integer, Set<Ship>> shipsBySpeed(){
		Map<Integer,Set<Ship>> map = new TreeMap<>();
		
		int speedLevel = 0;
		
		for(Ship ship : participants) {
			speedLevel = ship.getSpeed() / 10;
			
			if(!map.containsKey(speedLevel)) {
				map.put(speedLevel, new TreeSet<>());
			}
			map.get(speedLevel).add(ship);
		}
		return map;
	}
	
	public Ship createShipString(String line) {
	    try {
	        String[] parts = line.split("[ ,]+");

	        if (parts.length != 5) {
	            throw new RegattaException("Invalid number of fields: " + line);
	        }

	        String name = parts[0];
	        double lat = Double.parseDouble(parts[1]);
	        double lon = Double.parseDouble(parts[2]);
	        int direction = Integer.parseInt(parts[3]);
	        int speed = Integer.parseInt(parts[4]);

	        Position pos = new Position(lat, lon);
	        return new Ship(name, pos, direction, speed);
	    } catch (Exception e) {
	        throw new RegattaException("Invalid input format: " + line);
	    }
	}
	
	public void readFile(String filename) {
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
			read(sc);
			sc.close();
		} catch (FileNotFoundException e) {
			throw new RegattaException("File not found: " + filename);
		}
	}
	
	public void read(Scanner sc) {
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			try {
				Ship s = createShipString(line);
				add(s);
			} catch (RegattaException e) {
				//
			}
		}
	}

	
	public void writeToFile(String filename) {
		try(PrintWriter pw = new PrintWriter(filename)){
			writeTo(pw);
		}
		catch(FileNotFoundException e) {
			throw new RegattaException("File not found: " + filename);
		}
	}
	
	public void writeTo(PrintWriter pw) {
		for(Ship ship : participants) {
			pw.println(ship);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
