package covid;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class PerimeterClosureInfo implements COVIDInfo {

	private final int CLOSURE_CASES = 500;
	
	public PerimeterClosureInfo() {
		
	}

	@Override
	public Set<String> getInfo(COVIDMap mapa) {
		Set<String> set = new TreeSet<>();
		
		for(String prov : mapa.getProvinces()) {
			if(mapa.incidenceOfTheProvincia(prov) > 500) {
				set.add(prov);
			}
		}
		return set;
		
	}

}
