import java.util.SortedSet;
import java.util.TreeSet;

import tests.*;

public class MainExercise {

	public static void main(String[] args) {
		try {
			Exercise e1 = new Exercise("Pepe","pr1",20,15);
			Exercise e2 = new Exercise("pepe","PR1",22,10);
			Exercise e3 = new Exercise("paco","pr3",25,12);
			Exercise e4 = new Exercise("paco","pr4",30,17);
			
			if(e1.equals(e2)) {
				System.out.println("equal");
			}
			else System.out.println("different");
			
			if(e2.equals(e3)) {
				System.out.println("equal");
			}
			else System.out.println("different");
			
			if(e3.equals(e4)) {
				System.out.println("equal");
			}
			else System.out.println("different");
			
			
			e1.setSuccessful(18);
			e3.setExecuted(40);
			
			SortedSet<Exercise> set = new TreeSet<>();
			
			set.add(e1);
			set.add(e2);
			set.add(e3);
			set.add(e4);
			
			System.out.println(set.toString());
		
		}
		catch(AppException e) {
			System.out.println(e.getMessage());
		}
	
	}

}
