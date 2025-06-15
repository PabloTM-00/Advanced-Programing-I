package tvschedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class TVSchedule {

	protected Map<String,Set<TVShow>> stations;
	
	public TVSchedule() {
		stations = new HashMap<>();
	}

	void add(String tvStationName, TVShow show) {
		if(!stations.containsKey(tvStationName)) {
			TreeSet<TVShow> list = new TreeSet<>();
			list.add(show);
			stations.put(tvStationName, list);
		}
		else {
			Set<TVShow> list = stations.get(tvStationName);
			list.add(show);
		}
	}
	
	void readShows (String filePathAndName) {
		try {
			Scanner sc = new Scanner(new File(filePathAndName));
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter("[>@:-]+");
				
				String tvStationName = lineScanner.next();
				String showName = lineScanner.next();
				int hour = Integer.parseInt(lineScanner.next());
				int minute = Integer.parseInt(lineScanner.next());
				int duration = Integer.parseInt(lineScanner.next());
				
				Hour startHour = new Hour(hour,minute);
				TVShow show = new TVShow(showName,startHour,duration);
				add(tvStationName,show);
				
				lineScanner.close();
				sc.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void printShows(PrintWriter pw) {
		for(Map.Entry<String,Set<TVShow>> entry : stations.entrySet()) {
			String tvStation = entry.getKey();
			Set<TVShow> shows = entry.getValue();
			
			pw.println(tvStation + ":");
			
			for(TVShow show : shows) {
				pw.println("\t" + show.toString());
			}
		}
	}
	
	
	public void printShows(String filePathAndName) {
		
		try {
			PrintWriter pw = new PrintWriter(filePathAndName);
			
			printShows(pw);
			
			pw.close();

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public boolean isConsistent(String tvStation) {
	    if (stations.containsKey(tvStation)) {
	        Set<TVShow> shows = stations.get(tvStation);
	        
	        TVShow prev = null;
	        for (TVShow current : shows) {
	            if (prev != null) {
	                if (prev.getFinishHour().compareTo(current.getStartHour()) > 0) {
	                    return false; // This means a program ends after another starts
	                }
	            }
	            prev = current;
	        }
	        return true;
	    } else {
	        throw new TVScheduleException("TV Station not found: " + tvStation);
	    }
	}
	
	public SortedSet<TVShow> advice(TVAdvice selection){
		Collection<TVShow> allShows = new ArrayList<>();
		
		for (Set<TVShow> shows : stations.values()) {
		    allShows.addAll(shows);
		}

		return selection.advice(allShows);

	}

}
