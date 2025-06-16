package hospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Hospital {

	protected Map<Patient,Room> rooms;
	protected SortedSet<Room> free;
	
	private int floors;
	private String name;
	
	public Hospital(String name, int floors, int roomspf) throws HospitalException {
		if(name == null || name.isEmpty()) {
			throw new HospitalException("Name cant be null or empty");
		}
		if(floors < 0 || roomspf < 0) {
			throw new HospitalException("Number of floors and rooms per floor must be positive");
		}
		
		this.name = name;
		this.floors = floors;
		
		rooms = new HashMap<>();
		free = new TreeSet<>();
		
		for(int f = 0; f < floors ; f++) {
			for(int r = 0; r < roomspf ; r++) {
				free.add(new Room(f,r));
			}
		}
	}
	
	public void checkIn(Patient p) throws HospitalException {
		if(p == null || rooms.containsKey(p) || free.isEmpty()) throw new HospitalException();
		
		Room asign = free.first(); // Returns lowest room (lowest floor and room)
		rooms.put(p, asign);
		free.remove(asign);
	}
	
	public Patient checkOut(String ssn) {
		if(ssn == null) return null;
		
		for(Map.Entry<Patient,Room> entry : rooms.entrySet()){
				Patient p = entry.getKey();
				if(p.getSsn().equals(ssn)) {
					Room r = rooms.remove(p);
					free.add(r);
					return p;
				}
			}
			return null;
		}
	
	public Patient[] selection(Criterion c) {
		List<Patient> selected = new ArrayList<>();
		
		for(Map.Entry<Patient,Room> entry : rooms.entrySet()) {
			Patient p = entry.getKey();
			Room r = entry.getValue();
			if(c.meetsCondition(p, r)) {
				selected.add(p);
			}
		}
		return selected.toArray(new Patient[0]); /* Because we want to convert List<Patient> into 
													an array of Patients[] . toArray() needs
													to know what type of array to create (Patient[[])
													in this case, where java automatically increases 
													its size even if we say its of size 0
		 										*/
	}
	
	public void readPatients(String filename) {
		Scanner sc;
		
		try {
			sc = new Scanner(new File(filename));
			readPatients(sc);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readPatients(Scanner sc) {
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] parts = line.split("\\s*[,]\\s*");
			
			if(parts.length != 4) continue;
			
			String name = parts[0];
			String surname = parts[1];
			String ssn = parts[2];
			int birthYear = Integer.parseInt(parts[3]);
			
			try {
				Patient p = new Patient(name,surname,ssn,birthYear);
				checkIn(p);
			} catch (HospitalException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void writePatients(String f) {
		try {
			PrintWriter pw = new PrintWriter(f);
			writePatients(pw);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void writePatients(PrintWriter pw) {
		Map<Integer, List<Patient>> perFloor = new TreeMap<>();
		
		// Fill map by putting patients per floor
		for(Map.Entry<Patient,Room> entry : rooms.entrySet()) {
			int floor = entry.getValue().getFloor();
			
			perFloor.putIfAbsent(floor, new ArrayList<>());
			perFloor.get(floor).add(entry.getKey());		
		}
		
		// Iterate through floors in order
		for(Map.Entry<Integer,List<Patient>> entry : perFloor.entrySet()) {
			pw.println("Floor " + entry.getKey() + ":");
			for(Patient p : entry.getValue()) {
				pw.println(p);
			}
		}
	}

	@Override
	public String toString() {
		return "Hospital [name=" + name + ", rooms=" + rooms.toString() + ", free=" + free.toString() + "]";
	}
	
	

}
