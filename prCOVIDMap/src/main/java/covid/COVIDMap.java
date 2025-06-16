package covid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class COVIDMap {

	private String region;
	protected SortedMap<String,SortedSet<HealthDistrict>> map;
	
	public COVIDMap(String region, String filename) throws FileNotFoundException {
		super();
		map = new TreeMap<>();
		
		this.region = region;
		
		readData(filename);
	}

	public void readData(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
		
		readData(sc);
		
		sc.close();
	}
	
	public void readData(Scanner sc) {
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] parts = line.split("[():]+");
			
			if(parts.length != 4) continue;
			
			
			try {
				String district = parts[0];
				String province = parts[1];
				int population = Integer.parseInt(parts[2]);
				int cases = Integer.parseInt(parts[3]);
				
				HealthDistrict h = new HealthDistrict(district,population,cases);
				
				if(!map.containsKey(province)) {
					SortedSet<HealthDistrict> set = new TreeSet<>();
					
					set.add(h);
					
					map.put(province, set);
				}
				else {
					map.get(province).add(h);
				}
			}
			catch(COVIDException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void addDistrict(String prov, HealthDistrict hd) {
		if(!map.containsKey(prov)) {
			SortedSet<HealthDistrict> set = new TreeSet<>();
			set.add(hd);
			map.put(prov, set);
		}
		else {
			map.get(prov).add(hd);
		}
	}
	
	public String getRegion() {
		return region;
	}

	public Set<String> getProvinces(){
		return map.keySet();
	}

	public Set<HealthDistrict> getDistricts() {
	    SortedSet<HealthDistrict> result = new TreeSet<>();
	    
	    for (SortedSet<HealthDistrict> set : map.values()) {
	        result.addAll(set);
	    }
	    return result;
	}
	
	public int incidenceOfTheProvincia(String prov) {
		if(!map.containsKey(prov)) {
			return 0;
		}
		
		SortedSet<HealthDistrict> districts = map.get(prov);
		
		int totalPop = 0;
		int totalCases = 0;
		
		for(HealthDistrict hd : districts) {
			totalPop = totalPop + hd.getPopulation();
			totalCases = totalCases + hd.getCovidCases14days();
		}
		
		if(totalPop == 0) return 0;
		
		return (totalCases * 100000)/totalPop;
	}

	public void printMap(String filename) throws FileNotFoundException{
		try(PrintWriter pw = new PrintWriter(filename)){
			printMap(pw);
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void printMap(PrintWriter pw) {
		pw.println(region.toString().toUpperCase() + ":");
		
		for(Map.Entry<String,SortedSet<HealthDistrict>> entry : map.entrySet()) {
			pw.println(entry.getKey()); // Print name of province
			for(HealthDistrict hd  : entry.getValue()) {
				pw.println("\t" + hd.toString());
			}
		}
	}

	public Set<String> getCOVIDInfo(COVIDInfo info){
		return info.getInfo(this); // this passes the actual object of COVIDMap to info, the instance of COVIDInfo
	}

	
}
