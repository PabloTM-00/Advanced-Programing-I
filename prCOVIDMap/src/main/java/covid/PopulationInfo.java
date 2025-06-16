	package covid;
	
	import java.util.Set;
	import java.util.SortedSet;
	import java.util.TreeSet;

public class PopulationInfo implements COVIDInfo{

	private int minPopulation;
	private int maxPopulation;
	
	public PopulationInfo(int minPopulation, int maxPopulation) {
		super();
		this.minPopulation = minPopulation;
		this.maxPopulation = maxPopulation;
	}

	@Override
	public Set<String> getInfo(COVIDMap mapa) {
		Set<String> set = new TreeSet<>();
		
		SortedSet<HealthDistrict> districts = new TreeSet<>();
		
		for (SortedSet<HealthDistrict> provDistricts : mapa.map.values()) { // First add all districts of each province
		    districts.addAll(provDistricts);
		}
		
		for(HealthDistrict hd : districts) {
			if(hd.getPopulation() > minPopulation && hd.getPopulation() < maxPopulation) {
				set.add(hd.getDistrict());
			}

		}
		return set;
	}
	
	
}
