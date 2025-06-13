package rank;

import java.util.Objects;

public class Site implements Comparable<Site> {
	private String name;
	private double rank;
	
	public Site(String name) {
		super(); // this is not necessary but not wrong either since we're not extending from an explicit class
		
		this.name = name;
		rank = 0;
	}

	public String getName() {
		return name;
	}

	public double getRank() {
		return rank;
	}
	
	public void addRank(double r) {
		rank += r;
	}

	@Override
	public int hashCode() {
		return name.toLowerCase().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Site other = (Site) obj;
		return name.equalsIgnoreCase(other.name);
	}

	@Override
	public String toString() {
		return name + "(" + String.format("%.5f", rank) + ")";
	}

	@Override
	public int compareTo(Site o) {
		return this.name.compareToIgnoreCase(o.name);
	}
		
}
