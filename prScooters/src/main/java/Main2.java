import java.util.SortedSet;

import java.util.TreeSet;

import prScooters.Position;
import prScooters.Employee;
import prScooters.CompanySelection;
import prScooters.ScootersException;
import prScooters.SelectionRange;
import prScooters.SelectionPosition;

public class Main2 {

	public static void main(String[] args) {
		try {
			Employee e = new Employee("11222333X", 
					new Position(-4.4204216, 36.7182771));

			CompanySelection empresa1 = 
					new CompanySelection("Aliquindoi", 
							"scooters.txt",
							new SelectionRange(5.0));
			
			SortedSet<Integer> c1 = new TreeSet<>();
			c1.add(100);
			c1.add(105);
			c1.add(102);
			c1.add(110);
			c1.add(101);
			empresa1.assignScootersToEmployee(e, c1);
								
			System.out.println(empresa1);
			
			CompanySelection empresa2 = 
					new CompanySelection("Aliquindoi", 
							"scooters.txt",
							new SelectionPosition(
									e.getPositon(),
									0.05));
			
			SortedSet<Integer> c2 = new TreeSet<>();
			c2.add(100);
			c2.add(102);
			c2.add(110);
			empresa2.assignScootersToEmployee(e, c2);
			
			System.out.println(empresa2);
		
		} catch (ScootersException e) {
			System.err.println(e.getMessage());
		} 

	}

}
