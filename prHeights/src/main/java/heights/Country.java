package heights;

import java.util.Objects;

public class Country implements Comparable<Country> {
	private String name;
	private String continent;
	private double height;
	
	public Country(String name, String continent, double height) {
		super();
		this.name = name;
		this.continent = continent;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public String getContinent() {
		return continent;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Country(" + name + "," + continent + "," + height + ")";
	}

	@Override
	public int compareTo(Country c) {
		return this.name.compareTo(c.name);
	}
	
}
