package hospital;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class HospitalPlus extends Hospital{

	public HospitalPlus(String name, int floors, int roomspf) throws HospitalException {
		super(name, floors, roomspf);
	}

	public SortedMap<Integer, SortedSet<Patient>> patientsPerYear(){
		SortedMap<Integer,SortedSet<Patient>> map = new TreeMap<>();

		for(Entry<Patient, Room> entry : rooms.entrySet()) {
			Patient p = entry.getKey();
			int year = p.getBirthYear();
			
			if(!map.containsKey(year)) { // If a set with that year doesnt exist we create a new set
				map.put(year, new TreeSet<>());
			}
			
			map.get(year).add(p); // We obtain the already existing set for that year and add the new patient
		}
		return map;
	}
	
	public SortedMap<Integer, Integer> numberOfPatientsPerYear() {
		SortedMap<Integer, Integer> map = new TreeMap<>();
	
		for(Patient p : rooms.keySet()) {
			int year = p.getBirthYear();
			if(!map.containsKey(year)) {
				map.put(year,1);
			}
			else {
				map.put(year, map.get(year) + 1); // get() obtains the key associated to the map and updates 
			}									  // total count already existing for that year
			
		}
		return map;
	}
	
	
}
