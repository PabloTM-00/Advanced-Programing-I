package prScooters;

import java.util.Objects;

public class Scooter implements Comparable<Scooter>{

	private String companyName;
	private int code;
	private Position position;
	private double range;
	
	public Scooter(String companyName, int code, Position position, double range) throws ScootersException {
		if(code < 0 || range < 0) throw new ScootersException("Code or range cant be negative");
		
		this.companyName = companyName;
		this.code = code;
		this.position = position;
		this.range = range;
	}

	public Scooter(String companyName, int code, Position position) throws ScootersException {
		if(code < 0 || range < 0) throw new ScootersException("Code or range cant be negative");

		this.companyName = companyName;
		this.code = code;
		this.position = position;
		range = 0;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getCode() {
		return code;
	}

	public Position getPosition() {
		return position;
	}

	public double getRange() {
		return range;
	}

	public void setPosition(Position position) throws ScootersException {
		if(position == null) throw new ScootersException("Position cant be negative");
		this.position = position;
	}

	public void setRange(double range) throws ScootersException {
		if(range < 0.0) throw new ScootersException("Range cant be negative");
		this.range = range;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, companyName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scooter other = (Scooter) obj;
		return code == other.code && this.companyName.equalsIgnoreCase(other.companyName);
	}

	@Override
	public int compareTo(Scooter o) {
	    int result = this.companyName.compareToIgnoreCase(o.companyName);
	    if (result != 0) return result;
	    return Integer.compare(this.code, o.code);
	}

	@Override
	public String toString() {
		return "(Company:" + companyName + "; Code:" + code + "; Lat:" + position.getLatitude() + "; Lon:" + position.getLongitude() +  "; Range=" + range
				+ ")";
	}
}
