import java.util.SortedSet;
import java.util.TreeSet;

import prScooters.*;

public class TestScooter {
	public static void main(String[] args) {
		SortedSet<Scooter> set = new TreeSet<>();
		
		Position p1 = new Position(-4.4204216, 36.7182771);
		try {
			Scooter s1 = new Scooter("campero",100,p1,3);
			
			Position p2 = new Position(-4.4495993,36.7015323);
			Scooter s2 = new Scooter("Campero",100,p2,2);
			
			Position p3 = new Position(-4.4150382,36.7306184);
			Scooter s3 = new Scooter("Biznaga",101,p3,5);
			
			System.out.println(s1.toString());
			System.out.println(s2.toString());
			System.out.println(s3.toString());
			
			if(s1.equals(s2)) System.out.println("Scooters 1 and 2 are equal");
			
			set.add(s1);
			set.add(s2);
			set.add(s3);
			System.out.println("Set: " + set);
			
			s3.setRange(-7);
			
		} catch (ScootersException e) {
			System.out.println("ERROR: " + e.getMessage());
		}	
	}
}
