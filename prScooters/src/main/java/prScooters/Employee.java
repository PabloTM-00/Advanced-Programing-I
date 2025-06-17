package prScooters;

import prScooters.Position;

public class Employee implements Comparable<Employee> {
	
	// instance variables: id and position
	private String id;
	private Position position;
	
	/*
	 *  constructor: an Employee object is created
	 *  given the id (id) and position (pos)
	 *  input parameters
	 */
	public Employee(String id, Position pos) {
		this.id = id;
		position = pos;
	}

	// returns the id
	public String getID() {
		return id;
	}

	// returns the position
	public Position getPositon() {
		return position;
	}

	// modifies the position
	public void setPosition(Position p) {
		position = p;
	}
	
	/*
	 *  check if two employees are equal 
	 */
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Employee) {
			Employee e = (Employee) o;
			res = this.id.equalsIgnoreCase(e.id);
		}
		return res;
	}
	
	// returns the employee hashCode
	public int hashCode() {
		return id.toLowerCase().hashCode();
	}
	
	/*
	 *  returns the employee string representation
	 */
	public String toString() {
		return id;
	}

	 @Override
	    public int compareTo(Employee other) {
	        return this.id.compareToIgnoreCase(other.id);
	    }
}
