package tests;

import java.util.Objects;

public class Exercise implements Comparable<Exercise> {

	private final String name;
	private final String description;
	private int executed;
	private int successful;
	
	public Exercise(String name, String description) {

		if(name == null || description == null || name.isEmpty() || description.isEmpty()) {
			throw new AppException("Name/Description can't be null or zero length");
		}
		
		this.name = name;
		this.description = description;
	}

	public Exercise(String name, String description, int executed, int successful) {

		if(name == null || description == null || name.isEmpty() || description.isEmpty() || executed < 0 || successful < 0) {
			throw new AppException("Name/Description can't be null or zero length or numbers cant be negative");
		}
		
		this.name = name;
		this.description = description;
		this.executed = executed;
		this.successful = successful;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getExecuted() {
		return executed;
	}

	public int getSuccessful() {
		return successful;
	}

	public void setExecuted(int executed) {
		if(executed < 0) throw new AppException("Number of executed tests cant be negative");
		
		this.executed = executed;
	}

	public void setSuccessful(int successful) {
		if(successful < 0) throw new AppException("Number of successful tests cant be negative");
		
		this.successful = successful;
	}
	
	public int getSuccessRate() {
		if(executed == 0) return 0;
		
		int rate = 0;
		
		rate = (100 * successful) / executed;
		
		return rate;
	}

	@Override
	public String toString() {
		return "(" + name + ", " + description + ", " + executed + ", "
				+ successful + ", " + getSuccessRate() + "%)";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		return description.equalsIgnoreCase(other.description) && name.equalsIgnoreCase(other.name);
	}

	@Override
	public int compareTo(Exercise o) {
		int result = name.compareToIgnoreCase(o.name);
		if(result != 0) return result;
		
		return description.compareToIgnoreCase(o.description);
	}
}
