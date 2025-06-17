package prScooters;

import java.util.SortedSet;
import java.util.TreeSet;

public class CompanySelection extends Company{

	private Selection s;
	
	public CompanySelection(String name, String filename, Selection s) throws ScootersException {
		super(name, filename);
		this.s = s;
	}
	
	@Override
	public void assignScootersToEmployee(Employee e, SortedSet<Integer> codes) {
		SortedSet<Integer> filteredCodes = new TreeSet<>();
		
		for(Integer code : codes) {
			Scooter sc = searchScooter(code);
			
			if(sc != null && s.select(sc)) {
				filteredCodes.add(code);
			}
		}
		super.assignScootersToEmployee(e, filteredCodes);
	}
	
	

}
