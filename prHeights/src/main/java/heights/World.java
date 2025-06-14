package heights;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.File;

public class World {

	List<Country> countries;
	
	public World() {
		countries = new ArrayList<>();
	}

	public List<Country> getCountries(){
		return countries;
	}
	
	public void load(String file) throws FileNotFoundException{
		countries.clear();
		
			Scanner sc = new Scanner(new File(file));
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				
				try {
		            String[] parts = line.split(",");

		            String name = parts[0];
		            String continent = parts[1];
		            double height = Double.parseDouble(parts[2]);
		            
		            Country c = new Country(name,continent,height);
		            
		            countries.add(c);
					
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
						
			sc.close();
	}
	
	public SortedMap<String, Integer> numberOfCountriesPerContinent(){
		SortedMap<String,Integer> countriesPerC = new TreeMap<>();
		
		for(Country country : countries) {
			String continent = country.getContinent();
			if(!countriesPerC.containsKey(continent)) {
				countriesPerC.put(continent, 1);
			}
			else {
				int count = countriesPerC.get(continent);
				countriesPerC.put(continent, count + 1);
			}
		}
		return countriesPerC;
	}
	
	public SortedMap<Double, List<Country>> countriesPerHeight(){
		SortedMap<Double, List<Country>> countriesPerH = new TreeMap<>();
		
		for(Country country : countries) {
			double original = country.getHeight();
			double truncated = Math.floor(original * 10) / 10.0;
			
			if(!countriesPerH.containsKey(truncated)) {
				List<Country> list = new ArrayList<>();
				list.add(country);
				countriesPerH.put(truncated, list);                      
			}
			else {
				countriesPerH.get(truncated).add(country);
			}
		}
		return countriesPerH;
	}
	
	public SortedMap<String, SortedSet<Country>> countriesPerContinent(){
		SortedMap<String, SortedSet<Country>> countriesPerC = new TreeMap<>();
		
		for(Country country : countries) {
			String continent = country.getContinent();
			
			if(!countriesPerC.containsKey(continent)) {
				SortedSet<Country> list = new TreeSet<>();
				list.add(country);
				countriesPerC.put(continent, list);
			}
			else {
				countriesPerC.get(continent).add(country);
			}
		}
		
		return countriesPerC;
	}
	
	public SortedMap<Character, SortedSet<Country>> countriesPerInitial(){
		SortedMap<Character, SortedSet<Country>> countriesPerI = new TreeMap<>();

		for(Country country : countries) {
			char initial = country.getName().charAt(0);
			
			if(!countriesPerI.containsKey(initial)) {
				SortedSet<Country> list = new TreeSet<>();
				list.add(country);
				countriesPerI.put(initial, list);
			}
			else {
				countriesPerI.get(initial).add(country);
			}
		}
		return countriesPerI;
	}
	
	
	public SortedMap<String, Double> averagePerContinent() {
		SortedMap<String, SortedSet<Country>> map = countriesPerContinent();
		
		SortedMap<String, Double> averageMap = new TreeMap<>();

		for(Map.Entry<String,SortedSet<Country>> entry : map.entrySet()) {
			String continent = entry.getKey();
			SortedSet<Country> countries = entry.getValue();
			
			double totalH = 0.0;
			int count = 0;
			
			for(Country country : countries) {
				totalH += country.getHeight();
				count ++;
			}
			double avg = totalH / count;
			
			averageMap.put(continent, avg);
		}
		return averageMap;
	}
	
	public List<String> continentsWithMoreCountries(){
		List<String> continents = new ArrayList<>();
		
		SortedMap<String, Integer> countriesPerC = numberOfCountriesPerContinent();
		
		int maxCount = 0;
		
		for(Map.Entry<String,Integer> entry : countriesPerC.entrySet()) {
			int act = entry.getValue();
			
			if(act > maxCount) maxCount = act;
		}
		
		for(Map.Entry<String,Integer> entry2 : countriesPerC.entrySet()) {
			if(entry2.getValue() == maxCount) {
				continents.add(entry2.getKey());
			}
		}
		
		return continents;	
	}
	
	public static<K, V> void presentInPW(PrintWriter pw, Map<K, V> map) {
		for(Map.Entry<K,V>entry : map.entrySet()) {
			pw.println(entry.getKey() + "\t" + entry.getValue());
		}
		
	}
	
	public static<K, V> void presentOnConsole(Map<K, V> map) {
		PrintWriter pw = new PrintWriter(System.out,true);
		
		presentInPW(pw,map);
	}
	
	public SortedSet<Country> countriesOrderedByHeight(){
		SortedSet<Country> set = new TreeSet<>();
		
		Comparator.comparingDouble(Country::getHeight);
		
		set.addAll(countries);
		return set;
	}
	
	public SortedMap<String, SortedSet<Country>> countriesByHeightPerContinent() {
	    SortedMap<String, SortedSet<Country>> map = new TreeMap<>();
	    Comparator<Country> byHeight = Comparator.comparingDouble(Country::getHeight);
	    
	    for (Country country : countries) {
	        String continent = country.getContinent();
	        map.computeIfAbsent(continent, c -> new TreeSet<>(byHeight));
	        map.get(continent).add(country);
	    }
	    
	    return map;
	}

	public SortedMap<String, SortedSet<Country>> countriesByReverseHeightPerContinent() {
	    SortedMap<String, SortedSet<Country>> map = new TreeMap<>();
	    Comparator<Country> byReverseHeight = Comparator.comparingDouble(Country::getHeight).reversed(); // Same as before but just adding this
	    
	    for (Country country : countries) {
	        String continent = country.getContinent();
	        map.computeIfAbsent(continent, c -> new TreeSet<>(byReverseHeight));
	        map.get(continent).add(country);
	    }
	    
	    return map;
	}

	
	
}
