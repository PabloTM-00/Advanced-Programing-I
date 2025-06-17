import java.util.SortedSet;
import java.util.TreeSet;

import prScooters.Employee;
import prScooters.Position;
import prScooters.Company;
import prScooters.ScootersException;

public class Main1 {

	public static void main(String[] args) {
		try {
			Company company = new Company("Aliquindoi", "scooters.txt");
			
			System.out.println(company);
	
			Employee e1 = new Employee("11222333X", 
					new Position(-4.4204216, 36.7182771));
			Employee e2 = new Employee("99888777H", 
					new Position(-4.4150382, 36.7306184));

			SortedSet<Integer> c1 = new TreeSet<>();
			c1.add(100);
			c1.add(105);
			c1.add(102);
			c1.add(8);
			company.assignScootersToEmployee(e1, c1);
			
			SortedSet<Integer> c2 = new TreeSet<>();
			c2.add(102);
			c2.add(110);
			c2.add(7);
			company.assignScootersToEmployee(e2, c2);
			
			SortedSet<Integer> c3 = new TreeSet<>();
			c3.add(105);
			c3.add(110);
			c3.add(101);
			company.assignScootersToEmployee(e1, c3);
			
			System.out.println(company);
			
			company.modifyScooter(100, 
					new Position(-4.5444444,36.8011111), 10);
	
			System.out.println(company);
			
		} catch (ScootersException e) {
			System.err.println(e.getMessage());
		} 
	}

}
