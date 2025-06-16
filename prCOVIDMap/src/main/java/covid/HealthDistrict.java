package covid;

import java.util.Objects;

public class HealthDistrict implements Comparable<HealthDistrict> {

	private String district;
	private int population;
	private int covidCases14days;
	
	public HealthDistrict(String district, int population, int covidCases14days) {
		if(district == null || population <= 0 || covidCases14days < 0) {
			throw new COVIDException();
		}	
		
		this.district = district;
		this.population = population;
		this.covidCases14days = covidCases14days;
	}

	public String getDistrict() {
		return district;
	}

	public int getPopulation() {
		return population;
	}

	public int getCovidCases14days() {
		return covidCases14days;
	}

	public void setCovidCases14days(int covidCases14days) {
		if(covidCases14days < 0) throw new COVIDException();
		
		this.covidCases14days = covidCases14days;
	}
	
	public int accumulatedIncidence() {
		return (covidCases14days * 100000)/population;
	}

	@Override
	public int hashCode() {
		return Objects.hash(district);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HealthDistrict other = (HealthDistrict) obj;
		return this.district.equalsIgnoreCase(other.district);
	}
	
	public int compareTo(HealthDistrict h) {
		return this.district.compareToIgnoreCase(h.district);
	}

	@Override
	public String toString() {
		return "District(" + district + ", " + covidCases14days + ")";
	}
}
